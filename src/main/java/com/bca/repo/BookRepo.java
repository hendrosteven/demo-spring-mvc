package com.bca.repo;

import org.springframework.data.repository.CrudRepository;

import com.bca.entity.Book;

public interface BookRepo extends CrudRepository<Book, Long> {

}
