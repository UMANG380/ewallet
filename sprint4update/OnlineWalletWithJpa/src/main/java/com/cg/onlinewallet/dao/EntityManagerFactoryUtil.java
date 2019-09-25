/**
 * 
 */
package com.cg.onlinewallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Admin
 *
 */
public class EntityManagerFactoryUtil {
	private static EntityManagerFactory entityManagerFactory;
	
	private EntityManagerFactoryUtil() {}
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
	}
	public static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory==null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
		}
		return entityManagerFactory;
	}
	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		EntityManagerFactoryUtil.entityManagerFactory = entityManagerFactory;
	}
}
