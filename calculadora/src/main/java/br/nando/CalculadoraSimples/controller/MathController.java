package br.nando.CalculadoraSimples.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.nando.CalculadoraSimples.NumberConvert;
import br.nando.CalculadoraSimples.Operator;

@RestController
public class MathController {
		
	@Autowired
    private Operator math;
	
	@RequestMapping("/soma/{n1}/{n2}")
	public Double soma(@PathVariable (value = "n1") String n1,
					   @PathVariable (value = "n2") String n2) throws Exception {
		
		return math.soma(NumberConvert.convertToDouble(n1), NumberConvert.convertToDouble(n2));
	}
	
	@RequestMapping("/sub/{n1}/{n2}")
	public Double subtracao(@PathVariable (value = "n1") String n1,
					  @PathVariable (value = "n2") String n2) throws Exception {
				
		return math.sub(NumberConvert.convertToDouble(n1), NumberConvert.convertToDouble(n2));
		
	}

	@RequestMapping("/multiply/{n1}/{n2}")
	public double multiply(@PathVariable (value = "n1") String n1, 
						   @PathVariable(value = "n2") String n2) throws Exception{
		return math.multiply(NumberConvert.convertToDouble(n1), NumberConvert.convertToDouble(n2));
	}

	@RequestMapping("/dividir/{n1}/{n2}")
	public double dividir(@PathVariable (value = "n1") String n1, 
						   @PathVariable(value = "n2") String n2) throws Exception{
		return math.dividir(NumberConvert.convertToDouble(n1), NumberConvert.convertToDouble(n2));
	}
	
	@RequestMapping("/raizquadrada/{n1}")
	public double raizQuadrada(@PathVariable (value = "n1") String n1) throws Exception{
		return math.raizQuadrada(NumberConvert.convertToDouble(n1));
	}

	@RequestMapping("/raizcubica/{n1}")
	public double raizCubica(@PathVariable (value = "n1") String n1) throws Exception{
		return math.raizCubica(NumberConvert.convertToDouble(n1));
	}

	@RequestMapping("/potencia/{n1}/{n2}")
	public double potencia(@PathVariable (value = "n1") String n1, 
						   @PathVariable(value = "n2") String n2) throws Exception{
		return math.potencia(NumberConvert.convertToDouble(n1), NumberConvert.convertToDouble(n2));
	}

	@RequestMapping("/binario/{n1}")
	public String binario(@PathVariable (value = "n1") String n1) throws Exception{
		return math.binario(NumberConvert.convertToInteger(n1));
	}

	@RequestMapping("/fatorial/{n1}")
	public BigInteger fatorial(@PathVariable (value = "n1") String n1) throws Exception{
		return math.fatorial(NumberConvert.convertToInteger(n1));
	}

	@RequestMapping("/pi")
	public double pi(){
		return math.pi();
	}
	
}
