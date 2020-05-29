package geostoat.jankscript.shell.parsing;

import java.util.*;

import geostoat.jankscript.shell.Token;
import geostoat.jankscript.shell.util.Operators;

public class AST extends ASTNode {
	private ASTNode child;
	//private ASTNode nextStatement;
	
	public AST(List<List<Token>> tokens) {
		for (List<Token> line : tokens) {
			KeywordNode keyword = null;
			boolean readRestOfLine = false;
			List<BinOpNode<?>> nodes = new ArrayList<BinOpNode<?>>();
			
			for (int i = 0; i < line.size(); i++) {
				if (readRestOfLine) {
					//System.out.println(line.get(i).getType());
					switch (line.get(i).getType()) {
						case "operator":
							//System.out.println(line.get(i).getValue());
							if (line.get(i - 1).getType().equals("integer") && line.get(i + 1).getType().equals("integer")) {
								//System.out.println(new BinOpNode<>("addition", new LiteralNode<>(Integer.valueOf(line.get(i - 1).getValue())), new LiteralNode<>(Integer.valueOf(line.get(i + 1).getValue()))));
								nodes.add(new BinOpNode<>(Operators.operatorToWord(line.get(i).getValue()), new LiteralNode<>(Integer.valueOf(line.get(i - 1).getValue())), new LiteralNode<>(Integer.valueOf(line.get(i + 1).getValue()))));
							}
							break;
					}
					continue;
				}
				
				switch (line.get(i).getType()) {
					case "print":
							//System.out.println("print " + line.get(i + 1).getValue());
						readRestOfLine = true;
						keyword = new PrintNode();
						break;
				}
			}
			
			if (readRestOfLine && keyword != null) {
				keyword.setValue(new ExprNode(nodes));
				child = keyword;
			}
		}
	}

	@Override
	public ASTNode traverse() {
		return child.traverse();
	}
}
