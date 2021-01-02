package com.univeristyguide.login.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CommentsDto {
	
	private int id;
	
	private UserDto user;
	
	//Testing
	private int userId;
	
	private PostsDto posts;
	
	//Testing
	private int postsId;
	
	private String commentsContent;
	
	private int likesCount;
	
	private boolean isAnonymous;
	
	private Date createdDate;
	
	private Date lastModifiedDate;
	
	private String createdBy;
	
	private boolean likes;

	public CommentsDto() {
		
	}

	




	public CommentsDto(int id, UserDto user, int userId, PostsDto posts, int postsId, String commentsContent,
			int likesCount, boolean isAnonymous, Date createdDate, Date lastModifiedDate, String createdBy,
			boolean likes) {
		super();
		this.id = id;
		this.user = user;
		this.userId = userId;
		this.posts = posts;
		this.postsId = postsId;
		this.commentsContent = commentsContent;
		this.likesCount = likesCount;
		this.isAnonymous = isAnonymous;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
		this.likes = likes;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//@JsonIgnore
	public UserDto getUser() {
		return user;
	}
	
	//@JsonSetter 
	public void setUser(UserDto user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public PostsDto getPosts() {
		return posts;
	}
	
	
	public void setPosts(PostsDto posts) {
		this.posts = posts;
	}

	public int getPostsId() {
		return postsId;
	}

	public void setPostsId(int postsId) {
		this.postsId = postsId;
	}

	public String getCommentsContent() {
		return commentsContent;
	}

	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public boolean isLikes() {
		return likes;
	}

	public void setLikes(boolean likes) {
		this.likes = likes;
	}


	@Override
	public String toString() {
		return "CommentsDto [id=" + id + ", user=" + user + ", userId=" + userId + ", posts=" + posts + ", postsId="
				+ postsId + ", commentsContent=" + commentsContent + ", likesCount=" + likesCount + ", isAnonymous="
				+ isAnonymous + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ ", createdBy=" + createdBy + ", likes=" + likes + "]";
	}

	

	
	
}
