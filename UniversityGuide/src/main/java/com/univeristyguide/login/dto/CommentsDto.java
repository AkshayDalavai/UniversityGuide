package com.univeristyguide.login.dto;

import java.util.Date;

import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.entity.User;

public class CommentsDto {
	
	private int id;
	
	private User user;
	
	private Posts posts;
	
	private String commentsContent;
	
	private int likes;
	
	private boolean isAnonymous;
	
	private Date createdDate;
	
	private Date lastModifiedDate;
	
	private String createdBy;

	public CommentsDto() {
		
	}

	

	public CommentsDto(int id, User user, Posts posts, String commentsContent, int likes, boolean isAnonymous,
			Date createdDate, Date lastModifiedDate, String createdBy) {
		
		this.id = id;
		this.user = user;
		this.posts = posts;
		this.commentsContent = commentsContent;
		this.likes = likes;
		this.isAnonymous = isAnonymous;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.createdBy = createdBy;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	public String getCommentsContent() {
		return commentsContent;
	}

	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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

	@Override
	public String toString() {
		return "CommentsDto [id=" + id + ", user=" + user + ", posts=" + posts + ", commentsContent=" + commentsContent
				+ ", likes=" + likes + ", isAnonymous=" + isAnonymous + ", createdDate=" + createdDate
				+ ", lastModifiedDate=" + lastModifiedDate + ", createdBy=" + createdBy + "]";
	}
	
	
	
}
