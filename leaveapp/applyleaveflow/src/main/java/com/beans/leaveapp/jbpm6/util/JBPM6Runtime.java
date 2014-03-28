package com.beans.leaveapp.jbpm6.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.EnvironmentName;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import com.beans.util.config.ConfigurationHolder;

public class JBPM6Runtime {
	private static JBPM6Runtime jbpm6Runtime;
	private static RuntimeManager manager;
	private HashMap<String, ProcessInstance> processInstanceMap;
	
	private JBPM6Runtime(EntityManagerFactory entityManagerFactory, AbstractPlatformTransactionManager abstractPlatformTransactionManager) {
		List<String> bpmnList = ConfigurationHolder.getStringList("bpmn.file");
		KieServices kservices = KieServices.Factory.get();
		RuntimeEnvironmentBuilder runtimeEnvironmentBuilder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder();
		Iterator<String> bpmnIterator = bpmnList.iterator();
		while(bpmnIterator.hasNext()) {
			String currentBPMNFile = bpmnIterator.next();
			
			runtimeEnvironmentBuilder.addAsset(kservices.getResources().newClassPathResource(currentBPMNFile), ResourceType.BPMN2);
		}
		runtimeEnvironmentBuilder.addEnvironmentEntry(EnvironmentName.TRANSACTION_MANAGER, abstractPlatformTransactionManager);
		runtimeEnvironmentBuilder.entityManagerFactory(entityManagerFactory);
		
		RuntimeEnvironment runtimeEnvironment = runtimeEnvironmentBuilder.get();
		manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(runtimeEnvironment);
        
		processInstanceMap = new HashMap<String, ProcessInstance>();	
		
	}
	
	
	public static JBPM6Runtime getRuntime(EntityManagerFactory entityManagerFactory, AbstractPlatformTransactionManager abstractPlatformTransactionManager) {
		if(jbpm6Runtime == null) {
			jbpm6Runtime = new JBPM6Runtime(entityManagerFactory, abstractPlatformTransactionManager);
		}
		
		
		return jbpm6Runtime;
	}
	
	public List<TaskSummary> getTaskAssignedForUser(String username) {
		RuntimeEngine runtimeEngine = manager.getRuntimeEngine(EmptyContext.get());	
        TaskService taskService = runtimeEngine.getTaskService();
        return taskService.getTasksAssignedAsPotentialOwner(username, "en-UK");
        
	}
	
	public List<TaskSummary> getTaskAssignedForUserForProcess(String username, String processName) {
		ProcessInstance processInstance = getProcessInstance(processName);
		List<TaskSummary> resultList = new ArrayList<TaskSummary>();
		
		if(processInstance != null) {
			List<TaskSummary> taskList = getTaskAssignedForUser(username);
			Iterator<TaskSummary> taskIterator = taskList.iterator();
			while(taskIterator.hasNext()) {
				TaskSummary currentTask = taskIterator.next();
				if(currentTask.getProcessInstanceId() == processInstance.getId()) {
					resultList.add(currentTask);
				}
			}
		}
		
		return resultList;
	}
	
	public Task getTaskById(long id) {
		RuntimeEngine runtimeEngine = manager.getRuntimeEngine(EmptyContext.get());	
		TaskService taskService = runtimeEngine.getTaskService();
		return taskService.getTaskById(id);
	}
	
	public List<Long> getTaskIdsByProcessInstanceId(long processInstanceId) {
		RuntimeEngine runtimeEngine = manager.getRuntimeEngine(EmptyContext.get());	
		TaskService taskService = runtimeEngine.getTaskService();
		return taskService.getTasksByProcessInstanceId(processInstanceId);
	}
	
	public List<Task> getTasksByProcessInstanceId(long processInstanceId) {
		List<Task> resultList = new ArrayList<Task>();
		List<Long> taskIdList = getTaskIdsByProcessInstanceId(processInstanceId);
		Iterator<Long> taskIdIterator = taskIdList.iterator();
		while(taskIdIterator.hasNext()) {
			Long currentTaskId = taskIdIterator.next();
			Task currentTask = getTaskById(currentTaskId);
			resultList.add(currentTask);
		}
		
		return resultList;
	}
	
	public ProcessInstance getProcessInstance(String processName) {
		if(processInstanceMap.containsKey(processName)) {
			return processInstanceMap.get(processName);
		} else {
			return null;
		}
	}
	
	public long startProcess(String processName) {
		if(processInstanceMap.containsKey(processName)) {
			return processInstanceMap.get(processName).getId();
		} else {
			RuntimeEngine runtimeEngine = manager.getRuntimeEngine(EmptyContext.get());	
			
			KieSession ksession = runtimeEngine.getKieSession();
			ProcessInstance processInstance = ksession.startProcess(processName);
			processInstanceMap.put(processName, processInstance);
			return processInstance.getId();
		}
	}
	
	public void submitTask(String username, long taskId, HashMap<String, Object> parameterMap) {
		RuntimeEngine runtimeEngine = manager.getRuntimeEngine(EmptyContext.get());
		
		TaskService taskService = runtimeEngine.getTaskService();
		
		taskService.start(taskId, username);
		taskService.complete(taskId, username, parameterMap);
	}
}