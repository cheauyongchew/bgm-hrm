package com.beans.leaveapp.jbpm6.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.transaction.support.AbstractPlatformTransactionManager;

public class JBPM6ServiceImpl {
	
	private EntityManagerFactory entityManagerFactory;
	private AbstractPlatformTransactionManager abstractPlatformTransactionManager;
	
	
	
	
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	public AbstractPlatformTransactionManager getAbstractPlatformTransactionManager() {
		return abstractPlatformTransactionManager;
	}
	public void setAbstractPlatformTransactionManager(
			AbstractPlatformTransactionManager abstractPlatformTransactionManager) {
		this.abstractPlatformTransactionManager = abstractPlatformTransactionManager;
	}
}
