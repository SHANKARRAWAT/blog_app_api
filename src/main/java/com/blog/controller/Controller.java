package com.blog.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.UserDto;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController // combination of @controller and @responseBody
@RequestMapping("/api/users")
public class Controller {

	@Autowired
	private UserService userService;
	
	// POST REQUEST
	@PostMapping("/")
	 public ResponseEntity<UserDto>  createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUser=userService.createUser(userDto);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	 }
	
	//PUT->update REQUEST
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@RequestBody UserDto userdto,@PathVariable("id") Integer id){
		
		UserDto updateUser = userService.updateUser(userdto,id);
		return new ResponseEntity<>(updateUser,HttpStatus.ACCEPTED);
	}
	
	
	//DELETE Request
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable("id") Integer id){
		   userService.deleteUser(id);
		  return new ResponseEntity<ApiResponse>(new ApiResponse("Succesfully deleted",true),HttpStatus.OK);
	}
	
	//Get Method
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> users=userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id){
		UserDto user=userService.getUserById(id);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
}
