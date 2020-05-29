package geostoat.jankscript.shell.parsing;

abstract class ASTNode {	
	abstract ASTNode traverse();
	
	@Override
	public String toString() {
		return this.getClass().getName() + "()";
	}
}
