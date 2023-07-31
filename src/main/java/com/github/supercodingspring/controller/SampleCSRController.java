package com.github.supercodingspring.controller;

import com.github.supercodingspring.config.MyComponentA;
import com.github.supercodingspring.dto.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SampleCSRController {
    private MyComponentA myComponentA;

    public SampleCSRController(MyComponentA myComponentA) {
        this.myComponentA = myComponentA;
    }

    @GetMapping(value = "/sample")
    public List<SampleData> getSampleList(){

        myComponentA.sayHello();

        List<SampleData> sampleDataList = new ArrayList<>();
        sampleDataList.add(new SampleData(1, "sample item1"));
        sampleDataList.add(new SampleData(2, "sample item2"));
        sampleDataList.add(new SampleData(3, "sample item3"));
        sampleDataList.add(new SampleData(4, "sample item4"));
        sampleDataList.add(new SampleData(5, "sample item5"));

        return sampleDataList;
    }
}
