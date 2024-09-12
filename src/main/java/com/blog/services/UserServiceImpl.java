package com.blog.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payload.UserDto;
import com.blog.repository.UserRepo;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// we have User table in Databse but we are taking data with DTO
	@Override
	public UserDto createUser(UserDto user) {
	   User u=dtoToUser(user);
	   User savedUser=userRepo.save(u);
	   return userToDTO(savedUser);
	   
	}

	@Override
	public UserDto updateUser(UserDto userdto, int userid) {
		
	   User user=userRepo.findById(userid).orElseThrow(
			   ()->new ResourceNotFound("User","id",userid));
	       
	     // User users=dtoToUser(userdto);
	      user.setName(userdto.getName());
	      user.setEmail(userdto.getEmail());
	      user.setPassword(userdto.getPassword());;
	      user.setAbout(userdto.getAbout());
	      System.out.println(user);  
	      userRepo.save(user);
	        
	      
	     return userToDTO(user);
	}

	@Override
	public UserDto getUserById(int id) {
	    
		User user=userRepo.findById(id).orElseThrow(
				()->new ResourceNotFound("User","id", id));
		
		return userToDTO(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		  List<User> users=userRepo.findAll();
		 List<UserDto> userDto= users.stream().map(user->userToDTO(user)).collect(Collectors.toList());
		  
		return userDto;
	}

	@Override
	public void deleteUser(int id) {
	
		User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFound("User", "id", id));
		 userRepo.delete(user);
		
	}
	
	
	
	
	// Model Mapper converts one class object to other class object
	public User dtoToUser(UserDto dto) {
		
		User user= modelMapper.map(dto, User.class);
		
//		user.setId(dto.getId());
//		user.setName(dto.getName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setAbout(dto.getAbout());
		return user;
		
	}
	
	

	public UserDto userToDTO(User user) {
		
		UserDto dto=modelMapper.map(user, UserDto.class);
		
//		 dto.setId(user.getId());
//		 dto.setName(user.getName());
//		 dto.setEmail(user.getEmail());
//		 dto.setPassword(user.getPassword());
//		 dto.setAbout(user.getAbout());
		return dto;
		
	}

}
