package br.com.erudio.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsuportedMathOperationException;
import br.com.erudio.math.SimpleMath;
import br.com.erudio.request.converters.NumberConverter;

@RestController
public class MathController {
	
	private SimpleMath math = new SimpleMath();

	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable(value="numberOne") String numberOne, 
					  @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		numberOne = NumberConverter.formatString(numberOne);
		numberTwo = NumberConverter.formatString(numberTwo);

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value.");
		}

		return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo)); 

	}

	@RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtraction(@PathVariable(value="numberOne") String numberOne, 
						      @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		numberOne = NumberConverter.formatString(numberOne);
		numberTwo = NumberConverter.formatString(numberTwo);

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value.");
		}
		
		return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo)); 

	}

	@RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double multiplication(@PathVariable(value="numberOne") String numberOne, 
						   		 @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		numberOne = NumberConverter.formatString(numberOne);
		numberTwo = NumberConverter.formatString(numberTwo);

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value.");
		}

		return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo)); 

	}

	@RequestMapping(value="/division/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double division(@PathVariable(value="numberOne") String numberOne, 
						   @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		numberOne = NumberConverter.formatString(numberOne);
		numberTwo = NumberConverter.formatString(numberTwo);

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberOne) || (numberTwo == "0")) {
			throw new UnsuportedMathOperationException("Please set a numeric value.");
		}

		return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));

	}

	@RequestMapping(value="/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double mean(@PathVariable(value="numberOne") String numberOne, 
					   @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		
		numberOne = NumberConverter.formatString(numberOne);
		numberTwo = NumberConverter.formatString(numberTwo);

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value.");
		}

		return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));

	}
	
	@RequestMapping(value="/squareRoot/{numberOne}", method=RequestMethod.GET)
	public Double squareRoot(@PathVariable(value="numberOne") String numberOne) throws Exception {
		
		numberOne = NumberConverter.formatString(numberOne);

		if (!NumberConverter.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value.");
		}

		return math.squareRoot(NumberConverter.convertToDouble(numberOne));

	}

}
