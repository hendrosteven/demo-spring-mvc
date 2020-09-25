package com.bca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bca.entity.Book;
import com.bca.repo.BookRepo;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private BookRepo bookRepo;
	
	@GetMapping
	public String index(Model model) {
		Iterable<Book> listOfBooks = bookRepo.findAll();
		model.addAttribute("listOfBooks", listOfBooks);
		return "index";
	}
	
}
