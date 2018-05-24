package lambda;

/*import java.util.ArrayList;
import java.util.Collections;
import java.util.List;*/

public class Lambdas {

	public static void main(String[] args) { // Demonstrations of Lambda usage
/*		new Thread(() -> {
			System.out.println("Hi from lambda runnable");
		}).start();
		
		Employee e0 = new Employee("John Doe", 30);
		Employee e1 = new Employee("Jane Smith", 22);
		Employee e2 = new Employee("Max Matthews", 23);
		
		List<Employee> employees = new ArrayList<>();
		
		employees.add(e0);
		employees.add(e1);
		employees.add(e2);
		
		Collections.sort(employees, (Employee em1, Employee em2) -> {
			return em1.getName().compareTo(em2.getName());
		});
		
		for(Employee e : employees)
			System.out.println(e.getName() + " " + e.getAge());
		
		UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
		
		String result = doStringThings(uc, "test1", "test2");
		
		System.out.println("Result: " + result);*/
		
		OneMoreClass omc = new OneMoreClass();
		
		String s = omc.doSomething();
		
		System.out.println(s);
	}
	
	public final static String doStringThings(UpperConcat uc, String s1, String s2) {
		return uc.UpperAndConcat(s1, s2);
	}
}

class Employee {
	
	private String name;
	private int age;
	
	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}

interface UpperConcat {
	public String UpperAndConcat(String s1, String s2);
}

class OneMoreClass {
	public String doSomething() {
		System.out.println("Class name of object: " + getClass().getSimpleName());
		return Lambdas.doStringThings((s1, s2) -> {
				System.out.println("Lambda expression name: " + getClass().getSimpleName());
				return s1.toUpperCase() + s2.toUpperCase();
			
		}, "Test1", "test2");
	}
}
