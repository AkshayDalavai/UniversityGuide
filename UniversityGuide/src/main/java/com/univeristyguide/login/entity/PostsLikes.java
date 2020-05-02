package com.univeristyguide.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="postslikes")
public class PostsLikes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_posts", nullable=false)
	private Posts posts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user", nullable=false)
	private User user;
	
	@Column(name="posts_likes")
	private boolean postsLikes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPostsLikes() {
		return postsLikes;
	}

	public void setPostsLikes(boolean postsLikes) {
		this.postsLikes = postsLikes;
	}

	@Override
	public String toString() {
		return "PostsLikes [id=" + id + ", posts=" + posts + ", user=" + user + ", postsLikes=" + postsLikes + "]";
	}

	public PostsLikes(int id, Posts posts, User user, boolean postsLikes) {
		super();
		this.id = id;
		this.posts = posts;
		this.user = user;
		this.postsLikes = postsLikes;
	}

	public PostsLikes() {
		super();
	}
	
}