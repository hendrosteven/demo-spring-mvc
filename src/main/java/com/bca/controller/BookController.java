package com.bca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

	@GetMapping
	public String insert() {
		return "input";
	}
	
	@PostMapping
	public String save() {
		return "input";
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "edit";
	}
	
	@PostMapping("/update")
	public String update() {
		return "input";
	}
}
