package challenge;

/* Challenge One
 * Write this anonymous Runnable as a lambda expression.
 * Runnable r = new Runnable () {
 * 
 *    @Override
 *    public void run () {
 *       String myString = "Let's split this up into an array";
 *       String[] parts = myString.split(" ");
 *       for(String part : parts)
 *          System.out.println(part);  
 *    }
 * }
 * */


public class ChallengeOne {

	public static void main(String[] args) {
		Runnable r = () -> {
			String myString = "Let's split this up into an array";
			String[] parts = myString.split(" ");
			for(String part : parts)
				System.out.println(part);
		};
		
		new Thread(r).start();
	}
}
