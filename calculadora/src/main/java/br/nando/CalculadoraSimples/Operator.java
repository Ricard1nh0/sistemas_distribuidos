package br.nando.CalculadoraSimples;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class Operator {
	public Double soma(Double n1, Double n2) {
		return(n1+n2);
	}
	
	public Double sub(Double n1, Double n2) {
		return(n1-n2);
	}

	public double multiply(double n1, double n2){
		return(n1*n2);
	}

	public double dividir(double n1, double n2){
		
		if (n2 == 0) {
			throw new IllegalArgumentException("Não é possível dividir por 0");
		}

		return(n1 / n2);
	}

	public double raizQuadrada(double n1){
		return(Math.sqrt(n1));
	}

	public double raizCubica(double n1){
		return(Math.cbrt(n1));
	}

	public double potencia(double n1, double n2){
		return(Math.pow(n1, n2));
	}

	public String binario(int n1){
		return(Integer.toBinaryString(n1));
	}

	public BigInteger fatorial(int n1) {
        if (n1 < 0) {
            throw new IllegalArgumentException("Fatorial não é definido para números negativos.");
        }
        
        BigInteger resultado = BigInteger.ONE;
        
        for (int i = 2; i <= n1; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        
        return resultado;
    }

		public double pi(){
			return Math.PI;
		}

}
