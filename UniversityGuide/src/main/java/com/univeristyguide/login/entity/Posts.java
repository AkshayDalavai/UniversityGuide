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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name="posts")
public class Posts extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user",nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_category",nullable=false)
	private Category category;
	
	@Field
	@Column(name="title",nullable=false)
	private String title;
	
	@Column(name="has_comments",nullable=false)
	private boolean hasComments;
	
	@Field
	@Column(name="posts_content",nullable=false)
	private String postContent;
	
	@Column(name="likes_count")
	private int likesCount;
	
	@Column(name="is_anonymous",nullable=false)
	private boolean isAnonymous;
	
	@Column(name="comments_count")
	private int commentsCount;
	
	
	public Posts() {
		
	}


	


	public Posts(int id, User user, Category category, String title, boolean hasComments, String postContent,
			int likesCount, boolean isAnonymous, int commentsCount) {
		
		this.id = id;
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
				+ isAnonymous + ", commentsCount=" + commentsCount + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getLastModifiedBy()=" + getLastModifiedBy()
				+ ", getLastModifiedDate()=" + getLastModifiedDate() + "]";
	}


	

	
}
