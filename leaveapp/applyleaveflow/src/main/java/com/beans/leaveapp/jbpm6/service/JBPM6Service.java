package com.beans.leaveapp.jbpm6.service;

import java.util.HashMap;
import java.util.List;

import org.kie.api.task.model.TaskSummary;

public interface JBPM6Service {
	public List<TaskSummary> getTaskAssignedForUser(String username);
	public long startProcess(String processName);
	public void approveTask(String actorId, long taskId, HashMap<String, Object> parameterMap);
	public List<TaskSummary> getTaskAssignedForUserForProcess(String username, String processName);
}
