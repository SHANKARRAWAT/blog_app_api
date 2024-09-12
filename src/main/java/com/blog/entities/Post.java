package com.blog.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
@Entity
public class Post {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@Column(name="post_title")
	private String title;
	
	@Column(name="post_content",nullable = false)
	private String content;
	
	private Date addedDate;
	
	private String imageName;
	
	
	@ManyToOne
	private User user;

	@ManyToOne
	private Category category;
	

	
}
