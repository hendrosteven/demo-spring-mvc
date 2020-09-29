package com.bca.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bca.entity.Book;
import com.bca.repo.BookRepo;

@Service("bookService")
@Transactional
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	public Book save(Book book) {
		return bookRepo.save(book);
	}
	
	public Iterable<Book> findAll(){
		return bookRepo.findAll();
	}
	
	public List<Book> findAll(int page, int rows){
		Pageable pageable = PageRequest.of(page-1, rows);
		return bookRepo.findAll(pageable).getContent();
	}
	
	public List<Book> findAllByTitle(String title, int page){
		Pageable pageable = PageRequest.of(page-1, 10);
		return bookRepo.findAllByTitle(title, pageable);
	}
	
	public Book findByCode(String code) {
		return bookRepo.findByCode(code);
	}
	
	public Optional<Book> findById(Long id){
		return bookRepo.findById(id);
	}
	
	public boolean delete(Long id) {
		bookRepo.deleteById(id);
		return true;
	}
	
}
