package com.example.aopdemo.immutability;

public class Driver {
  public static void main(String[] args) {
    Address address = new Address("Bengaluru", "Karnataka");
    Person person = new Person(1, "Ram", address);
    System.out.println(person);
    System.out.println(address);

    //trying to change address using an old reference
    address.setCity("Bangalore Urban");
    System.out.println(person);
    System.out.println(address);

    //trying to change through setter of inside object via getter of Person
    Address fetchedAddress = person.getAddress();
    fetchedAddress.setCity("Bangalore");
    System.out.println(person);
    System.out.println(fetchedAddress);
  }
}
