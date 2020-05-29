package geostoat.jankscript.shell.parsing;

abstract class KeywordNode extends ASTNode {
	ExprNode value;
		
	void setValue(ExprNode value) {
		this.value = value;
	}
}
