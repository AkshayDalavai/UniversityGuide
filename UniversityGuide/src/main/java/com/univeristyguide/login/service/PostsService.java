package com.univeristyguide.login.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.Comments;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.repository.CommentsRepository;
import com.univeristyguide.login.repository.PostsRepository;

public class PostsService {
	
	private PostsRepository postsRepository;
	private CommentsRepository commentsRepository;
	
	@Autowired
	public PostsService(PostsRepository thePostsRepository,CommentsRepository theCommentsRepository)
	{
		postsRepository = thePostsRepository;
		commentsRepository = theCommentsRepository;
	}
	
	public PostsService()
	{
		
	}
	
	public PostsDto createPost(Posts posts)
	{
		postsRepository.save(posts);
		return ToDtoConverter.postsToDtoConverter(posts);
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
		return ToDtoConverter.postsToDtoConverter(thePosts);
	}
	
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
		posts.setCreatedBy(thePosts.getCreatedBy());
		posts.setLastModifiedDate(thePosts.getLastModifiedDate());
		posts.setLastModifiedBy(thePosts.getLastModifiedBy());
		
		postsRepository.save(posts);
		return ToDtoConverter.postsToDtoConverter(posts);
	}
	
	public void deletePosts(int theId)
	{
		Optional<Posts> result = postsRepository.findById(theId);
		if(result.isPresent() == false)
		{
			throw new RuntimeException("Did not find the post id -" + theId);
		}
		List<Comments> relatedComments = commentsRepository.findByPostId(theId);
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
}
