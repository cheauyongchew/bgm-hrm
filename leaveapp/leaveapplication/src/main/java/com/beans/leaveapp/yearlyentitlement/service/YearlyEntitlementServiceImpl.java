package com.beans.leaveapp.yearlyentitlement.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.beans.leaveapp.employee.model.Employee;
import com.beans.leaveapp.employee.repository.EmployeeRepository;
import com.beans.leaveapp.leavetype.model.LeaveType;
import com.beans.leaveapp.leavetype.repository.LeaveTypeRepository;
import com.beans.leaveapp.yearlyentitlement.model.EmployeeEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.LeaveEntitlement;
import com.beans.leaveapp.yearlyentitlement.model.YearlyEntitlement;
import com.beans.leaveapp.yearlyentitlement.repository.YearlyEntitlementRepository;

public  class YearlyEntitlementServiceImpl implements YearlyEntitlementService{
	
	@Resource
    YearlyEntitlementRepository yearlyEntitleRepository;
	
	@Resource
    EmployeeRepository employeeRepository;
	
	@Resource
	LeaveTypeRepository leaveTypeRepository;
	
	YearlyEntitlement yearlyEntitlement = new YearlyEntitlement();
	
	LeaveEntitlement leaveEntitlement = new LeaveEntitlement();
	
	EmployeeEntitlement employeeEntitlement = new EmployeeEntitlement();
	
	
	
	

	public EmployeeEntitlement getEmployeeEntitlement() {
		return employeeEntitlement;
	}



	public void setEmployeeEntitlement(EmployeeEntitlement employeeEntitlement) {
		this.employeeEntitlement = employeeEntitlement;
	}



	public YearlyEntitlement getYearlyEntitlement() {
		return yearlyEntitlement;
	}



	public void setYearlyEntitlement(YearlyEntitlement yearlyEntitlement) {
		this.yearlyEntitlement = yearlyEntitlement;
	}




	public LeaveEntitlement getLeaveEntitlement() {
		return leaveEntitlement;
	}



	public void setLeaveEntitlement(LeaveEntitlement leaveEntitlement) {
		this.leaveEntitlement = leaveEntitlement;
	}



	@Override
	public List<YearlyEntitlement> findAll() {
		 List<YearlyEntitlement> yearlyEntitlementList = (List<YearlyEntitlement>) yearlyEntitleRepository.findByIsDeleted(0);
	
		return yearlyEntitlementList;
	}
	


	@Override
	public YearlyEntitlement update(YearlyEntitlement selectedYearlyEntitlement) throws Exception {
		YearlyEntitlement yearlyEntitlementToBeUpdated = yearlyEntitleRepository.findOne(selectedYearlyEntitlement.getId());
		
		if(yearlyEntitlementToBeUpdated != null) {

		yearlyEntitlementToBeUpdated.setAvailableBalance(selectedYearlyEntitlement.getAvailableBalance());
		yearlyEntitlementToBeUpdated.setEntitlement(selectedYearlyEntitlement.getEntitlement());
		yearlyEntitleRepository.save(yearlyEntitlementToBeUpdated);
		return yearlyEntitlementToBeUpdated;
		}
		return null;
	}

	@Override
	public YearlyEntitlement delete(int id) {
		
		YearlyEntitlement YearlyEntitlement= yearlyEntitleRepository.findOne(id);
			
			if(YearlyEntitlement != null){
				
				YearlyEntitlement.setDeleted(true);
				yearlyEntitleRepository.save(YearlyEntitlement);
			 return YearlyEntitlement;
			}
			return null;
		}	
	
	
	@SuppressWarnings("null")
	@Override
	public YearlyEntitlement create(LeaveEntitlement leaveEntitlement) {
		try{
		LeaveEntitlement yearlyEntitlementToBeCreated = leaveEntitlement;
		
		String name = yearlyEntitlementToBeCreated.getEmployeeName();
		Employee employeeObj = employeeRepository.findByName(name);
		int employeeId = employeeObj.getId();
		String leaveTypeName = yearlyEntitlementToBeCreated.getLeaveType();
		LeaveType leaveTypObj = leaveTypeRepository.findByName(leaveTypeName);
		int leaveTypObjId = leaveTypObj.getId();
		YearlyEntitlement finalYearlyEntitlementToBeCreated = new YearlyEntitlement();
		
		finalYearlyEntitlementToBeCreated.setEmployeeId(employeeId);
		finalYearlyEntitlementToBeCreated.setLeaveTypeId(leaveTypObjId);
		finalYearlyEntitlementToBeCreated.setEntitlement(yearlyEntitlementToBeCreated.getEntitlement());
		finalYearlyEntitlementToBeCreated.setAvailableBalance(yearlyEntitlementToBeCreated.getAvailableBalance());
		
		return yearlyEntitleRepository.save(finalYearlyEntitlementToBeCreated);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<LeaveEntitlement> findLeave() {
		 List<YearlyEntitlement> yearlyEntitlementList = (List<YearlyEntitlement>) yearlyEntitleRepository.findByIsDeleted(0);
		  List<LeaveEntitlement> leaveEntitlementList = new ArrayList<LeaveEntitlement>();
			if(yearlyEntitlementList != null){
			 for(YearlyEntitlement y :yearlyEntitlementList){
				 
				 Employee employeeObj = employeeRepository.findOne(y.getEmployeeId());
				 LeaveType leaveTypeObj = leaveTypeRepository.findOne(y.getLeaveTypeId());
				if(employeeObj != null || leaveTypeObj != null){
					 String name = null,leaveType = null;
					 
					 
				if(employeeObj.getName() != null){
				 name = employeeObj.getName();
				}
				
				if(leaveTypeObj.getName() != null){
				    // id =lt.getId();
				     leaveType = leaveTypeObj.getName();
				}
				 Double d = y.getEntitlement();
				 Double ab = y.getAvailableBalance();
				int id = y.getId();
				 System.out.println(leaveEntitlement.getEmployeeName()+" "+leaveEntitlement.getAvailableBalance()+ " " +leaveEntitlement.getEntitlement() + " "+leaveEntitlement.getId());			 
				 leaveEntitlementList.add(new LeaveEntitlement(id,d,ab,name,leaveType));
	             
				   
				}
			 }
			 }
			
			 System.out.println(yearlyEntitlementList.size());
			 System.out.println(leaveEntitlementList.size());
			return leaveEntitlementList;
		
	}



	@Override
	public List<String> employeeNames() {
		List<String> employeeNamesList = employeeRepository.findByNames();
		return employeeNamesList;
	}



	@Override
	public List<String> findLeaveTypes() {
		List<String> leavesNameList = (List<String>)leaveTypeRepository.findNamesList();
		return leavesNameList;
	}



	@Override
	public List<EmployeeEntitlement> findByEmployeeId(int x) {
		
		List<YearlyEntitlement> employeeYearlyEntitlementList =  (List<YearlyEntitlement>)yearlyEntitleRepository.findByEmployeeIdLike(x);
		List<EmployeeEntitlement> employeeEntitlementList  = new ArrayList<EmployeeEntitlement>();
		 for(YearlyEntitlement yearlyEntitlement : employeeYearlyEntitlementList){
			int leaveTypeId =  yearlyEntitlement.getLeaveTypeId();
			LeaveType leaveTypeObj =  leaveTypeRepository.findOne(leaveTypeId);
			String leaveTypeName = leaveTypeObj.getName();
			int id = yearlyEntitlement.getId();
			
			double entitlement = yearlyEntitlement.getEntitlement();
			double avaialbelBalance = yearlyEntitlement.getAvailableBalance();
			employeeEntitlementList.add(new EmployeeEntitlement(id,leaveTypeName,entitlement,avaialbelBalance));
			
		 }
		return employeeEntitlementList;
		
		
	}



	@Override
	public List<LeaveEntitlement> findByEmployee(int x) {
		List<YearlyEntitlement> yearlyEntitlementlist = yearlyEntitleRepository.findByEmployeeIdLike(x);
		 List<LeaveEntitlement> leaveEntitlementList = new ArrayList<LeaveEntitlement>();
		if(yearlyEntitlementlist != null){
			 for(YearlyEntitlement y :yearlyEntitlementlist){
				 
				 Employee employeeObj = employeeRepository.findOne(y.getEmployeeId());
				 LeaveType leaveTypeObj = leaveTypeRepository.findOne(y.getLeaveTypeId());
				if(employeeObj != null || leaveTypeObj != null){
					 String name = null,leaveType = null;
					 
					 
				if(employeeObj.getName() != null){
				 name = employeeObj.getName();
				}
				
				if(leaveTypeObj.getName() != null){
				    // id =lt.getId();
				     leaveType = leaveTypeObj.getName();
				}
				 Double d = y.getEntitlement();
				 Double ab = y.getAvailableBalance();
				int id = y.getId();
				 System.out.println(leaveEntitlement.getEmployeeName()+" "+leaveEntitlement.getAvailableBalance()+ " " +leaveEntitlement.getEntitlement() + " "+leaveEntitlement.getId());			 
				 leaveEntitlementList.add(new LeaveEntitlement(id,d,ab,name,leaveType));
	             
				   
				}
			 }
			 }
		
		return leaveEntitlementList;
	}



	@Override
	public List<LeaveEntitlement> findBySearchLeave(int leaveTypeId) {
		List<YearlyEntitlement> yearlyEntitlementlist = yearlyEntitleRepository.findByLeaveTypeIdLike(leaveTypeId);
		 List<LeaveEntitlement> leaveEntitlementList = new ArrayList<LeaveEntitlement>();
			if(yearlyEntitlementlist != null){
				 for(YearlyEntitlement y :yearlyEntitlementlist){
					 
					 Employee employeeObj = employeeRepository.findOne(y.getEmployeeId());
					 LeaveType leaveTypeObj = leaveTypeRepository.findOne(y.getLeaveTypeId());
					if(employeeObj != null || leaveTypeObj != null){
						 String name = null,leaveType = null;
					if(employeeObj.getName() != null){
					 name = employeeObj.getName();
					}
					
					if(leaveTypeObj.getName() != null){
					    // id =lt.getId();
					     leaveType = leaveTypeObj.getName();
					}
					 Double d = y.getEntitlement();
					 Double ab = y.getAvailableBalance();
					int id = y.getId();
					 System.out.println(leaveEntitlement.getEmployeeName()+" "+leaveEntitlement.getAvailableBalance()+ " " +leaveEntitlement.getEntitlement() + " "+leaveEntitlement.getId());			 
					 leaveEntitlementList.add(new LeaveEntitlement(id,d,ab,name,leaveType));
		             
					   
					}
				 }
				 }
			
			return leaveEntitlementList;
	}



	@Override
	public List<LeaveEntitlement> findByEmployeeIdAndfindByLeaveTypeId(String employeeName,String leaveType) {
		
		List<YearlyEntitlement> yearlyEntitlementlist = new  LinkedList<YearlyEntitlement>();
		boolean e = employeeName.trim().equals("");
		boolean ee = !e;
		if(employeeName == null || employeeName.trim().equals("")) {
			String leaveTypeSearchTerm = "%" + leaveType + "%";
			int leaveTypeId =leaveTypeRepository.findByName(leaveTypeSearchTerm).getId();
			 yearlyEntitlementlist =  yearlyEntitleRepository.findByLeaveTypeIdLike(leaveTypeId);
		
		} else if(leaveType == null || leaveType.trim().equals("")){
			String employeeNameSearchTerm = "%" + employeeName +"%";
			
			int name =employeeRepository.findByName(employeeNameSearchTerm).getId();
			 yearlyEntitlementlist =  yearlyEntitleRepository.findByLeaveTypeIdLike(name);
		}else{
			int leaveTypeId =leaveTypeRepository.findByName("%" + leaveType + "%").getId();
			int name =employeeRepository.findByName("%" + employeeName +"%").getId();
	       yearlyEntitlementlist= (List<YearlyEntitlement>)yearlyEntitleRepository.findByEmployeeIdAndLeaveTypeIdLike(leaveTypeId, name);
			
		}
		
		List<LeaveEntitlement> leaveEntitlementList = new ArrayList<LeaveEntitlement>();
		
		if(yearlyEntitlementlist != null){
			 for(YearlyEntitlement yearlyEntitlementObj :yearlyEntitlementlist){
				 
				 Employee employeeObj = employeeRepository.findOne(yearlyEntitlementObj.getEmployeeId());
				 LeaveType leaveTypeObj = leaveTypeRepository.findOne(yearlyEntitlementObj.getLeaveTypeId());
				if(employeeObj != null || leaveTypeObj != null){
					 String name = null,leaveTypeVar = null;
					 
					 
				if(employeeObj.getName() != null){
				 name = employeeObj.getName();
				}
				
				if(leaveTypeObj.getName() != null){
				    // id =lt.getId();
				     leaveType = leaveTypeObj.getName();
				}
				 Double d = yearlyEntitlementObj.getEntitlement();
				 Double ab = yearlyEntitlementObj.getAvailableBalance();
				int id = yearlyEntitlementObj.getId();
				 System.out.println(leaveEntitlement.getEmployeeName()+" "+leaveEntitlement.getAvailableBalance()+ " " +leaveEntitlement.getEntitlement() + " "+leaveEntitlement.getId());			 
				 leaveEntitlementList.add(new LeaveEntitlement(id,d,ab,name,leaveTypeVar));
	             
				   
				}
		
	}
		}
		return leaveEntitlementList;
	}
	
}

	

