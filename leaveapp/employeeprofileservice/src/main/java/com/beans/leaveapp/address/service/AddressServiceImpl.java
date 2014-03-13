package com.beans.leaveapp.address.service;

import java.util.List;

import javax.annotation.Resource;

import com.beans.leaveapp.address.model.Address;
import com.beans.leaveapp.address.repository.AddressRepository;

public class AddressServiceImpl implements AddressService {

	
	@Resource
	AddressRepository addressRepository;
	
	@Override
	public Address create(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address delete(int id) throws AddressNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address update(Address address) throws AddressNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address findById(int id) throws AddressNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findByEmployeeId(int employeeId) {
		List<Address> addressList = addressRepository.findByEmployeeId(employeeId);
		return addressList;
	}

}
