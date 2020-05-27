package geostoat.jankscript.shell.parsing;

import java.util.List;

import geostoat.jankscript.shell.Token;

public class AST extends ASTNode {
	private ASTNode child;
	
	public AST(List<Token> tokens) {
		
	}

	@Override
	public ASTNode traverse() {
		return child.traverse();
	}
}
