import java.util.Scanner;


// Scope challenge from Tim Buchalka's Java Master Course
// Goal was to only use x as variable and function name(except for main)
public class Main {

	public static void main(String[] args) {
		Scanner x = new Scanner(System.in);

		x(x.nextInt());
		x.close();
	}
	
	private static void x(int x){
		System.out.println(x);
		System.out.println(x * 2);
		System.out.println(x * 3);
		System.out.println(x * 4);
		System.out.println(x * 5);
		System.out.println(x * 6);
		System.out.println(x * 7);
		System.out.println(x * 8);
		System.out.println(x * 9);
		System.out.println(x * 10);
		System.out.println(x * 11);
		System.out.println(x * 12);
	}
}
