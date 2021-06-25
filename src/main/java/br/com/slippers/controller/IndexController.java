package br.com.slippers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.slippers.model.Chinelo;
import br.com.slippers.repository.ChineloRepository;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	ChineloRepository chineloRepository;	
	
	
	@GetMapping
	public String index(Model model) {
		List<Chinelo> chinelos = chineloRepository.findAll();
		model.addAttribute("chinelos", chinelos);
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
