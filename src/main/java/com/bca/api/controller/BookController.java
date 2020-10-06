package com.bca.api.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bca.dto.BookForm;
import com.bca.dto.ResponseData;
import com.bca.entity.Book;
import com.bca.services.BookService;
import com.bca.services.CategoryService;

@RestController("booksApi")
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;
	
	
	
	@GetMapping
	public ResponseEntity<ResponseData> findAllBook(){
		ResponseData response = new ResponseData();
		try {
			response.setStatus(true);
			response.getMessages().add("Books loaded");
			response.setPayload(bookService.findAll());
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not load books: "+ ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping(value = "/{page}/{rows}")
	public ResponseEntity<ResponseData> findAllBookPerPage(@PathVariable("page") int page, 
			@PathVariable("rows") int rows){
		ResponseData response = new ResponseData();
		try {
			response.setStatus(true);
			response.getMessages().add("Books loaded");
			response.setPayload(bookService.findAll(page, rows));
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not load books: "+ ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseData> saveBook(@Valid @RequestBody BookForm bookForm, BindingResult errors){
		ResponseData response = new ResponseData();
		
		if(!errors.hasErrors()) {
			
			if(bookService.findByCode(bookForm.getCode()) == null) {
				Book book = new Book();
				book.setCode(bookForm.getCode());
				book.setTitle(bookForm.getTitle());
				book.setDescription(bookForm.getDescription());
				book.setPrice(bookForm.getPrice());
				book.setImagePath(bookForm.getImagePath());
				book.setCategory(categoryService.findById(bookForm.getCategoryId()).get());
				response.setPayload(bookService.save(book));				
				response.setStatus(true);
				response.getMessages().add("Book saved");
				return ResponseEntity.ok(response);
			}else {
				response.setStatus(false);
				response.getMessages().add("Please use another code");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			
		}else {
			response.setStatus(false);
			for(ObjectError err: errors.getAllErrors()) {
				response.getMessages().add(err.getDefaultMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
}
