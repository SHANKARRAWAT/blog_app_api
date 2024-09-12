package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,int postId);
	
	//Delete
	void deletePost(Integer postId);
	
	void deleteAllPost();
	
	//getAllPost
	PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	
	//getPostById;
	PostDto getPostById(Integer id);
	
	//GetPost By Category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//GetAllPost By User;
	List<PostDto> getPostByUser(Integer userId);
	
	
	
}
