package com.univeristyguide.login.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univeristyguide.login.dto.CategoryDto;
import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.dto.dtoconverter.FromDtoConverter;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.Category;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.repository.CategoryRepository;
import com.univeristyguide.login.repository.CommentsRepository;
import com.univeristyguide.login.repository.PostsRepository;
import com.univeristyguide.login.repository.UserRepository;

@Service
public class CategoryService {
 
	
	public CategoryService() {
		
	}

	private PostsRepository postsRepository;
	private CommentsRepository commentsRepository;
	private UserRepository userRepository;
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(PostsRepository thePostsRepository,
			CommentsRepository theCommentsRepository,
			UserRepository theuserRepository,
			CategoryRepository thecategoryRepository)
	{
		postsRepository = thePostsRepository;
		commentsRepository = theCommentsRepository;
		userRepository = theuserRepository;
		categoryRepository = thecategoryRepository;
	}
	
	
	
	public CategoryDto createCategory(Category theCategory) {
		
		/*if(categoryRepository.findById(theCategory.getId()) == null) {
			throw new RuntimeException("Failed to create Category");

		}*/
		
		categoryRepository.save(theCategory);
		
		return ToDtoConverter.categoryToDtoConverter(theCategory);
	}
	
	
	
	public List<CategoryDto> getAllCategories()
	{
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(ToDtoConverter::categoryToDtoConverter).collect(Collectors.toList());
	}
	
	
	
}
