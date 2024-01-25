package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>();

		System.out.print("Quantos funcionário vão ser cadastrados? ");
		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {

			System.out.println();
			System.out.println("Funcionário #" + (i + 1) + ":");
			System.out.print("Id: ");
			Integer id = sc.nextInt();
			while (hasId(list, id)) {
				System.out.println("Id já utilizado, tente novamente: ");
				id = sc.nextInt();
			}

			System.out.print("Nome: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.print("Salário: ");
			Double salary = sc.nextDouble();

			Employee emp = new Employee(id, name, salary);

			list.add(emp);
		}

		System.out.println();
		System.out.print("Digite a id do empregado que vai ter o salário aumentado: ");
		int idsalary = sc.nextInt();

		Employee emp = list.stream().filter(x -> x.getId() == idsalary).findFirst().orElse(null);

		if (emp == null) {
			System.out.println("Esse Id não existe!");
		} else {
			System.out.print("Digite a porcentagem: ");
			double percent = sc.nextDouble();

			emp.increaseSalary(percent);
		}

		System.out.println();
		System.out.println("Lista de funcionários:");
		for (Employee e : list) {
			System.out.println(e);
		}

		sc.close();
	}

	public static Integer position(List<Employee> list, int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}

	public static boolean hasId(List<Employee> list, int id) {
		Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null;
	}
}