package com.beans.leaveapp.employee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskData;
import org.kie.api.task.model.TaskSummary;

import com.beans.leaveapp.employee.model.RegisteredEmployee;
import com.beans.leaveapp.jbpm6.service.JBPM6Service;

public class EmployeeRegistrationServiceImpl implements
		EmployeeRegistrationService {

	private JBPM6Service jbpm6Service;
	private static final String PROCESS_NAME = "com.beans.leaveapp.bpmn.empreg";
	
	@Override
	public void submitRegistration(HashMap<String, Object> parameterMap) {
		jbpm6Service.startProcess(PROCESS_NAME, parameterMap);
		
	}
	
	@Override
	public List<RegisteredEmployee> getPendingRegisteredEmployee(String username) {
		List<RegisteredEmployee> resultList = new ArrayList<RegisteredEmployee>();
		List<TaskSummary> taskList = jbpm6Service.getTaskAssignedForUser(username);
		Iterator<TaskSummary> taskIterator = taskList.iterator();
		while(taskIterator.hasNext()) {
			TaskSummary currentTaskSummary = taskIterator.next();
			Task currentTask = jbpm6Service.getTaskById(currentTaskSummary.getId());
			Map<String, Object> contentMap = jbpm6Service.getContentForTask(currentTask);
			RegisteredEmployee registeredEmployee = mapTaskSummaryToRegisteredEmployee(currentTaskSummary, contentMap);
			resultList.add(registeredEmployee);
		}
		return resultList;
	}

	private RegisteredEmployee mapTaskSummaryToRegisteredEmployee(TaskSummary taskSummary, Map<String, Object> contentMap) {
		RegisteredEmployee registeredEmployee = new RegisteredEmployee();
		registeredEmployee.setTaskId(taskSummary.getId());
		registeredEmployee.setRegistrationDate(taskSummary.getCreatedOn());
		
		if(contentMap != null) {
			String fullname = (String) contentMap.get("fullname");
			registeredEmployee.setFullname(fullname);
			
			String username = (String) contentMap.get("username");
			registeredEmployee.setUsername(username);
			
			String password = (String) contentMap.get("password");
			registeredEmployee.setPassword(password);
			
			String passportNumber = (String) contentMap.get("passportNumber");
			registeredEmployee.setPassportNumber(passportNumber);
			
			String personalEmailAddress = (String) contentMap.get("personalEmailAddress");
			registeredEmployee.setPersonalEmailAddress(personalEmailAddress);
			
			String personalPhoneNumber = (String) contentMap.get("personalPhoneNumber");
			registeredEmployee.setPersonalPhoneNumber(personalPhoneNumber);
			
			String gender = (String) contentMap.get("gender");
			registeredEmployee.setGender(gender);
			
			String idNumber = (String) contentMap.get("idNumber");
			registeredEmployee.setIdNumber(idNumber);
		}
		
		return registeredEmployee;
	}


	public JBPM6Service getJbpm6Service() {
		return jbpm6Service;
	}
	public void setJbpm6Service(JBPM6Service jbpm6Service) {
		this.jbpm6Service = jbpm6Service;
	}
	
}
