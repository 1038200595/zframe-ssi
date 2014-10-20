package com.zlzkj.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlzkj.app.mapper.UserMapper;
import com.zlzkj.app.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public Integer delete(Integer id){
		return userMapper.deleteByPrimaryKey(id);
	}
	
	public Integer update(User user){
		return userMapper.updateByPrimaryKey(user);
	}
	
	public void save(User user) {
		userMapper.insert(user);
	}
	
	public List<User> findAll() {
		return userMapper.selectAll();
	}
	
	public User findById(Integer id){
		return userMapper.selectByPrimaryKey(id);
	}
	
}

