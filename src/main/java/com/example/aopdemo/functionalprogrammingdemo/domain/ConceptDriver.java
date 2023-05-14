package com.example.aopdemo.functionalprogrammingdemo.domain;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class ConceptDriver {
  public static void main(String[] args) {
    List<Employee> employees = List.of(
        new Employee(1, "Sardar Khan", 10000, Gender.MALE),
        new Employee(2, "Ramadhir Singh", 75000, Gender.MALE),
        new Employee(3, "Faizal Khan", 25000, Gender.MALE),
        new Employee(4, "Rita Aggarwal", 45000, Gender.FEMALE),
        new Employee(5, "Nazma Khatoon", 5000, Gender.FEMALE),
        new Employee(6, "Anandi Singh", 35000, Gender.FEMALE)
    );

    //let's get employees with above 20000 salary
    //Predicate is a condition which accepts one argument
    //Predicate is a functional interface whose functional method is 'test(Object)'
    System.out.println("//Predicate test");
    Predicate<Employee> salaryPredicate = employee -> employee.getSalary() > 20000;
    Predicate<Employee> genderPredicate = employee -> employee.getGender().equals(Gender.FEMALE);
    System.out.println(genderPredicate.test(employees.get(0)));
    System.out.println(genderPredicate.test(employees.get(3)));
    employees.stream()
        .filter(salaryPredicate.and(genderPredicate))
        .forEach(System.out::println);

    //BiPredicate is the condition which accepts two arguments
    //BiPredicate is a functional interface whose functional method is 'test(Object)'
    System.out.println("//BiPredicate test");
    BiPredicate<Employee, Double> dynamicSalaryPredicate = (employee, salaryThreshold) ->
        employee.getSalary() > salaryThreshold;
    System.out.println(dynamicSalaryPredicate.test(employees.get(0), 2000.0));

    //Function - equivalent to a method that requires one argument and gives a result
    //Function is a functional interface which has the method apply
    System.out.println("//Function test");
    Function<Integer, Employee> getEmployeeById = id -> {
      for(Employee employee: employees) {
        if(employee.getId() == id)
          return employee;
      }
      return null;
    };
    System.out.println(getEmployeeById.apply(2));

    //BiFunction - equivalent to a method that requires two arguments and gives a result
    //BiFunction is a functional interface which has the method apply
    System.out.println("//BiFunction test");
    BiFunction<Employee, Double, Double> getSalaryHike =
        (employee, incrementPercent) -> employee.getSalary() * (100+incrementPercent)/100;
    Employee e1 = getEmployeeById.apply(1);
    System.out.println("Salary hike of "
        + e1.getName()
        + " : "
        + getSalaryHike.apply(e1, 200.0));

    //Consumer - equivalent of void method which requires one argument
    //Consumer is a functional interface which has the method accept
    System.out.println("//Consumer test");
    Consumer<Employee> greetEmployee = employee ->
        System.out.println("Good Morning "+employee.getName().split("\\s+")[0]+ " !!");
    greetEmployee.accept(employees.get(0));

    //BiConsumer - equivalent of void method which requires two arguments
    //BiConsumer is a functional interface which has the method accept
    System.out.println("//BiConsumer test");
    BiConsumer<Employee, Employee> pairEmployees = (employee, employee2) -> {
      System.out.println("Good Morning "+employee.getName().split("\\s+")[0]+ " !!");
      System.out.println("Good Morning "+employee2.getName().split("\\s+")[0]+ " !!");
      System.out.println("You both have been chosen for an onsite opportunity");
    };
    pairEmployees.accept(employees.get(0), employees.get(4));

    //Supplier takes no arguments, and returns a result
    //It is a functional interface which has the method get()
    System.out.println("//Supplier test");
    Supplier<List<Employee>> getAllFemaleEmployees = () ->
        employees.stream()
            .filter(employee -> employee.getGender()
                .equals(Gender.FEMALE)).collect(Collectors.toList());
    getAllFemaleEmployees.get().forEach(System.out::println);
  }
}
