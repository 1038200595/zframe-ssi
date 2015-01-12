package com.zlzkj.app.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zlzkj.app.model.Admin;
import com.zlzkj.core.base.BaseSpringTest;
import com.zlzkj.core.mybatis.SqlRunner;
import com.zlzkj.core.sql.Row;
import com.zlzkj.core.sql.SQLBuilder;

public class AdminServiceTest extends BaseSpringTest{
	
	protected Log logger = LogFactory.getLog(AdminServiceTest.class);
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private SqlRunner sqlRunner;
	
	/**
	 * 测试单个增删改查
	 */
	@Test
	@Transactional
	public void crud(){
		
		Admin admin = new Admin();
		admin.setLoginName("junit");
		admin.setLoginPass("122333");
		admin.setNickname("junit测试");
		admin.setEmail("j@qq.com");
		admin.setMobile("13800009999");
		admin.setRemark("测试备注");
		
		//新增
		adminService.save(admin);
		int newId = admin.getId();
		logger.info("@crud.add >>>> new insert id:"+newId);
		Assert.assertTrue(newId>0);
		
		//查找
		Admin admin1 = adminService.findById(newId);
		logger.info("@crud.find >>>> "+JSON.toJSONString(admin1));
		Assert.assertTrue(newId==admin1.getId());

		//修改
		admin1.setLoginName("junit-modified");
		adminService.update(admin1);
		Admin admin2 = adminService.findById(newId);
		Assert.assertTrue("junit-modified".equals(admin2.getLoginName()));
		
		//删除
		int affected = adminService.delete(newId);
		Assert.assertTrue(affected==1);
		
	}
	
	/**
	 * 测试获取所有对象
	 */
	@Test
	public void selectAll(){
		
		List<Admin> adminList = adminService.findAll();
		
		for(Admin admin:adminList){
			logger.info("@selectAll >>>> listed user id:"+admin.getId());
		}
		
		Assert.assertTrue(adminList.size()>0);
		
	}
	
	/**
	 * 测试获取Row
	 */
	@Test
	public void select(){
		
		SQLBuilder adminSB = SQLBuilder.getSQLBuilder(Admin.class);
		String sql = adminSB.fields("id,login_name").where("id=#{0}").selectSql();
		List<Row> list = sqlRunner.select(sql,1);
		
		for(Row r:list){
			logger.info("@select >>>> users:"+JSON.toJSONString(r));
		}
		
		Assert.assertTrue(list.size()>0);
	}
	
	@Test
	public void find(){
		
		SQLBuilder adminSB = SQLBuilder.getSQLBuilder(Admin.class);
		String sql = adminSB.fields("id,login_name").where("id=#{0}").selectSql();
		Row row = sqlRunner.find(sql, 1);
		
		logger.info("@find >>>> user:"+JSON.toJSONString(row));
		
		Assert.assertTrue(row.getInt("id")==1);
		
	}
	
	@Test
	public void count(){
		SQLBuilder adminSB = SQLBuilder.getSQLBuilder(Admin.class);
		String sql = adminSB.fields("count(*)").selectSql();
		int count = sqlRunner.count(sql);
		
		logger.info("@count >>>> count:"+count);
		
		Assert.assertTrue(count>0);
		
	}
	
}
