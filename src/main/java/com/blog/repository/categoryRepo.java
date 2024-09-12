package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;

public interface categoryRepo  extends JpaRepository<Category,Integer>{

}
