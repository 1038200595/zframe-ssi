package com.zlzkj.core.mybatis;

import java.util.List;

import com.zlzkj.core.base.Row;

/**
 * select数据专用
 * @author Simon
 *
 */
public interface SQLRunner {
    
	public List<Row> find(String sql);
	
}