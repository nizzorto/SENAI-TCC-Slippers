package br.com.slippers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	
	@GetMapping
	public String index(Model model) {
		List<Chinelo> chinelos = chineloRepository.findAll();
		model.addAttribute("chinelos", chinelos);
		SecurityContextHolder.getContext().getAuthentication().getName();
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
