package geostoat.jankscript.shell.parsing;

class VariableNode<T> extends ASTNode {
	private String name;
	private T value;
	
	VariableNode(String name, T value) {
		this.name = name;
		this.value = value;
	}
	
	String getName() {
		return name;
	}
	
	T getValue() {
		return value;
	}
	
	@Override
	ASTNode traverse() {
		return new LiteralNode<T>(value);
	}
}
