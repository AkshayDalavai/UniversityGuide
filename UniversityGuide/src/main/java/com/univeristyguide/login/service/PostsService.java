package com.univeristyguide.login.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.univeristyguide.login.dto.CommentsDto;
import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.dto.dtoconverter.FromDtoConverter;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.Category;
import com.univeristyguide.login.entity.Comments;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.entity.User;
import com.univeristyguide.login.repository.CategoryRepository;
import com.univeristyguide.login.repository.CommentsRepository;
import com.univeristyguide.login.repository.PostsRepository;
import com.univeristyguide.login.repository.UserRepository;

@Service
public class PostsService {
	
	private PostsRepository postsRepository;
	private CommentsRepository commentsRepository;
	private UserRepository userRepository;
	private CategoryRepository categoryRepository;
	private CommentsService commentsService;
	
	@Autowired
	public PostsService(PostsRepository thePostsRepository,
			CommentsRepository theCommentsRepository,
			UserRepository theuserRepository,
			CategoryRepository thecategoryRepository,
			CommentsService theCommentsService)
	{
		postsRepository = thePostsRepository;
		commentsRepository = theCommentsRepository;
		userRepository = theuserRepository;
		categoryRepository = thecategoryRepository;
		commentsService = theCommentsService;
	}
	
	public PostsService()
	{
		
	}
	
	
	public PostsDto createPost(PostsDto postsDto)
	{
		int userId = postsDto.getUserId();
		int categoryId = postsDto.getCategoryId();
		Optional<User> resultUser = userRepository.findById(userId);
		User theUser = null;
		if(resultUser.isPresent())
		{
			theUser=resultUser.get();
		}
		else
		{
			throw new RuntimeException("Did not find user id - " + userId);
		}
		Optional<Category> resultCategory = categoryRepository.findById(categoryId);
		Category theCategory =null;
		if(resultCategory.isPresent())
		{
			theCategory = resultCategory.get();
		}
		else
		{
			throw new RuntimeException("Did not find category id - " + categoryId);
		}
		if(postsDto.isAnonymous())
		{
			postsDto.setCreatedBy("Anonymous");
		}
		postsDto.setUser(ToDtoConverter.userToDtoConverter(theUser));
		postsDto.setCategory(ToDtoConverter.categoryToDtoConverter(theCategory));
		
		postsDto.setHasComments(false);
		Posts thePosts = postsRepository.save(FromDtoConverter.fromPostsDtoConverter(postsDto));
		postsDto.setCreatedDate(thePosts.getCreatedDate());
		postsDto.setLastModifiedDate(thePosts.getLastModifiedDate());
		postsDto.setCreatedBy(thePosts.getCreatedBy());
		postsDto.setId(thePosts.getId());
		return postsDto;
		
	}
	
	public List<PostsDto> getAllPosts()
	{
		List<Posts> posts = postsRepository.findAllSortedByDateReverse();
		return posts.stream().map(ToDtoConverter::postsToDtoConverter).collect(Collectors.toList());
	}
	
	public PostsDto getPostById(int theId)
	{
		Optional<Posts> result = postsRepository.findById(theId);
		
		Posts thePosts = null;
		if(result.isPresent())
		{
			thePosts = result.get();
		}
		else
		{
			throw new RuntimeException("Did not find post id - " + theId);
		}
		List<CommentsDto> relatedComments = commentsService.getAllCommentsByPostId(theId);
		PostsDto thePostsDto = ToDtoConverter.postsToDtoConverter(thePosts);
		thePostsDto.setComments(relatedComments);
		return thePostsDto;
	}
	
	//@PreAuthorize("hasRole('USER')")
	public PostsDto updatePosts(Posts posts)
	{
		Optional<Posts> result = postsRepository.findById(posts.getId());
		
		Posts thePosts = null;
		if(result.isPresent())
		{
			thePosts = result.get();
		}
		else
		{
			throw new RuntimeException("Did not find the post id -" + posts.getId());
		}
		
		posts.setCreatedDate(thePosts.getCreatedDate());
		if(thePosts.isAnonymous())
		{
			posts.setCreatedBy("Anonymous");
		}
		else
		{
			posts.setCreatedBy(thePosts.getCreatedBy());
		}
		
		posts.setLastModifiedDate(thePosts.getLastModifiedDate());
		posts.setLastModifiedBy(thePosts.getLastModifiedBy());
		
		postsRepository.save(posts);
		return ToDtoConverter.postsToDtoConverter(posts);
	}
	
	//@PreAuthorize("hasRole('USER')")
	public void deletePosts(int theId)
	{
		Optional<Posts> result = postsRepository.findById(theId);
		if(result.isPresent() == false)
		{
			throw new RuntimeException("Did not find the post id -" + theId);
		}
		List<Comments> relatedComments = commentsRepository.findByPosts(theId);
		if(relatedComments.size() > 0)
		{
			for(Comments comments : relatedComments)
			{
				commentsRepository.deleteById(comments.getId());
			}
		}
		postsRepository.deleteById(theId);
	}
	
	public void likes(int postsId,final int buttonState)
	{
		Optional<Posts> result = postsRepository.findById(postsId);
		Posts findPost = null;
		if(result.isPresent())
		{
			findPost = result.get();
		}
		else
		{
			throw new RuntimeException("Did not find the post id -" + postsId);
		}
		if(buttonState == 0)
		{
			findPost.setLikesCount(findPost.getLikesCount() -1);
		}
		else if(buttonState == 1)
		{
			findPost.setLikesCount(findPost.getLikesCount()+1);
		}
		postsRepository.save(findPost);
	}
	
	public void commentsCount(int postId)
	{
		Optional<Posts> result = postsRepository.findById(postId);
		Posts findPost = null;
		if(result.isPresent())
		{
			findPost = result.get();
			List<Comments> comments = commentsRepository.findByPosts(postId);
			if(comments.isEmpty())
			{
				findPost.setCommentsCount(0);
			}
			else
			{
				findPost.setCommentsCount(comments.size());
			}
		}
		else
		{
			throw new RuntimeException("Did not find the post id -" + postId);
		}
		postsRepository.save(findPost);
	}
	
	public List<PostsDto> getAllPostsByUserId(int theUserId)
	{
		List<Posts> posts = postsRepository.findByIdUser(theUserId);
		return posts.stream().sorted(Comparator.comparing(Posts::getCreatedDate).reversed())
				.map(ToDtoConverter::postsToDtoConverter).collect(Collectors.toList());
		
	}
	
	public List<PostsDto> getAllPostsByCategoryId(int theCategoryId)
	{
		List<Posts> posts = postsRepository.findByCategory(theCategoryId);
		return posts.stream().sorted(Comparator.comparing(Posts::getCreatedDate).reversed())
				.map(ToDtoConverter::postsToDtoConverter).collect(Collectors.toList());
		
	}
}
