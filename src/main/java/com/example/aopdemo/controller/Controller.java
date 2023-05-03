package com.example.aopdemo.controller;

import com.example.aopdemo.service.BusinessLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @Autowired
  BusinessLogicService businessLogicService;

  @GetMapping(path = "api/fibonacci/v1/{num}")
  public int fibonacciVersion1(@PathVariable int num) {
    return businessLogicService.findNthFibonacciNumberRecursively(num);
  }

  @GetMapping(path = "api/fibonacci/v2/{num}")
  public int fibonacciVersion2(@PathVariable int num) {
    return businessLogicService.findNthFibonacciNumberIteratively(num);
  }

}
