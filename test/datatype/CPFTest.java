package datatype;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.CPFException;

public class CPFTest {

private CPF cpf;
private static String randomValidCPF = "51464638403"; 
	
	@Test
	public void testwithValidCPF(){
		
		try {
			cpf = new CPF(randomValidCPF);
			assertEquals(randomValidCPF, cpf.getCpf());
		} catch (CPFException e){
			fail("Should create the given CPF.");
		}
	}
	
	@Test(expected=CPFException.class)
	public void testwithEmptyCPF() throws CPFException{
		 
		cpf = new CPF("");
	}
	
	@Test(expected=CPFException.class)
	public void testwithNullCPF() throws CPFException{
		
		cpf = new CPF(null);
	}
	
	@Test(expected=CPFException.class)
	public void testwithCPF10Length() throws CPFException{
		
		String randomInvalidCPF = "5146463840"; 
		cpf = new CPF(randomInvalidCPF);
	}
	
	@Test(expected=CPFException.class)
	public void testwithCPF12Length() throws CPFException{
		
		String randomInvalidCPF = "514646384033"; 
		cpf = new CPF(randomInvalidCPF);
	}
	
	@Test(expected=CPFException.class)
	public void testwithCPFRandomInvalidLength() throws CPFException{
		
		String randomInvalidCPF = "5146463"; 
		cpf = new CPF(randomInvalidCPF);
	}
	
	@Test(expected=CPFException.class)
	public void testwithInvalidCPF() throws CPFException{
		
		String randomInvalidCPF = "02345678910"; 
		cpf = new CPF(randomInvalidCPF);
	}
	
	@Test(expected=CPFException.class)
	public void testInvalidCPFWithLetters() throws CPFException{
		
		String randomInvalidCPF = "0asfad13478"; 
		cpf = new CPF(randomInvalidCPF);
	}
	
	@Test
	public void testGetFormattedCPF(){
		
		try{
			cpf = new CPF(randomValidCPF);
			assertEquals("514.646.384-03", cpf.getFormattedCpf());
		}catch(CPFException e){
			fail("Should not throw exception");
		}
	}
}
