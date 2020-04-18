package com.univeristyguide.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univeristyguide.login.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
	
	List<Comments> findByPostId (final int postId);
}
