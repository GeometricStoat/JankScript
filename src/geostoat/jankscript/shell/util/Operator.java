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
}
