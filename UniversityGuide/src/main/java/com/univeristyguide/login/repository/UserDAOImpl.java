package com.univeristyguide.login.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.univeristyguide.login.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// define field for entitymanager	
		private EntityManager entityManager;
			
		// set up constructor injection
		@Autowired
		public UserDAOImpl(EntityManager theEntityManager) {
			entityManager = theEntityManager;
		}
		
	 	
		@Override
		public List<User> findAll() {

			// get the current hibernate session
			Session currentSession = entityManager.unwrap(Session.class);
			
			// create a query
			Query<User> theQuery =
					currentSession.createQuery("from User", User.class);
			
			// execute query and get result list
			List<User> theResultList = theQuery.getResultList();
			
			// return the results		
			return theResultList;
		}
}
