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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Post;
import com.blog.payload.ApiResponse;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	//Creating post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> creatpost(@RequestBody PostDto dto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createdPost=postService.createPost(dto, userId, categoryId);
	  
	  return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	//Getting post through userId
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userid){
		List<PostDto> post=(List<PostDto>) postService.getPostByUser(userid);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> post=postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
		
	}
	
	
	
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postID){
		PostDto post=postService.getPostById(postID);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber" ,defaultValue = "0",required = false) Integer pageNumber,@RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize){
	 PostResponse postResponse=postService.getAllPost(pageNumber,pageSize);
	 
	 return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	
	
	//Update the Post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePostByPostId(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto Post=postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(Post,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts")
	public   ResponseEntity<ApiResponse> deleteAllPost(){
		postService.deleteAllPost();
		ApiResponse res=new ApiResponse("Deleted Successfully",true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post is deleted",true),HttpStatus.OK);
	}
	
	
}
