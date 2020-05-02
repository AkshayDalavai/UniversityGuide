package com.univeristyguide.login.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.univeristyguide.login.entity.Posts;

@Repository
@Transactional
public class PostSearch {

		// Spring will inject here the entity manager object
	  @PersistenceContext
	  private EntityManager entityManager;
	    
	  /**
	   * A basic search for the entity Posts. The search is done by exact match per
	   * keywords on fields title.
	  **/
	  public List<Posts> search(String text) {
	    
	    // get the full text entity manager
	    FullTextEntityManager fullTextEntityManager =
	        org.hibernate.search.jpa.Search.
	        getFullTextEntityManager(entityManager);
	    
	    // creating the query using Hibernate Search query DSL
	    QueryBuilder queryBuilder = 
	        fullTextEntityManager.getSearchFactory()
	        .buildQueryBuilder().forEntity(Posts.class).get();
	    
	    // a very basic query by keywords
	    org.apache.lucene.search.Query query =
	        queryBuilder
	          .keyword()
	          .onFields("title","postContent")
	          .matching(text)
	          .createQuery();

	    // wrap Lucene query in an Hibernate Query object
	    org.hibernate.search.jpa.FullTextQuery jpaQuery =
	        fullTextEntityManager.createFullTextQuery(query, Posts.class);
	  
	    // execute search and return results (sorted by relevance as default)
	    @SuppressWarnings("unchecked")
	    List<Posts> results = jpaQuery.getResultList();
	    
	    return results;
	  } 

}
