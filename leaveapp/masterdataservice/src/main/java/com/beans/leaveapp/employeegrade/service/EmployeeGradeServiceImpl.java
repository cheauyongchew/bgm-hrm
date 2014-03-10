package com.beans.leaveapp.employeegrade.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.employeegrade.model.EmployeeGrade;
import com.beans.leaveapp.employeegrade.repository.EmployeeGradeRepository;

@Service
public class EmployeeGradeServiceImpl implements EmployeeGradeService {

	
	@Resource
	private EmployeeGradeRepository employeeGradeRepository;
	
	
	@Override
	@Transactional
	public EmployeeGrade create(EmployeeGrade employeeGrade){
		EmployeeGrade  employeeGradeTableCreate = employeeGrade;
		return  employeeGradeRepository.save(employeeGradeTableCreate);	
	}

	
	@Override
	@Transactional
	public EmployeeGrade deleted(int id) {
		
		EmployeeGrade employeeGrade = employeeGradeRepository.findOne(id);
		if(employeeGrade != null) {
			 employeeGrade.setDeleted(false);
			
			System.out.println(employeeGrade.isDeleted());
			
			employeeGradeRepository.save(employeeGrade);
		
			 return employeeGrade;
		}
		return null;
	}

	@Override
	@Transactional
	public List<EmployeeGrade> findAll()  {
		 List<EmployeeGrade> ref = employeeGradeRepository.findByisDeleted(1);
		 System.out.println("Size: " +ref.size());
		 return ref;
		
	}

	
	
	@Override
	@Transactional
	public EmployeeGrade update(EmployeeGrade employeeGrade) {
		EmployeeGrade employeeGradeToBeUpdated = employeeGradeRepository.findOne(employeeGrade.getId());
		
		if(employeeGradeToBeUpdated != null){
		employeeGradeToBeUpdated.setName(employeeGrade.getName());
		employeeGradeToBeUpdated.setDescription(employeeGrade.getDescription());
		employeeGradeToBeUpdated.setDeleted(employeeGrade.isDeleted());
		employeeGradeRepository.save(employeeGradeToBeUpdated);
		return employeeGradeToBeUpdated;
		}
		return null;
	}
	

	@Override
	@Transactional
	public EmployeeGrade findById(int id) {
		// TODO Auto-generated method stub
		EmployeeGrade employeeGrade = employeeGradeRepository.findOne(id);
		if(employeeGrade == null){	
			return null;
		}
		
		return employeeGrade;
	}
	

}
