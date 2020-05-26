package geostoat.jankscript.shell.util;

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
}
