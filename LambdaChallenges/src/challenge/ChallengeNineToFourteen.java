package challenge;

import java.util.Arrays;
import java.util.List;

/* Challenge Eight was a bunch of questions asking if an interface was a functional interface.
 * 
 * Challenge Nine
 * Given the following list, capitalize the first letter, sort them, and print them. Use lambdas where applicable
 * 
 * Challenge Ten
 * Change code so it only uses method references
 * 
 * Challenge Eleven
 * Use a stream to do the above.
 * 
 * Challenge Twelve
 * Print the number of names starting with 'A'
 * 
 * Challenge Thirteen
 * What would peek() do in a chain without a terminal function. Answer: Nothing, executes lazily.
 * 
 * Challenge Fourteen
 * Add a terminal operation to make peek() work
 * 
 * */

public class ChallengeNineToFourteen {
	
	
	 public static void main(String[] args) {
		 List<String> names = Arrays.asList("Amelia", "Olivia", "emily", "Isla", "Ava",
                 "oliver", "Jack", "Charlie", "harry", "Jacob");
		 
		 //names.stream().map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)).sorted().forEach(System.out::println);
		 //names.stream().map(ChallengeNine::capitalizeFirst).sorted().forEach(System.out::println);
		 long count = names.stream().map(ChallengeNineToFourteen::capitalizeFirst).peek(System.out::println)
				           .sorted().filter(s -> s.startsWith("A")).count();
		 System.out.println(count);
	 }
	 
	 private static String capitalizeFirst(String s) {
		 return s.substring(0, 1).toUpperCase() + s.substring(1);
	 }
}
