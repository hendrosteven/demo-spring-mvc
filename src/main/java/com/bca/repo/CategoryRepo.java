package com.bca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bca.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}
