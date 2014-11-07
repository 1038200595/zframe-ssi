package com.zlzkj.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.zlzkj.app.mapper.AdminMapper;
import com.zlzkj.app.model.Admin;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminMapper mapper;
	
	public Integer delete(Integer id){
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Integer update(Admin entity){
		return mapper.updateByPrimaryKey(entity);
	}
	
	public void save(Admin entity) {
		mapper.insert(entity);
	}
	
	public List<Admin> findAll() {
		return mapper.selectAll();
	}
	
	public Admin findById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}
	
}

