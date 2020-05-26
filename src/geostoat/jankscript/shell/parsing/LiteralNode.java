package geostoat.jankscript.shell.parsing;

class LiteralNode<T> extends ASTNode {
	private T value;
	
	LiteralNode(T value) {
		this.value = value;
	}

	T getValue() {
		return value;
	}
	
	@Override
	ASTNode traverse() {
		return null;
	}

}
