package com.blog.services;

import java.util.List;

import com.blog.payload.UserDto;

public interface UserService {
  UserDto createUser(UserDto user);
  UserDto updateUser(UserDto user,int userid);
  UserDto getUserById(int id);
  List<UserDto> getAllUser();
  void deleteUser(int id);
  
  
}
