package main.load;

public class Loading {
	
	private static boolean loading = false;
	
	public static void startLoad() {
		loading = true;
	}
	
	public static void stopLoad() {
		loading = false;
	}
	
	public static boolean isLoading() {
		return loading;
	}

}
