package geostoat.jankscript.shell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import geostoat.jankscript.shell.parsing.AST;

public class JankShell {
	/*private static void addToken(List<Token> tokens, Token token) {
		if (token.getTokenType() != null)
			tokens.add(token);
	}*/
	
	private List<Token> tokenize(String script) {
		List<Token> tokens = new ArrayList<Token>();
		String currentToken = "";
		String previousToken = "";
		
		//string = '\n' + string + '\n';
		//boolean lexingString = false;
		
		script = ' ' + script;
		
		if (script.charAt(script.length() - 1) != '\n')
			script += '\n';
		
		for (int i = 1; i < script.length() - 1; i++) {
			String current = Character.toString(script.charAt(i));
			String next = Character.toString(script.charAt(i + 1));
			char currentChar = script.charAt(i);
			
			if (Token.isValidToken(current, Token.SYMBOLS) || Token.isValidToken(currentToken, Token.SYMBOLS)) {
				//System.out.println("TokenSym: " + current);
				
				String symbol = current;
				
				if (Token.isValidToken(current + next, Token.SYMBOLS)) {
					symbol += next;
					i++;
				}
				
				//System.out.println("[" + i + "] Symbol: " + symbol.strip());
								
				if (Token.isValidToken(currentToken, Token.VARIABLE)) {
					//System.out.println("[" + i + "] Variable: " + currentToken.strip());
					tokens.add(new Token(currentToken));
				}
				
				tokens.add(new Token(symbol));
				currentToken = "";
			}
						
			else if (Token.isValidToken(current, Token.KEYWORDS) || Token.isValidToken(currentToken, Token.KEYWORDS)) {
				//System.out.println("[" + i + "] Keyword: " + currentToken.strip());
				
				tokens.add(new Token(currentToken));
				currentToken = "";
			}			
			
			else {
				if (currentChar != ' ' && currentChar != '\n') {
					currentToken += current;
				}
				
				if (currentChar == '\n') {
					previousToken = currentToken;
					currentToken = "";
				}
				
				if (Token.isValidToken(previousToken, Token.VARIABLE)) {
					//System.out.println("[" + i + "] Variable: " + previousToken.strip());
					tokens.add(new Token(previousToken));
					previousToken = "";
				}
			}
			
			
			//if (string.script(i) == '\n') continue;
			/*if (string.charAt(i) == '"') lexingString = !lexingString;
			if (lexingString) {
				currentToken += string.charAt(i);
				continue;
			}*/
			
			//System.out.print(string.charAt(i));
			//System.out.println(i);
			
			
			//TokenType type = Token.getTokenType(currentToken);
			//TokenType type = Token.getTokenType(Character.toString(script.charAt(i)));
			//boolean newToken = true;
						
			//if (type == null || type == TokenType.ENDLINE || type == TokenType.SPACE) newToken = false;
			
			//System.out.println("0|" + currentToken + "|" + Integer.toString(i));
			//System.out.println("type|" + type + "|" + Integer.toString(i));
			//System.out.println("new|" + newToken + "|" + Integer.toString(i));
			
			/*if (type == TokenType.ENDLINE || type == TokenType.SPACE) {
				//System.out.println("token|" + currentToken + "|" + Integer.toString(i));
			//	addToken(tokens, new Token(currentToken));
			//	currentToken = "";
				continue;
			}
			
			currentToken += script.charAt(i);
			
			if (newToken) {
				addToken(tokens, new Token(currentToken));
				currentToken = "";
				continue;
			}*/
			
			/*if (isNextToken(string, i, 0)) {
				try {
					if (isNextToken(string, i, -1)) continue;

					tokens.add(Token.generateToken(currentToken));
					currentToken = "";
				} catch (IllegalTokenException e) {
					System.err.println("There was an error separating tokens.");
					e.printStackTrace();
				}
			} else if (Token.getTokenType(string, i) == TokenType.OPERATOR) {
				try {
					if (!isNextToken(string, i, -1))
						tokens.add(Token.generateToken(currentToken));
					currentToken = "";
					
					currentToken += string.charAt(i);
					tokens.add(Token.generateToken(currentToken));
					currentToken = "";
					
					if (isNextToken(string, i, 1)) i++;
				} catch (IllegalTokenException e) {
					System.err.println("There was an error separating operators.");
					e.printStackTrace();
				}
			} else {
				currentToken += string.charAt(i);
			}*/
		}
		
		return tokens;
	}
	
	/*private static List<Token> parseOperator(Operator operator, List<Token> tokens, int i) {
		try {
			boolean isFloatOperation = false;
			if (tokens.get(i-1).getTokenType() == "float" || tokens.get(i+1).getTokenType() == "float") isFloatOperation = true;
			if (isFloatOperation)
				tokens.set(i-1, new Token(String.valueOf(operator.apply(Float.valueOf(tokens.get(i-1).getTokenValue()), Float.valueOf(tokens.get(i+1).getTokenValue())))));
			else
				tokens.set(i-1, new Token(String.valueOf(operator.apply(Integer.valueOf(tokens.get(i-1).getTokenValue()), Integer.valueOf(tokens.get(i+1).getTokenValue())))));
		} catch (NumberFormatException e) {
			System.err.println("The values provided for the operation were invalid.");
			e.printStackTrace();
		}
		
		return tokens;
	}*/
	
	private void parse(List<List<Token>> tokens) {		
		/*for (List<Token> line : tokens) {//int line; = 0; i < tokens.size(); i++) {
			for (int i = 0; i < line.size(); i++) {
				if (i == 0) {//tokens.get(l).get(i).getTokenValue().equals("sol")) {
					List<Token> segment = line.subList(i+1, line.size());
					
					for (int j = 0; j < segment.size() - 1; j++) {
						if (segment.get(j).getTokenValue().equals("sol")) break;
						else if (segment.get(j).getTokenValue().equals("+"))
							segment = parseOperator(Operator.ADDITION, segment, j);
						else if (segment.get(j).getTokenValue().equals("-"))
							segment = parseOperator(Operator.SUBTRACTION, segment, j);
						else if (segment.get(j).getTokenValue().equals("*"))
							segment = parseOperator(Operator.MULTIPLICATION, segment, j);
						else if (segment.get(j).getTokenValue().equals("/"))
							segment = parseOperator(Operator.DIVISION, segment, j);
					}
				}
				if (line.get(i).getTokenValue().equals("print")) {
					System.out.println(line.get(i+1).getTokenValue());
				}
			}
		}*/
		
		AST ast = new AST(tokens.get(0));
		ast.traverse();
	}
	
	public JankShell() {
		Token.initTokens();
	}
	
	public void interpret(String string) {
		List<Token> tokens = tokenize(string);
		
		for (Token token : tokens) {
			System.out.println(token);
		}
		
		//parse(tokens);
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
