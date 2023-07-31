package com.github.supercodingspring.config;

import org.springframework.stereotype.Component;

@Component
public class MyComponentB {

    public String sayHello(){
        return "난 MyComponentB, ";
    }
}
