package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Streams {
	
	public static void main(String[] args) {
		List<String> lines = Arrays.asList("Tu Fui, Ego Eris.",
                "Memento Mori.",
                "Elapsam semel occasionem non ipse potest Iuppiter reprehendere.",
                "Plaudite. Acta est fabula.");
		
		lines.stream().map(String::toUpperCase).filter(s -> s.length() > 15).forEach(System.out::println);
		
		Stream<String> phrases = Stream.of("Tu Fui, Ego Eris.", "Memento Mori.");
		Stream<String> oPhrases = Stream.of("Elapsam semel occasionem non ipse potest Iuppiter reprehendere.",
                "Plaudite. Acta est fabula.");
		
		Stream<String> concatStream = Stream.concat(phrases, oPhrases);
		
		System.out.println(concatStream.count());
		
		Employee e1 = new Employee("John", 25);
		Employee e2 = new Employee("James", 30);
		Employee e3 = new Employee("Jack", 40);
		Employee e4 = new Employee("Josh", 50);
		
		Department d = new Department("Human Resources");
		
		d.addEmployee(e1);
		d.addEmployee(e2);
		d.addEmployee(e3);
		
		Department d2 = new Department("Accounting");
		d2.addEmployee(e4);
		
		List<Department> departments = new ArrayList<Department>();
		
		departments.add(d);
		departments.add(d2);
		
		departments.stream().flatMap(department -> department.getEmployees().stream()).forEach(System.out::println);
		
		
	}

}
