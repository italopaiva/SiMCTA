package datatype;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.CPFException;

public class CPFTest {

private CPF cpf;
	
	@Test
	public void testwithValidCPF(){
		
		String randomValidCPF = "51464638403"; 
		try {
			cpf = new CPF(randomValidCPF);
			assertEquals(randomValidCPF, cpf.getCpf());
		} catch (CPFException e){
			fail("Should create the given CPF.");
		}
	}
}
