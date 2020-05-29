package geostoat.jankscript.shell.parsing;

import java.util.*;

class ExprNode extends ASTNode {
	List<BinOpNode<?>> children;
	
	ExprNode(List<BinOpNode<?>> nodes) {
		children = new ArrayList<BinOpNode<?>>();
		children.addAll(nodes);
	}
	
	/*ContainerNode<?> child;
	
	ExprNode(ContainerNode<?> child) {
		this.child = child;
	}*/
	
	@Override
	ASTNode traverse() {		
		for (int i = 0; i < children.size(); i++) {
			//LiteralNode<?> x = (LiteralNode<?>) children.get(i).traverse();
			//System.out.println(x);
			System.out.println("Expression: " + children.get(i).traverse());
		}
		
		return null;//child.traverse();
	}
}
