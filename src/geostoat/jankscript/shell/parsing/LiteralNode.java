package geostoat.jankscript.shell.parsing;

class LiteralNode<T> extends ContainerNode<T> {
	private T value;
		
	LiteralNode(T value) {
		this.value = value;
	}

	@Override
	T getValue() {
		return value;
	}
	
	@Override
	ASTNode traverse() {
		return this;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
