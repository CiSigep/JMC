package challenge;

import java.util.function.Function;

/* Challenge Two
 * Write the following method as a lambda Expression
 * public static String everySecondChar(String src) {
 *    StringBuilder returnVal = new StringBuilder();
 *    for(int i = 0; i < src.length(); i++)
 *       if(i % 2 == 1)
 *          returnVal.append(src.charAt(i));
 *          
 *    return returnVal.toString();
 * }
 * 
 * Challenge Three
 * Write the code that will execute the function we just wrote.
 * 
 * */

public class ChallengeTwoThree {

	public static void main(String[] args) {
		Function<String, String> everySecondChar = src -> {
			StringBuilder returnVal = new StringBuilder();
		    for(int i = 0; i < src.length(); i++)
		       if(i % 2 == 1)
		          returnVal.append(src.charAt(i));
		          
		    return returnVal.toString();
		};
		
		System.out.println(everySecondChar.apply("Well hello there!"));
		

	}

}
