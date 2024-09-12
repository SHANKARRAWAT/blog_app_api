
package com.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
  
	
	private int id;
	
	@NotEmpty
	@Size(min=3, message="username must have length min 3")
	private String name;
	
	
	@NotEmpty
	@Email(message = "Email address is not Valid") 
	private String email;
	 
	@NotEmpty
	@Size(min=5,message="enter the password having length min 5")
	private String password;
	
	@NotEmpty(message = "about can't be empty")
	private String about;
}
