package br.com.slippers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	public String index() {
		return "index";
	}

	@GetMapping(value = "index")
	public String indexRedirect() {
		return "redirect:/";
	}
	
	@GetMapping(value = "cart")
	public String cart() {
		return "cart";
	}
}
