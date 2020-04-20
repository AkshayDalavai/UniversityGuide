package com.univeristyguide.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univeristyguide.login.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
	
	List<Comments> findByPosts (final int postId);
}
