package com.univeristyguide.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.entity.PostsLikes;
import com.univeristyguide.login.entity.User;

public interface PostsLikesRepository extends JpaRepository<PostsLikes, Integer> {

	@Query(value ="select * from university_guide.postslikes l where l.id_user =?1",nativeQuery=true)
	List<Posts> findByIdUser (final int id_user);

	
	@Query(value = "select * from university_guide.postslikes l where l.id_posts = ?1", nativeQuery=true)
	List<User> findByIdPosts (final int id_posts);
	
	@Query(value = "select * from university_guide.postsLikes l where l.id_user = ?1 and l.id_posts = ?1", nativeQuery = true)
	PostsLikes findByIdUserAndIdPosts(final int id_user, final int id_posts);
	
}