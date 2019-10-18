package com.feng.springboot.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return "redirect:/test/socket";
    }

    @RequestMapping("/test/controller")
    public String test() {
        return "controllerTest";
    }

    @RequestMapping("/test/socket")
    public String socket() {
        return "socket";
    }
}
