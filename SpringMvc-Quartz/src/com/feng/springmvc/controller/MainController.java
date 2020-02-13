package com.feng.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(method = RequestMethod.GET, value= {"/cron", "/cron/", "/cron/index"})
	public String cron(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "/cron/index";
	}
}
