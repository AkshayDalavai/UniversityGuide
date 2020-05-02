package com.univeristyguide.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.univeristyguide.login.entity.CommentsLikes;


public interface CommentsLikesRepository extends JpaRepository<CommentsLikes, Integer> {

	@Query(value = "select * from university_guide.commentslikes l where l.id_comments = ?1", nativeQuery=true)
	List<CommentsLikes> findByIdComments (final int id_comments);
	
	@Query(value = "select * from university_guide.commentslikes l where l.id_user = ?1", nativeQuery=true)
	List<CommentsLikes> findByIdUser(final int id_user);
	
	@Query(value = "select * from university_guide.commentslikes l where l.id_user = ?1 and l.id_comments = ?1", nativeQuery = true)
	CommentsLikes findByIdUserAndIdComments(final int id_user, final int id_comments);
	
}