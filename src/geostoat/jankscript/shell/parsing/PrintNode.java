package geostoat.jankscript.shell.parsing;

class PrintNode extends ASTNode {
	ExprNode value;
	
	PrintNode(ExprNode value) {
		this.value = value;
	}

	@Override
	ASTNode traverse() {
		System.out.println(value.traverse());
		return null;
	}
}
