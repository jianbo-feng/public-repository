package com.docker.demo;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/docker")
public class DockerController {

    @RequestMapping(value = {"", "/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "Hello,world!";
    }
}
