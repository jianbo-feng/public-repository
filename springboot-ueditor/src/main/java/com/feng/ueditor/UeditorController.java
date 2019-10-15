package com.feng.ueditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class UeditorController {

    @RequestMapping("test")
    public String test() {
        return "admin/test";
    }
}
