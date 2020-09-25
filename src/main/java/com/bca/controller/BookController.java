package com.bca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bca.dto.BookForm;
import com.bca.entity.Book;
import com.bca.services.BookService;
import com.bca.services.CategoryService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;

	@GetMapping
	public String insert(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("bookForm", new BookForm());
		return "input";
	}
	
	@PostMapping
	public String save(BookForm bookForm, Model model) {
		if(bookService.findByCode(bookForm.getCode()) == null) {
			Book book = new Book();
			book.setCode(bookForm.getCode());
			book.setTitle(bookForm.getTitle());
			book.setDescription(bookForm.getDescription());
			book.setPrice(bookForm.getPrice());
			book.setImagePath(bookForm.getImagePath());
			book.setCategory(categoryService.findById(bookForm.getCategoryId()).get());
			bookService.save(book);
			return "redirect:/";
		}else {
			//next
			return "input";
		}
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Book book = bookService.findById(id).get();
		model.addAttribute("categories", categoryService.findAll());
		BookForm form = new BookForm();
		form.setTitle(book.getTitle());
		form.setDescription(book.getDescription());
		form.setCode(book.getCode());
		form.setPrice(book.getPrice());
		form.setImagePath(book.getImagePath());
		form.setCategoryId(book.getCategory().getId());
		model.addAttribute("bookForm", form);
		return "edit";
	}
	
	@PostMapping("/update")
	public String update() {
		return "input";
	}
	
	
	@GetMapping(value="/delete/{id}")
	public String remove(@PathVariable("id") Long id) {
		bookService.delete(id);
		return "redirect:/";
	}
	
	
}
