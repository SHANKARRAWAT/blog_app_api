package com.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class categoryDto {
 
	private int categoryId;
	
	@NotEmpty
	@Size(min=4,message="size must be of 6 characters")
	private String title;
	
	@NotEmpty
	@Size(min=6,message="size must be of 6 characters")
	private String description;
}
