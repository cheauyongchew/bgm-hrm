package com.beans.leaveapp.jbpm6.service;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.kie.api.task.model.TaskSummary;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import com.beans.leaveapp.jbpm6.util.JBPM6Runtime;

public class JBPM6ServiceImpl implements JBPM6Service{
	
	private EntityManagerFactory entityManagerFactory;
	private AbstractPlatformTransactionManager abstractPlatformTransactionManager;
	
	@Override
	public List<TaskSummary> getTaskAssignedForUser(String username) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager);
		return jbpm6Runtime.getTaskAssignedForUser(username);
	}
	
	@Override
	public long startProcess(String processName) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager);
		return jbpm6Runtime.startProcess(processName);
		
	}
	
	@Override
	public void approveTask(String actorId, long taskId,
			HashMap<String, Object> parameterMap) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager);
		jbpm6Runtime.submitTask(actorId, taskId, parameterMap);
		
	}

	@Override
	public List<TaskSummary> getTaskAssignedForUserForProcess(String username,
			String processName) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager);
		return jbpm6Runtime.getTaskAssignedForUserForProcess(username, processName);
	}



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