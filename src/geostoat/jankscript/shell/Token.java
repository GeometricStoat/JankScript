package geostoat.jankscript.shell;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;

public class Token {
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
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
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
	}
	
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
}