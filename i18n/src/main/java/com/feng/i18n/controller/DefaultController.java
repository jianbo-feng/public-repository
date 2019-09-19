package com.feng.i18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class DefaultController {

    @GetMapping("/")
    private String defaultPage() {
        return "default";
    }
}
