package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.categoryDto;
import com.blog.services.categoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private categoryService categoryservice;
	
	
	@PostMapping("/")
	public ResponseEntity<categoryDto> createCategory(@Valid @RequestBody categoryDto cdto){
		categoryDto dto=categoryservice.createCategory(cdto);
		return new ResponseEntity<categoryDto>(dto,HttpStatus.CREATED);
	}
	
	
	//PUTMaping
	@PutMapping("/{id}")
	public ResponseEntity<categoryDto> update(@Valid @RequestBody categoryDto cdto , @PathVariable Integer id){
		categoryDto dto=categoryservice.updateCategory(cdto,id);
		return new ResponseEntity<categoryDto>(dto,HttpStatus.ACCEPTED);
	}
	
	//GET MAPPING
	 @GetMapping("/{id}")
	 public ResponseEntity<categoryDto> getCategory(@PathVariable("id") Integer id){
		 categoryDto cdto=categoryservice.getCategoryById(id);
		 return new ResponseEntity<categoryDto>(cdto,HttpStatus.OK);
	 }
	 
	 
	 @GetMapping("/")
	 public ResponseEntity<List<categoryDto>> getAllCategory(){
		List<categoryDto> categories= categoryservice.getAllCategory();
		return new ResponseEntity<List<categoryDto>>(categories,HttpStatus.OK);
	 }
	 
	 //DELETE MAPPING
	 @DeleteMapping("/{id}")
	 public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
		 categoryservice.deleteCategory(id);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("category with id deleted",true),HttpStatus.OK);
		 
	 }
}
