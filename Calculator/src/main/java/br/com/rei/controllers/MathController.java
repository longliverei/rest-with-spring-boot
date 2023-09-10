package br.com.rei.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rei.converter.Converter;
import br.com.rei.exceptions.UnsupportedMathOperationException;
import br.com.rei.math.MathFunctions;

@RestController
public class MathController {
	
	MathFunctions math = new MathFunctions();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
			if(!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
			// Make sum if Converter.isNumeric = true
			return math.sum(Converter.convertToDouble(numberOne), Converter.convertToDouble(numberTwo));
		}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
		if(!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		// Make subtraction if Converter.isNumeric = true
		return math.sub(Converter.convertToDouble(numberOne), Converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/multi/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double multi(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
		if(!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		// Make multiplication if Converter.isNumeric = true
		return math.multi(Converter.convertToDouble(numberOne), Converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
		if(!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		// Make division if Converter.isNumeric = true
		return math.div(Converter.convertToDouble(numberOne), Converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/avg/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double avg(
			@PathVariable(value = "numberOne")String numberOne,
			@PathVariable(value= "numberTwo") String numberTwo
			) throws Exception {
		
		if(!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		// Make average if Converter.isNumeric = true
		return math.avg(Converter.convertToDouble(numberOne), Converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/sqr/{numberOne}",
			method=RequestMethod.GET)
	public Double sqr(
			@PathVariable(value = "numberOne")String numberOne
			) throws Exception {
		
		if(!Converter.isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		// Make square root if Converter.isNumeric = true
		return math.sqr(Converter.convertToDouble(numberOne));
	}
}
