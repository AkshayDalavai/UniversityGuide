package com.univeristyguide.login.dto;

import java.util.List;

public class PostsandCommentsDto {

	
	private PostsDto posts;
	
	
	private CommentsDto comments;
	
	private List<CommentsDto> comments2;


	public PostsDto getPosts() {
		return posts;
	}


	public void setPosts(PostsDto posts) {
		this.posts = posts;
	}


	public void setComments(List<CommentsDto> comments2) {
		
		//for(Comments:)
		
	}


	public List<CommentsDto> getComments() {
		return comments2;
	}
	
	
	
	
}
