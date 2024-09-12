package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFound;
import com.blog.payload.UserDto;
import com.blog.payload.categoryDto;
import com.blog.repository.categoryRepo;


@Service
public class CategoryServiceImpl implements categoryService {

	@Autowired
	private   categoryRepo cr;
    
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public categoryDto createCategory(categoryDto c) {
		Category category=modelmapper.map(c,Category.class);
	     categoryDto cdto=modelmapper.map(cr.save(category),categoryDto.class );
		return cdto;
	}

	@Override
	public categoryDto updateCategory(categoryDto cdto, int id) {
		Category category= cr.findById(id).orElseThrow(()-> new ResourceNotFound("Category","Id", id));
		category.setTitle(cdto.getTitle());
		category.setCategoryDescription(cdto.getDescription());
		// cr.save(category);
		 cdto=modelmapper.map(cr.save(category),categoryDto.class);
		return cdto;
	}

	@Override
	public void deleteCategory(int id) {
		Category cd=cr.findById(id).orElseThrow(
				()->new ResourceNotFound("Category", "id", id));
		cr.delete(cd);
		
	}

	
	
	@Override
	public categoryDto getCategoryById(int id) {
		Category cd=cr.findById(id).orElseThrow(()-> new ResourceNotFound("Category", "id", id));	
		return modelmapper.map(cd,categoryDto.class);
	}

	@Override
	public List<categoryDto> getAllCategory() {
		List<Category> cd=cr.findAll();
//		 categoryDto cdto=new categoryDto();	
//		 for(Category cdg:cd) {
//			 
//	 }
		
		 List<categoryDto> cdto= cd.stream().map(c->modelmapper.map(c,categoryDto.class)).collect(Collectors.toList());	  
		return cdto;
	}
	
}


