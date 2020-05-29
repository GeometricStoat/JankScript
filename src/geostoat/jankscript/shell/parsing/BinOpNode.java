package geostoat.jankscript.shell.parsing;

import java.io.Serializable;

import geostoat.jankscript.shell.util.Operators.*;

class BinOpNode<T extends Serializable> extends ContainerNode<T> {
	private String operator;
	private ContainerNode<?> x;
	private ContainerNode<?> y;
	
	BinOpNode(String operator, ContainerNode<?> x, ContainerNode<?> y) {
		this.operator = operator.toUpperCase();
		this.x = x;
		this.y = y;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	T getValue() {
		if (x == null || y == null)
			return null;
						
		if (x.getValue() instanceof Float && y.getValue() instanceof Float)
			return (T) FloatOperator.valueOf(operator).apply((float)x.getValue(), (float)y.getValue());
		else if (x.getValue() instanceof Integer && y.getValue() instanceof Integer)
			return (T) IntOperator.valueOf(operator).apply((int)x.getValue(), (int)y.getValue());			
		else if (x.getValue() instanceof String && y.getValue() instanceof String)
			return (T) StringOperator.valueOf(operator).apply((String)x.getValue(), (String)y.getValue());
		
		

		return null;
	}

	@Override
	ASTNode traverse() {
		/*if (x.getValue() instanceof Float && y.getValue() instanceof Float)
			return new LiteralNode<Float>(operator.apply((float)x.getValue(), (float)y.getValue()));
		else if (x.getValue() instanceof Integer && y.getValue() instanceof Integer)
			return new LiteralNode<Integer>(operator.apply((int)x.getValue(), (int)y.getValue()));			
		else if (operator == Operators.ADDITION && x.getValue() instanceof String && y.getValue() instanceof String)
			return new LiteralNode<String>(operator.apply((String)x.getValue(), (String)y.getValue()));
		else if (operator == Operator.MULTIPLICATION && x.getValue() instanceof String && y.getValue() instanceof Integer)
			return new LiteralNode<String>(operator.apply((String)x.getValue(), (Integer)y.getValue()));*/
		
		return new LiteralNode<T>(this.getValue());
	}
	
	@Override
	public String toString() {
		return this.getValue().toString();
	}
}
