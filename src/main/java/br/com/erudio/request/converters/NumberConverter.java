package br.com.erudio.request.converters;

public class NumberConverter {
	public static String formatString(String strNumber) {
		return strNumber.replaceAll(",", ".");
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}	
	
	public static Double convertToDouble(String strNumber) {
		return Double.parseDouble(strNumber);
	}
}
