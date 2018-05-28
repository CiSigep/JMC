package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConsumerAndPredicate {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Jim", 22));
		employees.add(new Employee("John", 23));
		employees.add(new Employee("Josh", 24));
		employees.add(new Employee("Jack", 37));
		employees.add(new Employee("James", 40));
		employees.add(new Employee("Joseph", 21));
		
		printByAge(employees, "Employees under 25", e -> e.getAge() < 25);
		printByAge(employees, "\nEmployees over 30", new Predicate<Employee>() {

			@Override
			public boolean test(Employee t) {
				return t.getAge() > 30;
			}
		});

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
