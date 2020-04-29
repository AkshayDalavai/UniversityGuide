package com.univeristyguide.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PostsLikes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="posts_id")
	private Posts post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="posts_likes")
	private boolean postsLikes;

	
	
	public boolean isPostsLikes() {
		return postsLikes;
	}

	public void setPostsLikes(boolean postsLikes) {
		this.postsLikes = postsLikes;
	}

		
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Posts getPost() {
		return post;
	}

	public void setPost(Posts post) {
		this.post = post;
	}

	


	public PostsLikes(int id, Posts post, User user, boolean postsLikes) {
		super();
		this.id = id;
		this.post = post;
		this.user = user;
		this.postsLikes = postsLikes;
	}

	
	
	public PostsLikes() {
		super();
	}

	@Override
	public String toString() {
		return "PostsLikes [id=" + id + ", post=" + post + ", user=" + user + ", postsLikes=" + postsLikes + "]";
	}

	
	

	
	
	
}
