package com.beans.leaveapp.jbpm6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;

public interface JBPM6Service {
	public List<TaskSummary> getTaskAssignedForUser(String username);
	public long startProcess(String processName);
	public long startProcess(String processName, Map<String, Object> parameterMap);
	public void approveTask(String actorId, long taskId, HashMap<String, Object> parameterMap);
	public List<TaskSummary> getTaskAssignedForUserForProcess(String username, String processName);
	public Task getTaskById(long id);
	public ProcessInstance getProcessInstanceById(long id);
	public Map<String, Object> getContentForTask(Task task);
}
