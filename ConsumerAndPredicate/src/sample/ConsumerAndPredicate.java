package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConsumerAndPredicate {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Jim Jam", 22));
		employees.add(new Employee("John June", 23));
		employees.add(new Employee("Josh Bosh", 24));
		employees.add(new Employee("Jack Back", 37));
		employees.add(new Employee("James Thames", 40));
		employees.add(new Employee("Joseph Boseph", 27));
		
		printByAge(employees, "Employees under 25", e -> e.getAge() < 25);
		printByAge(employees, "\nEmployees over 30", new Predicate<Employee>() {

			@Override
			public boolean test(Employee t) {
				return t.getAge() > 30;
			}
		});
		
		
		Predicate<Employee> overOrEqualTo25 = e -> e.getAge() >= 25;
		Predicate<Employee> underOrEqualTo30 = e -> e.getAge() <= 30;
		
		Predicate<Employee> chainedPredicate = overOrEqualTo25.and(underOrEqualTo30);
		
		printByAge(employees, "\nEmployees between 25 and 30", chainedPredicate);
		
		//Function<Employee, String> getLastName = (Employee e) -> e.getName().substring(e.getName().indexOf(' ') + 1);
		Function<Employee, String> getLastName = new Function<Employee, String>() {

			@Override
			public String apply(Employee e) {
				return e.getName().substring(e.getName().indexOf(' ') + 1);
			}
		};
		
		System.out.println("\n---------");
		for(Employee em : employees)
			System.out.println(getLastName.apply(em));
		
		
		Function<String, String> toUpper = s -> s.toUpperCase();
		
		// Function chaining
		System.out.println("\n-----------");
		for(Employee e : employees)
			System.out.println(getLastName.andThen(toUpper).apply(e));

	}
	
	
	private static void printByAge(List<Employee> em, String ageText, Predicate<Employee> condition) {
		System.out.println(ageText);
		System.out.println("--------------");
		em.forEach(e -> {
			if(condition.test(e))
				System.out.println(e.getName() + " " + e.getAge());
		});
/*		em.forEach(new Consumer<Employee>() {

			@Override
			public void accept(Employee t) {
				if(condition.test(t))
					System.out.println(t.getName() + " " + t.getAge());
			}
		});*/
	}

}
