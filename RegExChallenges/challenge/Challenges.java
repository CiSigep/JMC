package challenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Challenge One
 * Write the string literal RegEx to match the first string. Use String.matches() to test.
 * 
 * Challenge Two
 * Write a RegEx that matches the first string AND the second String.
 * 
 * Challenge Three
 * Create a matcher to test the RegEx instead for the given pattern.
 * 
 * Challenge Four
 * Replace all occurrences of whitespace in the given string with underscores.
 * 
 * Challenge Five
 * Write a RegEx to match the given string in its entirety. Use String.matches() to test. 
 * 
 * Challenge Six
 * Write a RegEx to match ONLY Challenge Five's string.
 * 
 * Challenge Seven
 * Write a RegEx to match the type of string described: Starts with a series of letters, followed by a period,
 * followed by a series of numbers.
 * Use the strings given to test.
 * 
 * Challenge Eight
 * Modify Challenge Seven's RegEx to use a group, so all the digits can be printed out. Use the
 * String provided to test.
 * 
 * Challenge Nine
 * Modify Challenge Seven and Eight'ss RegEx to fit the string given.
 * 
 * Challenge Ten
 * Same as Challenge Nine except print the indices of the digits.
 * 
 * Challenge Eleven
 * Extract what is within the curly braces in the String given.
 * 
 * Challenge Twelve
 * Write a RegEx to match the basic 5 digit US zipcode.
 * 
 * Challenge Thirteen
 * Modify Challenge Twelve's RegEx to match the next four numbers on a US zipcode
 * 
 * Challenge Fourteen 
 * Write a RegEx to match both the 5 digit and 5 + 4 digit US zipcodes
 * */

public class Challenges {
	
	public static void main(String[] args) {
		// Challenge One
		String challengeOneString = "I want a bike.";
		
		System.out.println("One: " + challengeOneString.matches("I want a bike."));
		
		// Challenge Two
		String challengeTwoString = "I want a ball.";
		String twoPattern = "I want a (bike|ball).";
		
		System.out.println("Two: " + challengeOneString.matches(twoPattern) + " " + challengeTwoString.matches(twoPattern));
		
		// Challenge Three
		String threePattern = "I want a \\w+.";
		Pattern p = Pattern.compile(threePattern);
		Matcher m31 = p.matcher(challengeOneString);
		Matcher m32 = p.matcher(challengeTwoString);
		
		System.out.println("Three: " + m31.matches() + " " + m32.matches());
		
		// Challenge Four
		String challengeFourString = "Replace all blanks with underscores.";
		System.out.println("Four: " + challengeFourString.replaceAll("\\s", "_"));
		
		// Challenge Five & Six
		String challengeFiveString = "aaabccccccccdddefffg";
		System.out.println("Five & Six: " + challengeFiveString.matches("^a{3}bc{8}d{3}ef{3}g$"));
		
		// Challenge Seven
		String challengeSevenString1 = "abcd.135";
		String challengeSevenString2 = "kijsl.22";
		String challengeSevenString3 = "f5.12a";
		
		String challengeSevenPattern = "^[a-zA-Z]+\\.\\d+$";
		
		System.out.println("Seven: " + challengeSevenString1.matches(challengeSevenPattern) + " " +
				           challengeSevenString2.matches(challengeSevenPattern) + " " + challengeSevenString3.matches(challengeSevenPattern));
		
		// Challenge Eight
		String challengeEightString = "abcd.135uvqz.7tzik.999";
		Pattern p8 = Pattern.compile("([a-zA-Z]+)(\\.)(\\d+)");
		Matcher m8 = p8.matcher(challengeEightString);
		
		System.out.print("Eight: ");
		while(m8.find()) {
			System.out.print(m8.group(3) + " ");
		}
		System.out.println();
		
		// Challenge Nine
		String challengeNineString = "abcd.135\tuvqz.7\ttzik.999\n";
		Pattern p9 = Pattern.compile("[a-zA-Z]+\\.(\\d+)\\s");
		Matcher m9 = p9.matcher(challengeNineString);
		
		System.out.print("Nine: ");
		while(m9.find()) {
			System.out.print(m9.group(1) + " ");
		}
		System.out.println();
		
		// Challenge Ten
		Matcher m10 = p9.matcher(challengeNineString);
		
		System.out.println("Ten\n---");
		while(m10.find()) {
			System.out.println("Start: " + m10.start(1) + " End: " + (m10.end(1) - 1));
		}
		System.out.println("---");
		
		// Challenge Eleven
		String challengeElevenString = "{0, 2}, {0, 5}, {1, 3}, {2, 4}";
		// If you want separate.
		Pattern p11 = Pattern.compile("\\{(\\d+), (\\d+)\\}");
		// If you want the whole thing.
		//Pattern p11 = Pattern.compile("\\{(.+?)\\}");
		Matcher m11 = p11.matcher(challengeElevenString);
		
		System.out.println("Eleven\n---");
		while(m11.find()) {
			// If you want separate.
			System.out.println("X: " + m11.group(1) + " Y: " + m11.group(2));
			// If you want the whole thing
			//System.out.println("Values: " + m11.group(1));
		}
		System.out.println("---");
		
		// Challenge Twelve
		String challengeTwelveString = "11111";
		System.out.println("Twelve: " + challengeTwelveString.matches("^\\d{5}$"));
		
		// Challenge Thirteen
		String challenge13String = "11111-1111";
		System.out.println("Thirteen: " + challenge13String.matches("^\\d{5}-\\d{4}$"));
		
		// Challenge Fourteen
		String challenge14Pattern = "^\\d{5}(-\\d{4})?$";
		
		System.out.println("Fourteen: " + challengeTwelveString.matches(challenge14Pattern) + " " + challenge13String.matches(challenge14Pattern));
		
	}
}
