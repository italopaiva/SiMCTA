package datatype;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datatype.Date;
import exception.DateException;

public class DateTest {
	
	private Date validDate;
	private Date invalidDate;
	
	@Before
	public void setUp() throws DateException{
		
		validDate = new Date(4,8,1995);
	}
	
	/** Valid entries
	 * @throws DateException
	 */
	@Test
	public void testYearValid() throws DateException {
		
		assertEquals(new Integer(1995), validDate.getYear());
	}
	
	@Test
	public void testMonthValid() throws DateException {
		assertEquals(new Integer(8), validDate.getMonth());
	}
	
	@Test
	public void testDayValid() throws DateException {
		
		assertEquals(new Integer(4), validDate.getDay());
	}
	
	@Test
	public void testValidDay28_2_1995() throws DateException {
		validDate = new Date(28, 2, 1995);
		assertEquals(new Integer(28), validDate.getDay());
	}
	
	
	@Test
	public void testValidDay29_2_1996() throws DateException {
		validDate = new Date(29, 2, 1996);
		assertEquals(new Integer(29), validDate.getDay());
	}
	
	/** Invalid entries
	 * @throws DateException
	 */
	@Test (expected = DateException.class)
	public void testDayWithValueLessThanMin() throws DateException {
		invalidDate = new Date(0,8,1995);
	}
	
	@Test (expected = DateException.class)
	public void testInvalidDay32_8() throws DateException {
		invalidDate = new Date(32,8,1995);
	}
	
	@Test (expected = DateException.class)
	public void testInvalidDay31_6() throws DateException {
		invalidDate = new Date(31,6,1995);
	}
	
	@Test (expected = DateException.class)
	public void testInvalidDay30_2() throws DateException {
		invalidDate = new Date(30,2,1995);
	}
	
	@Test (expected = DateException.class)
	public void testInvalidDay29_2() throws DateException {
		invalidDate = new Date(29,2,1995);
	}
	
	@Test (expected = DateException.class)
	public void testMonthInvalid() throws DateException {
		invalidDate = new Date(1,0,1995);
	}
	


}
