package com.univeristyguide.login.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univeristyguide.login.dto.CommentsDto;
import com.univeristyguide.login.dto.CommentsLikesDto;
import com.univeristyguide.login.dto.dtoconverter.FromDtoConverter;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.Comments;
import com.univeristyguide.login.entity.CommentsLikes;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.entity.User;
import com.univeristyguide.login.repository.CategoryRepository;
import com.univeristyguide.login.repository.CommentsLikesRepository;
import com.univeristyguide.login.repository.CommentsRepository;
import com.univeristyguide.login.repository.PostsRepository;
import com.univeristyguide.login.repository.UserRepository;

@Service
public class CommentsService {
	
	private CommentsRepository commentsRepository;
	private UserRepository userRepository;
	private PostsRepository postsRepository;
	private CategoryRepository categoryRepository;
	private CommentsLikesRepository commentsLikesRepository;
	
	@Autowired
	public CommentsService(CommentsRepository theCommentsRepository,
			UserRepository theUserRepository,
			PostsRepository thePostsRepository,
			CategoryRepository theCategoryRepository,
			CommentsLikesRepository theCommentsLikesRepository)
	{
		commentsRepository = theCommentsRepository;
		userRepository = theUserRepository;
		postsRepository = thePostsRepository;
		categoryRepository = theCategoryRepository;
		commentsLikesRepository = theCommentsLikesRepository;
	}
	
	//create new comments
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
	
	//get all comments by postId
	public List<CommentsDto> getAllCommentsByPostId(int theId)
	{
		List<Comments> comments = commentsRepository.findByPosts(theId);
		return comments.stream().sorted(Comparator.comparing(Comments::getCreatedDate).reversed())
				.map(ToDtoConverter::commentsToDtoConverter).collect(Collectors.toList());
		
	}
	
	//update or edit comments by commentId
	//@PreAuthorize("hasRole('USER')")
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
	
	//delete comments by commentId
	//@PreAuthorize("hasRole('USER')")
	public void deleteComments(int theId)
	{
		Optional<Comments> result = commentsRepository.findById(theId);
		if(result.isPresent() == false)
		{
			throw new RuntimeException("Did not find the post id -" + theId);
		}
		commentsRepository.deleteById(theId);
	}
	
	//like comment
	public int likesComment(CommentsLikesDto commentsLikesDto)
	{
		Optional<Comments> resultComment = commentsRepository.findById(commentsLikesDto.getCommentId());
		Comments findComment = new Comments();
		if(resultComment.isPresent())
		{
			findComment = resultComment.get();
		}
		else
		{
			throw new RuntimeException("Did not find the comment id -" + commentsLikesDto.getCommentId());
		}
		
		Optional<Posts> resultPosts = postsRepository.findById(commentsLikesDto.getPostId());
		Posts findPost = new Posts();
		if(resultPosts.isPresent()) {
			findPost = resultPosts.get();
		}
		else 
		{
			throw new RuntimeException("Did not find the post id -" + commentsLikesDto.getPostId());
		}
				
		Optional<User> resultUsers = userRepository.findById(commentsLikesDto.getUserId());
		CommentsLikes commentsLikes = new CommentsLikes();
		User findUser = new User();
		if(resultUsers.isPresent()) {
			findUser = resultUsers.get();
			//commentsLikes = commentsLikesRepository.findByIdUserAndIdComments(commentsLikesDto.getUserId(), commentsLikesDto.getCommentId());
			if(commentsLikesDto.isCommentLike()) {
				commentsLikes.setUser(findUser);
				commentsLikes.setPost(findPost);
				commentsLikes.setComment(findComment);
				commentsLikes.setLike(true);
				findComment.setLikes(findComment.getLikes()+1);
				commentsRepository.save(findComment);
				commentsLikesRepository.save(commentsLikes);
			
			}
			else {
					if(findComment.getLikes()>0) findComment.setLikes(findComment.getLikes()-1);
					commentsRepository.save(findComment);
					CommentsLikes commentLike = commentsLikesRepository.findByIdUserAndIdComments(commentsLikesDto.getUserId(), commentsLikesDto.getCommentId());
					if(commentLike!=null) commentsLikesRepository.delete(commentLike);
			}
			
		}
		else {
			throw new RuntimeException("Did not find user id- " + commentsLikesDto.getUserId());
			
		}
		
		return findComment.getLikes();
		
	}
}   