package com.zlzkj.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zlzkj.app.model.User;
import com.zlzkj.app.service.UserService;
import com.zlzkj.core.base.BaseController;

/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/"})
	public void index(HttpServletRequest request,HttpServletResponse response) {
		
//		User test = new User();
//		test.setAddTime(Fn.time());
//		test.setLoginName("simon");
//		test.setLoginPass("123456");
//		test.setSex((byte) 1);
//		userService.save(test);
		
		//User user = userService.find();
		
		List<User> userList = userService.findAll();
		
		ajaxReturn(response, userList);
	}
	
	
	
}
