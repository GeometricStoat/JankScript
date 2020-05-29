package geostoat.jankscript.shell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import geostoat.jankscript.shell.parsing.AST;

public class JankShell {	
	private List<List<Token>> tokenize(String script) {
		List<List<Token>> tokenizedScript = new ArrayList<List<Token>>();
		List<Token> tokens = new ArrayList<Token>();
		String currentToken = "";
		
		script = ' ' + script;
		if (script.charAt(script.length() - 1) != '\n')
			script += '\n';
		script += '\n';
		
		for (int i = 1; i < script.length() - 1; i++) {
			String current = Character.toString(script.charAt(i));
			String next = Character.toString(script.charAt(i + 1));
			
			if (Token.isValidToken(current, Token.SYMBOLS) || Token.isValidToken(currentToken, Token.SYMBOLS)) {
				String symbol = current;
				
				if (Token.isValidToken(current + next, Token.SYMBOLS)) {
					symbol += next;
					i++;
				}
								
				if (Token.isValidToken(currentToken, Token.VARIABLE)) {
					//System.out.println("[" + i + "] Variable: " + currentToken.strip());
					tokens.add(new Token(currentToken));
					currentToken = "";
				}

				//System.out.println("[" + i + "] Symbol: " + symbol.strip());
				tokens.add(new Token(symbol));
			} else if (Token.isValidToken(current, Token.KEYWORDS) || Token.isValidToken(currentToken, Token.KEYWORDS)) {
				//System.out.println("[" + i + "] Keyword: " + currentToken.strip());
				tokens.add(new Token(currentToken));
				currentToken = "";
			} else {
				if (!current.equals(" ") && !current.equals("\n"))
					currentToken += current;
								
				if (current.equals("\n")) {					
					if (Token.isValidToken(currentToken, Token.VARIABLE)) {
						//System.out.println("[" + i + "] Variable: " + currentToken.strip());
						tokens.add(new Token(currentToken));
					}
					
					currentToken = "";
					
					tokenizedScript.add(new ArrayList<>(tokens));
					tokens = new ArrayList<Token>();
				}
			}
		}
		
		return tokenizedScript;
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
		
		new AST(tokens).traverse();
	}
	
	public JankShell() {
		Token.initTokens();
	}
	
	public void interpret(String string) {
		List<List<Token>> tokens = tokenize(string);
		
		/*for (List<Token> line : tokens) {
			for (Token token : line) {
				System.out.println(token);
			}
			System.out.println("--LINE--");
		}*/
		
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
