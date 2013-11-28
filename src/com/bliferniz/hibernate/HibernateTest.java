/**
 * 
 */
package com.bliferniz.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.bliferniz.dto.UserDetails;

public class HibernateTest {

	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {

		Session session = factory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.or(Restrictions.gt("userId", 5), Restrictions.eq("userName", "User2"))); //gt := Greater then
		
		List<UserDetails> users = (List<UserDetails>) criteria.list();
		
		session.getTransaction().commit();
		session.close();

		for(UserDetails user : users){
			System.out.println(user.getUserId() + " : " + user.getUserName());
		}		
	}

	
}
