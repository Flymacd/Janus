package main.debug;

public class Debugger {
	
	public Debugger() {}
	
	public static void printLine(String string) {
		System.out.println("[PRINT]: " + string);
	}
	
	public static void printError(String string) {
		System.out.println("[ERROR]: " + string);
	}
	
	public static void printWarning(String string) {
		System.out.println("[WARNING]: " + string);
	}

}
