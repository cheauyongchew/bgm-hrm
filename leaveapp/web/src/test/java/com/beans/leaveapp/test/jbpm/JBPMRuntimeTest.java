package com.beans.leaveapp.test.jbpm;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.kie.api.task.model.TaskSummary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beans.leaveapp.jbpm6.service.JBPM6Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring-jbpm.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JBPMRuntimeTest {
	
	
	/*ApplicationContext applicationContext;
	JBPM6Service jbpm6Service;
	@Before
	public void setUp()
	{
		applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-jbpm.xml");
		jbpm6Service = (JBPM6Service) applicationContext.getBean("jbpm6Service");
		jbpm6Service.startProcess("com.sample.bpmn.hello");
	}

	
	
	//@Test
	public void getAssignedTaskForUser() {
		List<TaskSummary> taskList = jbpm6Service.getTaskAssignedForUser("john");
		Assert.assertEquals(1, taskList.size());		
	}
	
	//@Test
	public void submitTask() {
		List<TaskSummary> taskList = jbpm6Service.getTaskAssignedForUser("john");
		
		long taskId = taskList.get(0).getId();
		jbpm6Service.approveTask("john", taskId, new HashMap<String, Object>());
		
		Assert.assertNotNull(taskId);
	}*/
	
	@Test
	public void testNothing() {
		Assert.assertEquals(1, 1);
	}
}
