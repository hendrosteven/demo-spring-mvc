package com.bca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bca.dto.CategoryForm;
import com.bca.dto.ResponseData;
import com.bca.entity.Category;
import com.bca.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<ResponseData> findAllCategory(){
		ResponseData response = new ResponseData();
		try {
			response.setStatus(true);
			response.getMessages().add("Categories found");
			response.setPayload(categoryService.findAll());
			return ResponseEntity.ok(response);
		}catch(Exception ex) {
			response.setStatus(false);
			response.getMessages().add("Could not load categories: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<ResponseData> saveCategory(@RequestBody CategoryForm form){
		Category category = new Category();
		category.setName(form.getName());
		ResponseData response = new ResponseData();
		response.setStatus(true);
		response.setPayload(categoryService.save(category));
		return ResponseEntity.ok(response);
	}
}
