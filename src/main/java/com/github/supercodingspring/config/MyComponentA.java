package com.github.supercodingspring.config;

import org.springframework.stereotype.Component;

@Component
public class MyComponentA {

// 1. 필드 주입
//    @Autowired
    private MyComponentB componentB;

// 2. setter 주입
//    @Autowired
//    public void setComponentB(MyComponentB componentB) {
//        this.componentB = componentB;
//    }

// 3. 생성자 주입
    public MyComponentA(MyComponentB componentB) {
        this.componentB = componentB;
    }

    public void sayHello(){
        String message = componentB.sayHello() + ", 그리고 난 MyComponentA";
        System.out.println(message);
    }


}
