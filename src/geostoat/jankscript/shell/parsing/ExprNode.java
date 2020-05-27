package geostoat.jankscript.shell.parsing;

class ExprNode extends ASTNode {
	ASTNode[] children;
	
	ExprNode(ASTNode... nodes) {
		children = nodes;
	}
	
	@Override
	ASTNode traverse() {
		for (int i = 0; i < children.length; i++) {
			if (children[i] instanceof LiteralNode<?>)
				System.out.println("Hey");
		}
		
		return null;
	}
}
