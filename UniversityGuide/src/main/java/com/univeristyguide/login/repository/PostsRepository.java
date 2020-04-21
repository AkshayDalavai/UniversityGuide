package com.univeristyguide.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.univeristyguide.login.entity.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer>{
	
	@Query("from Posts p order by p.createdBy desc")
    List<Posts> findAllSortedByDateReverse();
	
	List<Posts> findByUser (final int userId);
	
	List<Posts> findByCategory (final int categoryId);

}
