package sample;

import java.util.Arrays;
import java.util.List;

public class Streams {
	
	public static void main(String[] args) {
		List<String> lines = Arrays.asList("Tu Fui, Ego Eris.",
                "Memento Mori.",
                "Elapsam semel occasionem non ipse potest Iuppiter reprehendere.",
                "Plaudite. Acta est fabula.");
		
		lines.stream().filter(s -> s.length() > 15).forEach(System.out::println);
	}

}
