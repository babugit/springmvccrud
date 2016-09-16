package example.spring.mvccrud.config;

public class Utils {

	public static boolean isEmpty(String string) {
		if (string == null || string.equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Integer integer) {
		if (integer == null || integer.intValue() == 0) {
			return true;
		}
		return false;
	}
	
	
	public static String[] stringToArray(String str) {
		if (isEmpty(str)) {
			return new String[0];
		}
		String[] ret = str.split(",");
		
		return ret;
	}

	public static String arrayToString(String[] arr) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		
		return String.join(",", arr);
	}
}
