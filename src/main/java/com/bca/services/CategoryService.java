package com.bca.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bca.entity.Category;
import com.bca.repo.CategoryRepo;

@Service("categoryService")
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public Category save(Category category) {
		return categoryRepo.save(category);
	}
	
	public Optional<Category> findById(Long id){
		return categoryRepo.findById(id);
	}
	
	public Iterable<Category> findAll(){
		return categoryRepo.findAll();
	}

}
