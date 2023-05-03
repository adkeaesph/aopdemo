package com.example.aopdemo.service;

import com.example.aopdemo.annotations.LogMethodName;
import com.example.aopdemo.annotations.MethodRunTime;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogicService {

  @LogMethodName
  @MethodRunTime
  public int findNthFibonacciNumberRecursively(int n) {
    if(n==0 || n==1)
      return n;

    return findNthFibonacciNumberRecursively(n-1) + findNthFibonacciNumberRecursively(n-2);
  }

  @LogMethodName
  @MethodRunTime
  public int findNthFibonacciNumberIteratively(int n) {
    if(n==0 || n==1)
      return n;

    int first = 0;
    int second = 1;

    for(int i=2; i<=n; i++) {
      int temp = first + second;
      first = second;
      second = temp;
    }
    return second;
  }

}
