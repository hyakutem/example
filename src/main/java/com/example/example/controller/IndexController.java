package com.example.example.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Getter;
import lombok.Setter;

@Controller
@ConfigurationProperties(prefix = "exampleapp")
public class IndexController {
    
	@Setter
	@Getter
	private String contents = "brv-example default text on IndexController!!";

	@RequestMapping("/")
	String index(Model model) {
		model.addAttribute("contents", contents);
		return "index";
	}

}
