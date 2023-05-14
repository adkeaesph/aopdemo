package com.example.aopdemo.functionalprogrammingdemo;

import com.example.aopdemo.functionalprogrammingdemo.domain.Employee;
import com.example.aopdemo.functionalprogrammingdemo.domain.Gender;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class MainDriver {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(4);
    list.add(7);
    list.add(4);
    list.add(3);
    display(list, "Elements of list:");
    System.out.println("Sum = "+getSum(list));
    System.out.println("Sum using accumulator = "+getSumUsingAccumulator(list));

    display(getOddNumbers(list), "Odd numbers in the list:");

    display(getEvenNumbers(list), "Even numbers in the list:");

    display(getDistinctNumbers(list), "Distinct numbers in the list:");

    display(getNonRepeatableNumbers(list), "Numbers that were never repeated:");

    System.out.println("Sum of squares of numbers = "+getSumOfSquaresOfNumbers(list));
    System.out.println("Sum of squares of odd numbers = "+getSumOfSquaresOfOddNumbers(list));
    System.out.println("Sum of squares of even numbers = "+getSumOfSquaresOfEvenNumbers(list));

    List<Employee> employees = getDummyListOfEmployees();
    List<String> names = employees.stream().map(Employee::getName).toList();
    display(names, "Names of Employees: ");
    Map<Integer, Employee> map = employees.stream().
        collect(Collectors.toMap(Employee::getId, Function.identity()));
    display(map, "Id to Employee map:");
    Map<Integer, String> map2 = employees.stream().
        collect(Collectors.toMap(Employee::getId, Employee::getName));
    display(map2, "Id to Name map:");

    Map<Integer, Double> map3 = employees.stream()
        .collect(Collectors.toMap(Employee::getId, Employee::getSalary));
    map3.entrySet().forEach(entry -> entry.setValue(entry.getValue()*1.2));
    display(map3, "Id to Salary with 20% hike map:");

  }

  private static <T> void display(List<T> list, String messageHeader) {
    System.out.println(messageHeader);
    list.forEach(System.out::println);
  }

  private static <K,V> void display(Map<K,V> map, String messageHeader) {
    System.out.println(messageHeader);
    map.forEach((key, value) -> System.out.println(key + " -> " + value));
  }

  private static int getSum(List<Integer> list) {
    return list.stream().mapToInt(element -> element).sum();
  }

  private static int getSumUsingAccumulator(List<Integer> list) {
    return list.stream().reduce(0, (subtotal, element) -> subtotal + element);
  }

  private static List<Integer> getOddNumbers(List<Integer> list) {
    return list.stream().filter(element -> element%2!=0).collect(Collectors.toList());
  }

  private static List<Integer> getEvenNumbers(List<Integer> list) {
    return list.stream().filter(element -> element%2==0).collect(Collectors.toList());
  }

  private static int getSumOfSquaresOfNumbers(List<Integer> list) {
    return list.stream().mapToInt(element-> element*element).sum();
  }

  private static int getSumOfSquaresOfOddNumbers(List<Integer> list) {
    return list.stream().filter(element -> element%2!=0).mapToInt(element-> element*element).sum();
  }

  private static int getSumOfSquaresOfEvenNumbers(List<Integer> list) {
    return list.stream().filter(element -> element%2==0).mapToInt(element-> element*element).sum();
  }

  private static List<Integer> getDistinctNumbers(List<Integer> list) {
    return list.stream().distinct().collect(Collectors.toList());
  }

  private static List<Integer> getNonRepeatableNumbers(List<Integer> list) {
    return list.stream().collect(groupingBy(element -> element, counting()))
        .entrySet().stream()
        .filter(element -> (element.getValue() == 1))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  private static List<Employee> getDummyListOfEmployees() {
    return List.of(
        new Employee(1, "Akash", 10000.0, Gender.MALE),
        new Employee(2, "Sky", 25000.0, Gender.MALE),
        new Employee(3, "Aadesh", 21000.0, Gender.MALE));
  }

}
