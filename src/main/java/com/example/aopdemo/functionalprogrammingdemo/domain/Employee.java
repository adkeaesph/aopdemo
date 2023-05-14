package com.example.aopdemo.functionalprogrammingdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Employee {
  private int id;
  private String name;
  private double salary;
  private Gender gender;
}


