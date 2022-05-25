package com.wx.aspect;

import com.wx.annotation.MethodAnnotation;
import org.springframework.stereotype.Component;

@Component
public class DemoComponent {
  @MethodAnnotation
  public void sayHello() {
    System.out.println("hello");
  }
}