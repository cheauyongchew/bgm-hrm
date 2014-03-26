package com.beans.leaveapp.leavetype.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.repository.LeaveTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring-masterdata.xml")
public class LeaveTypeRepositoryTest {

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    
    public void test() {
        LeaveType leaveType = new LeaveType();
        leaveType.setName("Test123");
        leaveType.setDescription("Test123Desc");
        leaveType.setDeleted(false);

        leaveTypeRepository.save(leaveType);
        LeaveType leaveType1 = leaveTypeRepository.findOne(leaveType.getId());
        org.junit.Assert.assertNotNull(leaveType1);
        System.out.println(leaveType1.getName());
    }
    
    
}
