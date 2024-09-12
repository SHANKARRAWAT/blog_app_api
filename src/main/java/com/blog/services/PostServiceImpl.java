package com.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.payload.categoryDto;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.repository.categoryRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private categoryRepo categoryRepo;

	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
	    User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User", "userId", userId));
	    Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("User", "userId", categoryId));
	    
	    Post post =modelMapper.map(postDto, Post.class);
	    post.setImageName("default.img");
	    post.setCategory(category);
	    post.setUser(user);
	    post.setAddedDate(new Date());
        Post newPost=postRepo.save(post);
        
		return modelMapper.map(newPost,PostDto.class) ;
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("Post", "postId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		post.setAddedDate(new Date());
		Post updatedPost= postRepo.save(post);
		
		return  modelMapper.map(updatedPost,PostDto.class);
	}

	
	@Override
	public void deletePost(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "postId",postId));
		postRepo.deleteById(postId);
	}

	@Override
	public void deleteAllPost() {
		postRepo.deleteAll();
	}
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {
		Pageable p=PageRequest.of(pageNumber, pageSize);
        Page<Post> pp=  postRepo.findAll(p);
        
		List<Post> posts=pp.getContent();
		List<PostDto> postsDto=posts.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		 postResponse.setContent(postsDto);
		 postResponse.setPageNumber(pp.getNumber());
		 postResponse.setPageSize(pp.getSize());
		 postResponse.setTotalElements(pp.getTotalElements());
		 postResponse.setTotalPages(pp.getTotalPages());
		 postResponse.setLastPage(pp.isLast());
		 
		 
			return postResponse ;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post=postRepo.findById(id).orElseThrow(()-> new ResourceNotFound("post", "Id", id));
		PostDto postdto=modelMapper.map(post, PostDto.class);
		return postdto;
	}


	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat= categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("category","categoryId", categoryId));
		List<Post> posts=postRepo.findByCategory(cat);
		 List<PostDto> pdto= posts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());	
		 return pdto;
		
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
	   User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFound ("User", "userId",userId));
	   List<Post> posts=postRepo.findByUser(user);
	   List<PostDto> pdto=posts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return pdto;
	}

}
