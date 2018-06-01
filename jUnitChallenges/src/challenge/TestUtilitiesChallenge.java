package challenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import objects.Utilities;

/* Challenge One
 * Create a test class for the Utilities Class methods. Don't write any actual test methods, just set the tests to fail.
 * 
 * Challenge Two
 * Add code to test the removePairsMethod.
 * 
 * Challenge Three 
 * Think up tests that can test another part of the removePairs code.
 * 
 * Challenge Four
 * Write a test for the everyNthChar method.
 * 
 * Challenge Five 
 * Add a test for the everyNthChar method where n is greater than the size of the array.
 * 
 * Challenge Six
 * Write two tests checking both paths of the nullIfOddLength method.
 * 
 * Challenge Seven
 * Test the output of the converter method.
 * 
 * Challenge Eight
 * Write a test for the converter method that uses 0 as b and expects an ArithmeticException.
 * 
 * Challenge Nine
 * Replace the repetitive instantiation of the Utilities class for tests.
 */


public class TestUtilitiesChallenge {
	
	// Challenge Nine
	private Utilities u;
	
	@Before
	public void setup() {
		this.u = new Utilities();
	}

	@Test
	public void testEveryNthChar() throws Exception {
		//fail("Not yet implemented"); // Challenge One
		
		// Challenge Four
		//Utilities u = new Utilities();
		
		assertArrayEquals(new char[] {'e', 'l'}, u.everyNthChar(new char[] {'h', 'e', 'l', 'l', 'o'}, 2));
	}
	
	// Challenge Five
	@Test
	public void testEveryNthChar_NLargerThanArraySize() throws Exception {

		//Utilities u = new Utilities();
		
		assertArrayEquals(new char[] {'h', 'i'}, u.everyNthChar(new char[] {'h', 'i'}, 3));
	}

	@Test
	public void testRemovePairs() throws Exception {
		//fail("Not yet implemented"); // Challenge One
		
		//Utilities u = new Utilities();
		
		// Challenge Two
		assertEquals("The Strings do not match.", "ABCDEF", u.removePairs("AABCDDEFF"));
		assertEquals("The Strings do not match.", "ABCABDEF", u.removePairs("ABCCABDEEF"));
	}

	// Challenge Three
	@Test
	public void testRemovePairs_singleCharString() throws Exception { 
		//Utilities u = new Utilities();
		
		String actual = u.removePairs("A");
		
		assertEquals("The Strings do not match.", "A", actual);
	}
	
	// Challenge Three
	@Test
	public void testRemovePairs_Null() throws Exception {
		//Utilities u = new Utilities();
		
		String actual = u.removePairs(null);
		
		assertNull(actual);
	}
	
	// Challenge Seven
	@Test
	public void testConverter() throws Exception {
		//fail("Not yet implemented"); // Challenge One
		
		//Utilities u = new Utilities();
		
		assertEquals("The numbers are not equal", 300, u.converter(10, 5));
	}

	// Challenge Eight
	@Test(expected = ArithmeticException.class)
	public void testConverter_DivideByZero() throws Exception {
		//Utilities u = new Utilities();
		
		u.converter(10, 0);
	}
	
	// Challenge Six
	@Test
	public void testNullIfOddLength_Even() throws Exception {
		//fail("Not yet implemented"); // Challenge One
		
		//Utilities u = new Utilities();
		
		assertNotNull(u.nullIfOddLength("ABCD"));
	}
	
	// Challenge Six
	@Test
	public void testNullIfOddLength_Odd() throws Exception {
		//fail("Not yet implemented"); // Challenge One
		
		//Utilities u = new Utilities();
		
		assertNull(u.nullIfOddLength("ABC"));
	}

}
