package geostoat.jankscript.shell.parsing;

class PrintNode extends ASTNode {
	ExprNode value;
	
	PrintNode() {}
	
	PrintNode(ExprNode value) {
		this.value = value;
	}
	
	void setValue(ExprNode value) {
		this.value = value;
	}

	@Override
	ASTNode traverse() {
		if (value != null)
			System.out.println(value.traverse());
		return null;
	}
}
