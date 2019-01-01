/**
 * 
 */
package com.ern.web.dao;

import java.io.Serializable;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ern.web.model.User;

/**
 * @author Sandeep
 *
 */
@Repository
public class UserDaoImp implements UserDao, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * (non-Javadoc)
	 * @see com.ern.web.dao.UserDao#list()
	 */
   @SuppressWarnings("unchecked")
   @Transactional(readOnly = true)
   @Override
   public List<User> list() {
	  Session session=null;
	  try {
			session = sessionFactory.openSession();
			return (List<User>) session.createQuery("from User").getResultList();
		} 
	  catch (Exception e) {
			e.printStackTrace();  
            session.getTransaction().rollback();  
            return null;
		} 
	  finally {
			if(session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	      
	   }
	   
   	   /*
   	    * (non-Javadoc)
   	    * @see com.ern.web.dao.UserDao#findUserByUsername(java.lang.String)
   	    */
       @Transactional(readOnly = true)
   	   @Override
	   public User findUserByUsername(String username) {
	     return sessionFactory.getCurrentSession().get(User.class, username);
	   }
	   
	   /*
	    * Get SessionFactory
	    */
	   public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

	   /*
	    * Set sessionFactory
	    */
	   public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
		/*
		 * (non-Javadoc)
		 * @see com.ern.web.dao.UserDao#addUser(com.ern.web.model.User)
		 */
		@Transactional(readOnly = false)
		@Override
		public void addUser(User user) {
			getSessionFactory().getCurrentSession().saveOrUpdate(user);
			
		}

		/*
		 * (non-Javadoc)
		 * @see com.ern.web.dao.UserDao#deleteUser(com.ern.web.model.User)
		 */
		@Transactional(readOnly = false)
		@Override
		public void deleteUser(User user) {
			getSessionFactory().getCurrentSession().delete(user);
			
		}

}
