package com.univeristyguide.login.dto;

public class CommentsLikesDto {

	private int id;
	
	private int userId;
	
	private int postId;
	
	private int commentId;
	
	private boolean commentLike;

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

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public boolean isCommentLike() {
		return commentLike;
	}

	public void setCommentLike(boolean commentLike) {
		this.commentLike = commentLike;
	}

	@Override
	public String toString() {
		return "CommentsLikesDto [id=" + id + ", userId=" + userId + ", postId=" + postId + ", commentId=" + commentId
				+ ", commentLike=" + commentLike + "]";
	}

	public CommentsLikesDto(int id, int userId, int postId, int commentId, boolean commentLike) {
		super();
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.commentId = commentId;
		this.commentLike = commentLike;
	}

	public CommentsLikesDto() {
		super();
	}
	
	
	
	
	
}