package br.com.tdc.workshopapis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home1() {
		return "redirect:/swagger-ui.html";
	}

	@RequestMapping("/swagger")
	public String home2() {
		return "redirect:/swagger-ui.html";
	}
}
