package com.univeristyguide.login.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univeristyguide.login.dto.CommentsDto;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.Comments;
import com.univeristyguide.login.repository.CommentsRepository;
import com.univeristyguide.login.repository.PostsRepository;

@Service
public class CommentsService {
	
	private CommentsRepository commentsRepository;
	
	
	@Autowired
	public CommentsService(CommentsRepository theCommentsRepository)
	{
		commentsRepository = theCommentsRepository;
	}
	
	public CommentsDto createComments(Comments comments)
	{
		commentsRepository.save(comments);
		return ToDtoConverter.commentsToDtoConverter(comments);
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
