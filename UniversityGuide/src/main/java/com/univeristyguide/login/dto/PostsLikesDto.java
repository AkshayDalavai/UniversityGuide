package com.univeristyguide.login.dto;

import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.entity.User;

public class PostsLikesDto {

	private int id;
	
	private int userId;
	
	private int postId;
	
	private boolean postslikes;

	public boolean isPostslikes() {
		return postslikes;
	}

	public void setPostslikes(boolean postslikes) {
		this.postslikes = postslikes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public PostsLikesDto(int id, int userId, int postId, boolean postslikes) {
		super();
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.postslikes = postslikes;
	}

	public PostsLikesDto() {
		super();
	}
	
}
