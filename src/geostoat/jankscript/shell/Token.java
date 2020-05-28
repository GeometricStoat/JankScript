package geostoat.jankscript.shell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Token {
	/*public static enum TokenType {
		//KEYWORD,
		//SEPARATOR,
		//OPERATOR,
		//STRING,
		//INTEGER,
		//FLOAT,
		//SPECIAL
		SPACE,
		ENDLINE,
		OPERATOR,
		ASSIGN,
		EQUALS,
		PRINT,
		INTEGER,
		WHILE,
		IF,
		THEN,
		END,
		VARIABLE
	}*/
	//public static Map<String, String> TOKENS = new HashMap<String, String>();
	public static Map<String, String> SYMBOLS = new HashMap<String, String>();
	public static Map<String, String> KEYWORDS = new HashMap<String, String>();
	public static Map<String, String> VARIABLE = Map.of("[_a-zA-Z][_a-zA-Z0-9]*", "variable");
	private String type;
	private String token;
	
	public Token(String token) {
		this.token = token;
		
		String symbols = getTokenType(token, SYMBOLS);
		String keywords = getTokenType(token, KEYWORDS);
		String variable = getTokenType(token, VARIABLE);
		this.type = symbols != null ? symbols : keywords != null ? keywords : variable != null ? variable : null;
	}
	
	public Token(String token, String type) {
		this.token = token;
		this.type = type;
	}
	
	public String getTokenType() {
		return type;
	}
	
	public String getTokenValue() {
		return token;
	}
	
	@Override
	public String toString() {
		if (type != null)
			return "Token(\"" + token + "\", " + type.toString() + ")";
		else
			return "Token(\"" + token + "\", [No type])";
	}
	
	public static Map<String, String> readFileToMap(String filename) {
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			String[] lines = Files.readString(Path.of(filename), StandardCharsets.US_ASCII).split("\n");
			for (String line : lines) {
				String[] values = line.split(",");
				map.put(values[0], values[1]);
			}
		} catch (IOException e) {
			System.err.println("There was an error in reading the tokens files.");
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static void initTokens() {
		SYMBOLS = readFileToMap("definitions/symbols.txt");
		KEYWORDS = readFileToMap("definitions/keywords.txt");
		//TOKENS.putAll(SYMBOLS);
		//TOKENS.putAll(KEYWORDS);
	}
	
	/*public static Token generateToken(String token) throws IllegalTokenException {
		if (isValidToken(token)) {
			return new Token(token, getTokenType(token));
		} else {
			throw new IllegalTokenException();
		}
	}*/
	
	/*public static boolean isValidToken(String token) {
		for (Map.Entry<String, String> entry : TOKENS.entrySet()) {
			if (Pattern.matches(entry.getKey(), token)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String getTokenType(String token) {
		if (isValidToken(token)) {
			for (Map.Entry<String, String> entry : TOKENS.entrySet()) {
				if (Pattern.matches(entry.getKey(), token)) {
					return entry.getValue();
				}
			}
		}
		
		return null;
	}*/
	
	public static boolean isValidToken(String token, Map<String, String> tokens) {
		for (Map.Entry<String, String> entry : tokens.entrySet()) {
			if (Pattern.matches(entry.getKey(), token)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String getTokenType(String token, Map<String, String> tokens) {
		if (isValidToken(token, tokens)) {
			for (Map.Entry<String, String> entry : tokens.entrySet()) {
				if (Pattern.matches(entry.getKey(), token)) {
					return entry.getValue();
				}
			}
		}
		
		return null;
	}
	
	/*public static TokenType getTokenType(String token, int index) {
		token = String.valueOf(token.charAt(index));
		if (isValidToken(token)) {
			for (Map.Entry<String, TokenType> entry : TOKENS.entrySet()) {
				if (Pattern.matches(entry.getKey(), token)) {
					return entry.getValue();
				}
			}
		}
		
		return null;
	}*/
}