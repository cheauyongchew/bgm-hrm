package com.beans.leaveapp.department.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.beans.leaveapp.department.model.DepartmentDataModel;
import com.beans.leaveapp.department.model.Department;
import com.beans.leaveapp.department.service.DepartmentNotFound;
import com.beans.leaveapp.department.service.DepartmentService;



public class DepartmentManagementBean implements Serializable{
private static final long serialVersionUID = 1L;
	
	
	DepartmentService departmentService;
	private List<Department> departmentList;
	private DepartmentDataModel departmentDataModel;
	private Department newDepartment = new Department();
	private Department selectedDepartment = new Department();
	private boolean insertDeleted = false;
	

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public Department getNewDepartment() {
		return newDepartment;
	}

	public void setNewDepartment(Department newDepartment) {
		this.newDepartment = newDepartment;
	}

	public Department getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(Department selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

	public boolean isInsertDeleted() {
		return insertDeleted;
	}

	public void setInsertDelete(boolean insertDeleted) {
		this.insertDeleted = insertDeleted;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public List<Department> getDepartmentList() {
		if(departmentList == null || insertDeleted == true) {
			 try {
				departmentList = getDepartmentService().findAll();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return departmentList;	
	}
	
	public DepartmentDataModel getDepartmentDataModel() {
		if(departmentDataModel == null || insertDeleted == true) {
			departmentDataModel = new DepartmentDataModel(getDepartmentList());
		}
		
		return departmentDataModel;
	}

	public void setDepartmentDataModel(
			DepartmentDataModel departmentDataModel) {
		this.departmentDataModel = departmentDataModel;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public void doCreateDepartment() throws DepartmentNotFound {
		newDepartment.setDeleted(false);
		getDepartmentService().create(newDepartment);
		setInsertDelete(true);
	}
	
	public void doUpdateDepartment() throws DepartmentNotFound {
		try {
			System.out.println("New name:" + selectedDepartment.getName());
			System.out.println("ID: " + selectedDepartment.getId());
			getDepartmentService().update(selectedDepartment);
		// RequestContext.getCurrentInstance().
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage("Error", "Department With id: " + selectedDepartment.getId() + " not found!");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
	}
	
	public void onRowSelect(SelectEvent event) {  
		setSelectedDepartment((Department) event.getObject());
        FacesMessage msg = new FacesMessage("Department Selected", selectedDepartment.getName());  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
	
	
	public void doDeleteDepartment() throws Exception, DepartmentNotFound {
		try {
			getDepartmentService().delete(selectedDepartment.getId());
		} catch(DepartmentNotFound e) {
			FacesMessage msg = new FacesMessage("Error", "Department With id: " + selectedDepartment.getId() + " not found!");  
			  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
		}
		
		setInsertDelete(true);
	}
	
	public void doResetFrom() throws DepartmentNotFound {
		
	}
}
