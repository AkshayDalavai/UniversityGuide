package com.univeristyguide.login.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Posts extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category category;
	
	@Column(name="title")
	private String title;
	
	@Column(name="has_comments")
	private boolean hasComments;
	
	@Column(name="posts_content")
	private String postContent;
	
	@Column(name="likes_count")
	private int likesCount;
	
	@Column(name="is_anonymous")
	private boolean isAnonymous;
	
	@Column(name="comments_count")
	private int commentsCount;
	
	
	public Posts() {
		
	}


	public Posts(User user, Category category, String title, boolean hasComments, String postContent, int likesCount,
			boolean isAnonymous, int commentsCount) {
		super();
		this.user = user;
		this.category = category;
		this.title = title;
		this.hasComments = hasComments;
		this.postContent = postContent;
		this.likesCount = likesCount;
		this.isAnonymous = isAnonymous;
		this.commentsCount = commentsCount;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHasComments() {
		return hasComments;
	}

	public void setHasComments(boolean hasComments) {
		this.hasComments = hasComments;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
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



	public int getCommentsCount() {
		return commentsCount;
	}



	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}


	@Override
	public String toString() {
		return "Posts [id=" + id + ", user=" + user + ", category=" + category + ", title=" + title + ", hasComments="
				+ hasComments + ", postContent=" + postContent + ", likesCount=" + likesCount + ", isAnonymous="
				+ isAnonymous + ", commentsCount=" + commentsCount + "]";
	}

	
}
