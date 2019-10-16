package com.feng.activiti.designer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping("/modeler")
    public String modeler(Model model, @RequestParam("modelId") String modelId) {
        model.addAttribute("modelId", modelId);
        return "modeler";
    }
}
