package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
//	@RequestMapping(method = RequestMethod.GET, value="/")
//	@RequestMapping("/")
//	@GetMapping("/")
//	public String home() {
//		return "home";
//	}

	
	//ModelMap  - used to transfer the data from controller  -->view(Jsp)
	@GetMapping("/")
	public String home(ModelMap  map) {
		map.addAttribute("msg", "Welcome to SpringMVC .......");
		return "home";
	}

}
