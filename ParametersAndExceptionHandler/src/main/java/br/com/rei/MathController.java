package br.com.rei;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rei.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
			if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
			// Make sum if isNumeric = true
			return convertToDouble(numberOne) + convertToDouble(numberTwo);
		}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		// Make subtraction if isNumeric = true
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/multi/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double multi(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		// Make subtraction if isNumeric = true
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}

	private Double convertToDouble(String strNum) {
		if (strNum == null) return 0D;
		// Convert "," to "."
		String number = strNum.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNum) {
		if (strNum == null) return false;
		// Convert "," to "."
		String number = strNum.replaceAll(",", ".");
		// Regex to check if is numeric
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
