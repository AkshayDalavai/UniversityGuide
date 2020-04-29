package com.univeristyguide.login.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentsService {
	
	private CommentsRepository commentsRepository;
	private UserRepository userRepository;
	private PostsRepository postsRepository;
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CommentsService(CommentsRepository theCommentsRepository,
			UserRepository theUserRepository,
			PostsRepository thePostsRepository,
			CategoryRepository theCategoryRepository)
	{
		commentsRepository = theCommentsRepository;
		userRepository = theUserRepository;
		postsRepository = thePostsRepository;
		categoryRepository = theCategoryRepository;
	}
	
	public CommentsDto createComments(CommentsDto commentsDto)
	{
		int userId = commentsDto.getUserId();
		int postsId = commentsDto.getPostsId();
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
		
		Optional<Posts> resultPosts = postsRepository.findById(postsId);
		Posts thePosts =null;
		if(resultPosts.isPresent())
		{
			thePosts = resultPosts.get();
			thePosts.setHasComments(true);
			thePosts.setCommentsCount(thePosts.getCommentsCount()+1);
			postsRepository.save(thePosts);
		}
		else
		{
			throw new RuntimeException("Did not find category id - " + postsId);
		}
		if(commentsDto.isAnonymous())
		{
			commentsDto.setCreatedBy("Anonymous");
		}
		commentsDto.setUser(ToDtoConverter.userToDtoConverter(theUser));
		commentsDto.setPosts(ToDtoConverter.postsToDtoConverter(thePosts));
		
	  
		Comments comments = commentsRepository.save(FromDtoConverter.fromCommentsDtoConverter(commentsDto));
		commentsDto.setCreatedDate(comments.getCreatedDate());
		commentsDto.setLastModifiedDate(comments.getLastModifiedDate());
		commentsDto.setCreatedBy(comments.getCreatedBy());
		commentsDto.setId(comments.getId());
		return commentsDto;
	}
	
	public List<CommentsDto> getAllCommentsByPostId(int theId)
	{
		List<Comments> comments = commentsRepository.findByPosts(theId);
		return comments.stream().sorted(Comparator.comparing(Comments::getCreatedDate).reversed())
				.map(ToDtoConverter::commentsToDtoConverter).collect(Collectors.toList());
		
	}
	
	public CommentsDto updateComments(Comments comment)
	{
		Optional<Comments> result = commentsRepository.findById(comment.getId());
		
		Comments theComment = null;
		if(result.isPresent())
		{
			theComment = result.get();
		}
		else
		{
			throw new RuntimeException("The comment Id "+ comment.getId() +" not found");
		}
		comment.setCreatedDate(theComment.getCreatedDate());
		comment.setCreatedBy(theComment.getCreatedBy());
		comment.setLastModifiedBy(theComment.getLastModifiedBy());
		comment.setLastModifiedDate(theComment.getLastModifiedDate());
		commentsRepository.save(comment);
		return ToDtoConverter.commentsToDtoConverter(comment);
	}
	
	public void deleteComments(int theId)
	{
		Optional<Comments> result = commentsRepository.findById(theId);
		if(result.isPresent() == false)
		{
			throw new RuntimeException("Did not find the post id -" + theId);
		}
		commentsRepository.deleteById(theId);
	}
	
	public void likesComment(int commentsId,final int buttonState)
	{
		Optional<Comments> result = commentsRepository.findById(commentsId);
		Comments findComment = null;
		if(result.isPresent())
		{
			findComment = result.get();
		}
		else
		{
			throw new RuntimeException("Did not find the post id -" + commentsId);
		}
		if(buttonState == 0)
		{
			findComment.setLikes(findComment.getLikes() -1);
		}
		else if(buttonState == 1)
		{
			findComment.setLikes(findComment.getLikes()+1);
		}
		commentsRepository.save(findComment);
	}
}   