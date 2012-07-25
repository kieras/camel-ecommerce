package arkhi.camel.ecommerce.domain;


public class Duration {

	private static long init;
	
	public static void init() {
		init = System.currentTimeMillis();
	}
	
	public static String get() {
		return ((System.currentTimeMillis() - init)/1000) + "s";
	}
	
}
