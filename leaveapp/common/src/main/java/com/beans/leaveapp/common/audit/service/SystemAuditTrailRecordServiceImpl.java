package com.beans.leaveapp.common.audit.service;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.leaveapp.audit.repository.SystemAuditTrailRecordRepository;
import com.beans.leaveapp.common.audit.model.SystemAuditTrail;

@Service
public class SystemAuditTrailRecordServiceImpl implements SystemAuditTrailRecordService {

	@Resource
	SystemAuditTrailRecordRepository systemAuditTrailRecordRepository;
	
	
	
	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

	
	
	@Override
	@Transactional
	public SystemAuditTrail create(SystemAuditTrail systemAuditTrail) {
		SystemAuditTrail s = systemAuditTrail;
		return systemAuditTrailRecordRepository.save(s);
	}

	@Override
	@Transactional
	public SystemAuditTrail delete(int id) throws Exception {
		SystemAuditTrail ss = systemAuditTrailRecordRepository.findOne(id);
		if(ss == null) throw new Exception();
			ss.setDeleted(false);
			systemAuditTrailRecordRepository.save(ss);
			return ss;
	}

	@SuppressWarnings( "deprecation")
	@Override
	public List<SystemAuditTrail> findAll() {
		// List<SystemAuditTrail> l =  (List<SystemAuditTrail>) systemAuditTrailRecordRepository.findAll();
		List<SystemAuditTrail> l =  systemAuditTrailRecordRepository.findByisDeleted(1);
		
		
		return l;
	}

	@Override
	public SystemAuditTrail update(SystemAuditTrail systemAuditTrail)
			throws Exception {
		SystemAuditTrail systemAuditTrailRecord =  systemAuditTrailRecordRepository.findOne(systemAuditTrail.getId());
		if(systemAuditTrailRecord == null)
			throw new Exception();
		systemAuditTrailRecord.setDate(systemAuditTrail.getDate());
		systemAuditTrailRecord.setActorUserId(systemAuditTrail.getActorUserId());
		systemAuditTrailRecord.setDescription(systemAuditTrail.getDescription());
		systemAuditTrailRecord.setDeleted(systemAuditTrail.isDeleted());
		systemAuditTrailRecordRepository.save(systemAuditTrailRecord);
		return systemAuditTrailRecord;
	}

	@Override
	public SystemAuditTrail findById(int id) throws Exception {
		SystemAuditTrail systemAuditTrail  = systemAuditTrailRecordRepository.findOne(id);
		if(systemAuditTrail == null)
			throw new Exception();
		return systemAuditTrail;
		
	}

	@Override
	public List<SystemAuditTrail> findSelectedDates(Date x, Date y) {
		
	
		//java.util.Date d1 = new java.util.Date();
		//ja
		
		//Date d1 = new Date("15-03-2013");
	// 	Date d2 = new Date("16-03-2014");
		
		
		//s.format(d1);  s.parse(source);
		//s.format(d2);
		List<SystemAuditTrail> l = systemAuditTrailRecordRepository.findByDate(x, y);
		System.out.println(l.size());
		return l;
	}
}
