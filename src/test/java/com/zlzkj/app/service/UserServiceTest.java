package com.zlzkj.app.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.zlzkj.app.model.User;
import com.zlzkj.core.base.BaseSpringTest;
import com.zlzkj.core.util.Fn;

public class UserServiceTest extends BaseSpringTest{
	
	protected Log logger = LogFactory.getLog(UserServiceTest.class);
	
	@Autowired
	private UserService userService;
	
	@Test
	public void crud(){
		
		User user = new User();
		user.setLoginName("junit");
		user.setLoginPass("122333");
		user.setSex((byte) 1);
		user.setAddTime(Fn.time());
		
		//新增
		userService.save(user);
		int newId = user.getId();
		logger.info("@crud.add >>>> new user id:"+newId);
		Assert.assertTrue(newId>0);
		
		//查找
		User user1 = userService.findById(newId);
		logger.info("@crud.find >>>> "+JSON.toJSONString(user1));
		Assert.assertTrue(newId==user1.getId());

		//修改
		user1.setLoginName("junit-modified");
		userService.update(user1);
		User user2 = userService.findById(newId);
		Assert.assertTrue("junit-modified".equals(user2.getLoginName()));
		
		//删除
		int affected = userService.delete(newId);
		Assert.assertTrue(affected==1);
		
	}
	
//	@Test
//	public void findAll(){
//		
//		List<User> userList = userService.findAll();
//		
//		for(User user:userList){
//			logger.info("@findAll >>>> listed user id:"+user.getId());
//		}
//		
//		Assert.assertTrue(userList.size()>0);
//		
//	}
	
}
