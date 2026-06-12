package com.learn.firstservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class FirstController {

   @GetMapping("/message")
   public String  getMessage() {
	   return " Welcome to First Service";
   }

}
