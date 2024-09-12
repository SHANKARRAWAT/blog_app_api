package com.blog.services;

import java.util.List;

import com.blog.entities.Category;
import com.blog.payload.categoryDto;

public interface categoryService {

	categoryDto createCategory(categoryDto c);
	
	categoryDto updateCategory(categoryDto c,int id);
	
	void deleteCategory(int id);
	
	categoryDto getCategoryById(int id);
	
	List<categoryDto> getAllCategory();
}
