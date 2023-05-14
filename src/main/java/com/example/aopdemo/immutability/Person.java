package com.example.aopdemo.immutability;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Person {
  final private int id;
  final private String name;
  final private Address address;

  public Person(int id, String name, Address address) {
    this.id = id;
    this.name = name;
    Address newAddress = new Address();
    newAddress.setCity(address.getCity());
    newAddress.setState(address.getState());
    this.address = newAddress;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Address getAddress() {
    Address address = new Address();
    address.setCity(this.address.getCity());
    address.setState(this.address.getState());
    return address;
  }
}
