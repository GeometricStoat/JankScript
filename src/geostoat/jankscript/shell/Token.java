package geostoat.jankscript.shell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class Token {
	public static enum TokenType {
		KEYWORD,
		SEPARATOR,
		OPERATOR,
		STRING,
		INTEGER,
		FLOAT,
		SPECIAL
	}
	public static Map<String, TokenType> TOKENS = new HashMap<String, TokenType>();
	private TokenType type;
	private String token;
	
	Token(String token, TokenType type) {
		this.token = token;
		this.type = type;
	}
	
	TokenType getTokenType() {
		return type;
	}
	
	String getTokenValue() {
		return token;
	}
	
	public String toString() {
		return "Token(" + token + ", " + type.toString() + ")";
	}
	
	static void initTokens() {
		try {
			String[] lines = Files.readString(Path.of("definitions/tokens.csv"), StandardCharsets.US_ASCII).split("\n");
			for (String line : lines) {
				String[] values = line.split(",");
				TOKENS.put(values[0], TokenType.valueOf(values[1]));
			}
		} catch (IOException e) {
			System.err.println("There was an error in reading the tokens file.");
			e.printStackTrace();
		}
	}
	
	static Token generateToken(String token) throws IllegalTokenException {
		if (isValidToken(token)) {
			return new Token(token, getTokenType(token));
		} else {
			throw new IllegalTokenException();
		}
	}
	
	static boolean isValidToken(String token) {
		for (Map.Entry<String, TokenType> entry : TOKENS.entrySet()) {
			if (Pattern.matches(entry.getKey(), token)) {
				return true;
			}
		}
		
		return false;
	}
	
	static TokenType getTokenType(String token) {
		if (isValidToken(token)) {
			for (Map.Entry<String, TokenType> entry : TOKENS.entrySet()) {
				if (Pattern.matches(entry.getKey(), token)) {
					return entry.getValue();
				}
			}
		}
		
		return null;
	}
	
	static TokenType getTokenType(String token, int index) {
		token = String.valueOf(token.charAt(index));
		if (isValidToken(token)) {
			for (Map.Entry<String, TokenType> entry : TOKENS.entrySet()) {
				if (Pattern.matches(entry.getKey(), token)) {
					return entry.getValue();
				}
			}
		}
		
		return null;
	}
}