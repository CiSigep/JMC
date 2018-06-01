package challenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import objects.Utilities;

/* Challenge Ten
 * Write a parameterized version of the removePairs test and test with the values given.
 */

// Challenge Ten
@RunWith(Parameterized.class)
public class TestUtilitiesChallengeParameterized {
	
	@Parameters
	public static Collection<Object[]> parameters(){
		return Arrays.asList(new Object[][] {
			{ "ABCDEFF", "ABCDEF" },
			{ "AB88EFFG", "AB8EFG" },
			{ "112233445566", "123456" },
			{ "ZYZQQB", "ZYZQB" },
			{ "A", "A" }
		});
	}
	
	private String input;
	private String output;
	
	private Utilities u;
	
	@Before
	public void setup() {
		this.u = new Utilities();
	}
	
	public TestUtilitiesChallengeParameterized(String input, String output) {
		this.input = input;
		this.output = output;
	}
	
	@Test
	public void testRemovePairs() {
		assertEquals("The Strings do not match", output, u.removePairs(input));
	}

}
