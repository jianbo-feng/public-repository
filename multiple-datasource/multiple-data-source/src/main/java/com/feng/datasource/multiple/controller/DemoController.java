package com.feng.datasource.multiple.controller;

import com.feng.datasource.multiple.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = {"/", "/test"}, method = {RequestMethod.GET})
    public Map<String, Object> test() {
        Map<String, Object> data = new HashMap<>();
        data.put("products", myService.allProduct());
        data.put("customers", myService.allCustomer());
        return data;
    }
}
