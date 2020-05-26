package geostoat.jankscript.shell.parsing;

import geostoat.jankscript.shell.util.Operator;

class BinOpNode<T> extends ASTNode {
	private Operator operator;
	private LiteralNode<T> x;
	private LiteralNode<T> y;
	
	BinOpNode(Operator operator, LiteralNode<T> x, LiteralNode<T> y) {
		this.operator = operator;
		this.x = x;
		this.y = y;
	}

	@Override
	ASTNode traverse() {
		if (x.getValue() instanceof Float && y.getValue() instanceof Float)
			return new LiteralNode<Float>(operator.apply((float)x.getValue(), (float)y.getValue()));
		else if (x.getValue() instanceof Integer && y.getValue() instanceof Integer)
			return new LiteralNode<Integer>(operator.apply((int)x.getValue(), (int)y.getValue()));			
		else if (operator == Operator.ADDITION && x.getValue() instanceof String && y.getValue() instanceof String)
			return new LiteralNode<String>(operator.apply((String)x.getValue(), (String)y.getValue()));
		else if (operator == Operator.MULTIPLICATION && x.getValue() instanceof String && y.getValue() instanceof Integer)
			return new LiteralNode<String>(operator.apply((String)x.getValue(), (Integer)y.getValue()));
		return null;
	}
}
