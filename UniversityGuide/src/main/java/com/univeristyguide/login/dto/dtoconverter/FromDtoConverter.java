package com.univeristyguide.login.dto.dtoconverter;

import com.univeristyguide.login.dto.CategoryDto;
import com.univeristyguide.login.dto.CommentsDto;
import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.dto.UserDto;
import com.univeristyguide.login.entity.Category;
import com.univeristyguide.login.entity.Comments;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.entity.User;

public class FromDtoConverter {


	public static User fromUsersDtoConverter(final UserDto userDto)
	{
		User resultUser = new User(userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail(),
				userDto.getPassword(),
				userDto.getUniversity(),
				userDto.getRoles());
		return resultUser;
	}
	
	public static Posts fromPostsDtoConverter(final PostsDto postsDto)
	{
		Posts resultPosts = new Posts(fromUsersDtoConverter(postsDto.getUser()),
				fromCategoryDtoConverter(postsDto.getCategory()),
				postsDto.getTitle(),
				postsDto.isHasComments(),
				postsDto.getPostContent(),
				postsDto.getLikesCount(),
				postsDto.isAnonymous(),
				postsDto.getCommentsCount());
			return resultPosts;
	}
	
	public static Category fromCategoryDtoConverter(final CategoryDto categoryDto)
	{
		Category resultsCategory = new Category(categoryDto.getId(),
				categoryDto.getCategoryName());
		return resultsCategory;
	}
	
	public static Comments fromCommentsDtoConverter(final CommentsDto commentsDto)
	{
		Comments resultComments = new Comments(commentsDto.getId(),
				fromUsersDtoConverter(commentsDto.getUser()),
				fromPostsDtoConverter(commentsDto.getPosts()),
				commentsDto.getCommentsContent(),
				commentsDto.getLikesCount(),
				commentsDto.isAnonymous());
		return resultComments;
	}
}
