package com.beans.leaveapp.jbpm6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.UserGroupCallback;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import com.beans.leaveapp.jbpm6.util.JBPM6Runtime;

public class JBPM6ServiceImpl implements JBPM6Service{
	
	private EntityManagerFactory entityManagerFactory;
	private AbstractPlatformTransactionManager abstractPlatformTransactionManager;
	private UserGroupCallback userGroupCallback;
	
	@Override
	public List<TaskSummary> getTaskAssignedForUser(String username) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		return jbpm6Runtime.getTaskAssignedForUser(username);
	}
	
	@Override
	public long startProcess(String processName) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		return jbpm6Runtime.startProcess(processName);
		
	}
	
	@Override
	public long startProcess(String processName,
			Map<String, Object> parameterMap) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		return jbpm6Runtime.startProcessWithInitialParameters(processName, parameterMap);
	}

	@Override
	public void approveTask(String actorId, long taskId,
			HashMap<String, Object> parameterMap) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		jbpm6Runtime.submitTask(actorId, taskId, parameterMap);
		
	}

	@Override
	public List<TaskSummary> getTaskAssignedForUserForProcess(String username,
			String processName) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		return jbpm6Runtime.getTaskAssignedForUserForProcess(username, processName);
	}

	@Override
	public Task getTaskById(long id) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		return jbpm6Runtime.getTaskById(id);
	}

	@Override
	public ProcessInstance getProcessInstanceById(long id) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		return jbpm6Runtime.findProcessInstance(id);
	}

	@Override
	public Map<String, Object> getContentForTask(Task task) {
		JBPM6Runtime jbpm6Runtime = JBPM6Runtime.getRuntime(entityManagerFactory, abstractPlatformTransactionManager, userGroupCallback);
		return null;
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
	
	public UserGroupCallback getUserGroupCallback() {
		return userGroupCallback;
	}
	public void setUserGroupCallback(UserGroupCallback userGroupCallback) {
		this.userGroupCallback = userGroupCallback;
	}
}

