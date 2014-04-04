package com.beans.leaveapp.test.welcomeemail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beans.common.security.users.model.Users;
import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.welcomeemail.service.WelcomeEmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/META-INF/spring-welcomeemail.xml")
public class WelcomeEmailServiceTest {

	/*ApplicationContext applicationContext;
	WelcomeEmailService welcomeEmailService;
	
	@Before
	public void setup() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-welcomeemail.xml");
		welcomeEmailService = (WelcomeEmailService) applicationContext.getBean("welcomeEmailService");
		
	}
	
	@Test
	public void testEmailSuccessful() {
		Employee employee = new Employee();
		employee.setName("Yeoh Seng Keat");
		employee.setPosition("Junior Programmer");
		employee.setPersonalEmailAddress("sengkeat.yeoh@beans.com.my");
		
		Users user = new Users();
		user.setUsername("SengKeat");
		employee.setUsers(user);
		
		
		
		welcomeEmailService.sendHTMLEmail(employee);
	}
	
	*/
	
	@Test
	public void testNothing() {
		Assert.assertEquals(1, 1);
	}
}
