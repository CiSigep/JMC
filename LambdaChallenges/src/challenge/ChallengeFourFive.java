package challenge;

import java.util.function.Function;

/* Challenge Four
 * Write a function that takes the function given as a parameter and executes it. Don't hardcode
 * the string within the method if possible.
 * 
 * Challenge Five
 * Call the function using the lambda expression version of the function and print the result.
 * */

public class ChallengeFourFive {
	public static void main(String[] args) {
		/*Function<String, String> everySecondChar = src -> {
			StringBuilder returnVal = new StringBuilder();
		    for(int i = 0; i < src.length(); i++)
		       if(i % 2 == 1)
		          returnVal.append(src.charAt(i));
		          
		    return returnVal.toString();
		};*/
		
		System.out.println(stringManipulate("Well hello there!", src -> {
			StringBuilder returnVal = new StringBuilder();
		    for(int i = 0; i < src.length(); i++)
		       if(i % 2 == 1)
		          returnVal.append(src.charAt(i));
		          
		    return returnVal.toString();
		}));
	}
	
	
	private static String stringManipulate(String src, Function<String, String> func) {
		return func.apply(src);
	}
}
