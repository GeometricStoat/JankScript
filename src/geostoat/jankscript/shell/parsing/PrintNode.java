package geostoat.jankscript.shell.parsing;

class PrintNode extends KeywordNode {
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
			System.out.println("[PRINT] " + value.traverse());
		return null;
	}
}
