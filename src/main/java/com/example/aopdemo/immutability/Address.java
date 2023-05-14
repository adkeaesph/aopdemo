package com.example.aopdemo.immutability;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  private String city;
  private String state;
}
