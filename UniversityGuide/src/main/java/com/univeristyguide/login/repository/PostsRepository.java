package com.univeristyguide.login.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.univeristyguide.login.entity.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer>{
	
	@Query("from Posts p order by p.createdBy desc")
    List<Posts> findAllSortedByDateReverse();
	
	//HashMap<Integer, Posts> map = findAllSortedByDateReverse();
	
	@Query(value ="select * from university_guide.posts p where p.id_user =?1",nativeQuery=true)
	List<Posts> findByIdUser (final int id_user);
	
	@Query(value ="select * from university_guide.posts p where p.id_category =?1",nativeQuery=true)
	List<Posts> findByCategory (final int id_category);

}
