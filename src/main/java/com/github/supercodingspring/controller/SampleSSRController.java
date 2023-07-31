package com.github.supercodingspring.controller;

import com.github.supercodingspring.dto.SampleData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleSSRController {

    @GetMapping("/sample")
    public String samplePage(Model model){
        List<SampleData> sampleDataList = new ArrayList<>();
        sampleDataList.add(new SampleData(1, "sample item1"));
        sampleDataList.add(new SampleData(2, "sample item2"));
        sampleDataList.add(new SampleData(3, "sample item3"));
        sampleDataList.add(new SampleData(4, "sample item4"));
        sampleDataList.add(new SampleData(5, "sample item5"));

        model.addAttribute("dataList", sampleDataList);
        return "samplePage";
    }
}
