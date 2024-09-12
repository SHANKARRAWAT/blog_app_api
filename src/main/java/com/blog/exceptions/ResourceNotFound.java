package com.blog.exceptions;

public class ResourceNotFound extends RuntimeException{
  
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	
	public ResourceNotFound(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
	
	
	
	
	
}
