/**
 * 
 */
package com.ern.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ern.web.model.Roles;

/**
 * @author sandeep chauhan
 *
 */
@Transactional(readOnly = true)
@Repository
public class RolesDaoImp  implements RolesDao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Roles> list() {
		Session session=null;
		  try {
			session = sessionFactory.openSession();
			return (List<Roles>) session.createQuery("from Roles").getResultList();
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

}
