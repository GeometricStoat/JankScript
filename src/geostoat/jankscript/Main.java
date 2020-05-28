package geostoat.jankscript;

import geostoat.jankscript.shell.JankShell;

public class Main {
	public static void main(String args[]) {
		JankShell shell = new JankShell();
		shell.interpretFile("scripts/test.jank");
	}
}
