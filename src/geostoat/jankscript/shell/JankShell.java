package geostoat.jankscript.shell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import geostoat.jankscript.shell.Token.TokenType;

public class JankShell {
	private List<Token> tokenize(String string) {
		List<Token> tokens = new ArrayList<Token>();
		String currentToken = "";		
		boolean lexingString = false;
		string = '\n' + string + '\n';
		
		Anonymous sep = (t, i, p) -> {
			return Token.getTokenType(t, i+p) == TokenType.SEPARATOR || t.charAt(i+p) == ' ';
		};
		
		try {
			tokens.add(Token.generateToken("sol"));
		} catch (IllegalTokenException e) {
			System.err.println("There was an error creating new lines.");
			e.printStackTrace();
		}
		
		for (int i = 1; i < string.length() - 1; i++) {
			if (string.charAt(i) == '\n') {
				try {
					tokens.add(Token.generateToken("sol"));
				} catch (IllegalTokenException e) {
					System.err.println("There was an error creating new lines.");
					e.printStackTrace();
				}
				continue;
			}
			if (string.charAt(i) == '"') lexingString = !lexingString;
			if (lexingString) {
				currentToken += string.charAt(i);
				continue;
			}
			
			TokenType type = Token.getTokenType(string, i);
			if (sep.isTrue(string, i, 0)) {
				try {
					if (sep.isTrue(string, i, -1)) continue;//string.charAt(i-1) == ' ') continue;
					tokens.add(Token.generateToken(currentToken));
					currentToken = "";
				} catch (IllegalTokenException e) {
					System.err.println("There was an error separating tokens.");
					e.printStackTrace();
				}
			} else if (type == TokenType.OPERATOR) {
				try {
					if (!sep.isTrue(string, i, -1))
						tokens.add(Token.generateToken(currentToken));
					currentToken = "";
					
					currentToken += string.charAt(i);
					tokens.add(Token.generateToken(currentToken));
					currentToken = "";
					
					if (sep.isTrue(string, i, 1)) i++;
				} catch (IllegalTokenException e) {
					System.err.println("There was an error separating operators.");
					e.printStackTrace();
				}
			} else {
				currentToken += string.charAt(i);
			}
		}
		
		return tokens;
	}
	
	private void parse(List<Token> tokens) {		
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).getTokenValue().equals("sol")) {
				List<Token> segment = tokens.subList(i+1, tokens.size());
				
				for (int j = 0; j < segment.size() - 1; j++) {
					if (segment.get(j).getTokenValue().equals("sol")) break;
					if (segment.get(j).getTokenValue().equals("+")) {
						try {
							boolean isFloatOperation = false;
							if (segment.get(j-1).getTokenType() == TokenType.FLOAT || segment.get(j+1).getTokenType() == TokenType.FLOAT) isFloatOperation = true;
							if (isFloatOperation)
								segment.set(j-1, Token.generateToken(String.valueOf(Float.valueOf(segment.get(j-1).getTokenValue()) + Float.valueOf(segment.get(j+1).getTokenValue()))));
							else
								segment.set(j-1, Token.generateToken(String.valueOf(Integer.valueOf(segment.get(j-1).getTokenValue()) + Integer.valueOf(segment.get(j+1).getTokenValue()))));
						} catch (NumberFormatException e) {
							System.err.println("The values provided for the operation were invalid.");
							e.printStackTrace();
						} catch (IllegalTokenException e) {
							System.err.println("There was an error while parsing an operation.");
							e.printStackTrace();
						}
					}
				}
			}
			if (tokens.get(i).getTokenValue().equals("print")) {
				System.out.println(tokens.get(i+1).getTokenValue());
			}
		}
	}
	
	public JankShell() {
		Token.initTokens();
	}
	
	public void interpret(String string) {
		List<Token> tokens = tokenize(string);
		parse(tokens);
	}
	
	public void interpretFile(String filename) {
		try {
			String file = Files.readString(Path.of(filename), StandardCharsets.US_ASCII);
			interpret(file);
		} catch (IOException e) {
			System.err.println("There was an error in reading the script file.");
			e.printStackTrace();
		}
	}
}
