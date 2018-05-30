package challenge;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/* Challenge Six
 * Write a lambda expression that maps to the Supplier interface. 
 * 
 * Challenge Seven
 * Take the result of the Supplier and print it.
 * */


public class ChallengeSixSeven {

	public static void main(String[] args) {
		List<String> phrases = Arrays.asList("Tu Fui, Ego Eris.",
                "Memento Mori.",
                "Elapsam semel occasionem non ipse potest Iuppiter reprehendere.",
                "Plaudite. Acta est fabula.");
		Random r = new Random();
		
		Supplier<String> stringSupplier = () -> phrases.get(r.nextInt(phrases.size()));
		
		System.out.println(stringSupplier.get());
	}
}
