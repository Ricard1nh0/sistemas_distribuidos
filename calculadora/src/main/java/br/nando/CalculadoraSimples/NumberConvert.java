package br.nando.CalculadoraSimples;

import br.nando.CalculadoraSimples.exception.UnsupportedMathOperationException;

public class NumberConvert {
	public static Double convertToDouble(String strNumber) throws Exception {
		if(strNumber==null) {
			throw new UnsupportedMathOperationException("Não é possível fazer operação com letras e números");			
		}
		
		strNumber =strNumber.replaceAll(",",".");
		
		if(!isNumeric(strNumber)) {
			throw new UnsupportedMathOperationException("Não é possível fazer operação com letras e números");
		}
		
		return Double.parseDouble(strNumber);
	}
	
	public static boolean isNumeric(String strNumber) {
		if(strNumber==null) {
			return false;
		}
		
		return(strNumber.matches(("[-+]?[0-9]*\\.?[0-9]+")));
	}

	public static Integer convertToInteger(String strNumber) throws Exception {
		if(strNumber==null) {
			throw new UnsupportedMathOperationException("Não é possível fazer operação com letras e números");			
		}
		
		strNumber = strNumber.replaceAll(",",".");
		
		if(!isNumeric(strNumber)) {
			throw new UnsupportedMathOperationException("Não é possível fazer operação com letras e números");
		}
		
		return Integer.parseInt(strNumber);
	}
}
