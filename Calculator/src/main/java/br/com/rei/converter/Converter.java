package br.com.rei.converter;

public class Converter {

	public static Double convertToDouble(String strNum) {
		if (strNum == null) return 0D;
		// Convert "," to "."
		String number = strNum.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) return false;
		// Convert "," to "."
		String number = strNum.replaceAll(",", ".");
		// Regex to check if is numeric
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
