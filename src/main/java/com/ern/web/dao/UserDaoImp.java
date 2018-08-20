/**
 * 
 */
package com.ern.web.dao;

import java.io.Serializable;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	 
	   @Override
	   public void save(User user) {
	      sessionFactory.getCurrentSession().save(user);
	   }
	   
	   @SuppressWarnings("unchecked")
	   @Override
	   public List<User> list() {
		  System.out.println("Reached UserDao");
		  Session session=null;
		  try {
			session = sessionFactory.openSession();
			return (List<User>) session.createQuery("from User").getResultList();
		} catch (Exception e) {
			e.printStackTrace();  
            session.getTransaction().rollback();  
            return null;
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	      
	   }
	   
	   @Override
	   public User findUserByUsername(String username) {
	     return sessionFactory.getCurrentSession().get(User.class, username);
	   }
	   
	   public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

}
