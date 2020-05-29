package geostoat.jankscript.shell.util;

import java.io.Serializable;

public final class Operators {
	public interface Operator<T extends Serializable> extends Serializable {
		T apply(T x, T y);
	}
	
	public static enum IntOperator implements Operator<Integer> {
		ADDITION {
			@Override
			public Integer apply(Integer x, Integer y) {
				return x + y;
			}
		},
		SUBTRACTION {
			@Override
			public Integer apply(Integer x, Integer y) {
				return x - y;
			}
		},
		MULTIPLICATION {
			@Override
			public Integer apply(Integer x, Integer y) {
				return x * y;
			}
		},
		DIVISION {
			@Override
			public Integer apply(Integer x, Integer y) {
				return x / y;
			}
		}
	}
	
	public static enum FloatOperator implements Operator<Float> {
		ADDITION {
			@Override
			public Float apply(Float x, Float y) {
				return x + y;
			}
		},
		SUBTRACTION {
			@Override
			public Float apply(Float x, Float y) {
				return x - y;
			}
		},
		MULTIPLICATION {
			@Override
			public Float apply(Float x, Float y) {
				return x * y;
			}
		},
		DIVISION {
			@Override
			public Float apply(Float x, Float y) {
				return x / y;
			}
		}
	}
	
	public static enum StringOperator implements Operator<String> {
		ADDITION {
			@Override
			public String apply(String x, String y) {
				return x + y;
			}
		}
	}
	
	public static String operatorToWord(String operator) {
		switch (operator) {
			case "+":
				return "addition";
			case "-":
				return "subtraction";
			case "*":
				return "multiplication";
			case "/":
				return "division";
			default:
				return "";
		}
	}
}

/*
public enum Operator {
	ADDITION {
		@Override
		public float apply(float x, float y) {
			return x + y;
		}
		
		@Override
		public int apply(int x, int y) {
			return x + y;
		}
		
		@Override
		public String apply(String x, String y) {
			return x + y;
		}
	},
	SUBTRACTION {
		@Override
		public float apply(float x, float y) {
			return x - y;
		}
		
		@Override
		public int apply(int x, int y) {
			return x - y;
		}
	},
	MULTIPLICATION {
		@Override
		public float apply(float x, float y) {
			return x * y;
		}
		
		@Override
		public int apply(int x, int y) {
			return x * y;
		}
		
		@Override
		public String apply(String x, int y) {
			String r = "";
			
			for (int i = 0; i < y; i++)
				r += x;
			
			return r;
		}
	},
	DIVISION {
		@Override
		public float apply(float x, float y) {
			return x / y;
		}
		
		@Override
		public int apply(int x, int y) {
			return x / y;
		}
	};
	
	public abstract float apply(float x, float y);
	public abstract int apply(int x, int y);
	public String apply(String x, String y) { return null; }
	public String apply(String x, int y) { return null; }
}*/
