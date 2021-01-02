package com.univeristyguide.login.dto.dtoconverter;

import java.util.ArrayList;
import java.util.List;

import com.univeristyguide.login.dto.CategoryDto;
import com.univeristyguide.login.dto.CommentsDto;
import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.dto.UserDto;
import com.univeristyguide.login.entity.Category;
import com.univeristyguide.login.entity.Comments;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.entity.User;

public class ToDtoConverter {

	public static UserDto userToDtoConverter(final User user)
	{
		UserDto resultUser = new UserDto(user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPassword(),
				user.getCreatedDate(),
				user.getLastModifiedDate(),
				user.getUniversity(),
				user.getRoles()
				);
		return resultUser;
	}
	
	public static PostsDto postsToDtoConverter(final Posts posts)
	{
		PostsDto resultPosts = new PostsDto(posts.getId(),
				userToDtoConverter(posts.getUser()),
				posts.getUser().getId(),
				categoryToDtoConverter(posts.getCategory()),
				posts.getCategory().getId(),
				posts.getTitle(),
				posts.isHasComments(),
				posts.getPostContent(),
				posts.getLikesCount(),
				posts.isAnonymous(),
				posts.getCommentsCount(),
				posts.getCreatedDate(),
				posts.getLastModifiedDate(),
				posts.getCreatedBy(),
				posts.isLikes());
		return resultPosts;
				
	}
	
	public static CommentsDto commentsToDtoConverter(final Comments comments)
	{
		CommentsDto resultComments = new CommentsDto(comments.getId(),
				userToDtoConverter(comments.getUser()),
				comments.getUser().getId(),
				postsToDtoConverter(comments.getPosts()),
				comments.getPosts().getId(),
				comments.getCommentsContent(),
				comments.getLikesCount(),
				comments.isAnonymous(),
				comments.getCreatedDate(),
				comments.getLastModifiedDate(),
				comments.getCreatedBy(),
				comments.isLikes());
		return resultComments;
	}
	
	
	public static List<CommentsDto> listofCommentsToDtoConverter(final List<Comments> theComments){
		List<CommentsDto> commentsDto = new ArrayList<CommentsDto>();
		CommentsDto tempCommentsDto = new CommentsDto();
		for(Comments comments:theComments) {
			tempCommentsDto = new CommentsDto(comments.getId(),
					userToDtoConverter(comments.getUser()),
					comments.getUser().getId(),
					postsToDtoConverter(comments.getPosts()),
					comments.getPosts().getId(),
					comments.getCommentsContent(),
					comments.getLikesCount(),
					comments.isAnonymous(),
					comments.getCreatedDate(),
					comments.getLastModifiedDate(),
					comments.getCreatedBy(),
					comments.isLikes());
			commentsDto.add(tempCommentsDto);
		}
		return commentsDto;
		
	}
	
	
	public static CategoryDto categoryToDtoConverter(final Category category)
	{
		CategoryDto resultCategory = new CategoryDto(category.getId(),
				category.getCategoryName()
				);
		return resultCategory;
	}
	
	
	
	//public static LikesDto likesToDtoConverter(final Likes likes) {
		
		
	//}
}
