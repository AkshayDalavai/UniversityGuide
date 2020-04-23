package com.univeristyguide.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.univeristyguide.login.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
	
	@Query(value ="select * from university_guide.comments c where c.id_posts =?1",nativeQuery=true)
	List<Comments> findByPosts (final int postId);
}