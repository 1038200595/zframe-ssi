package com.zlzkj.core.base;

import java.util.HashMap;

/**
 * 元组对象
 * @author Simon
 *
 */
public class Row extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;

	@Override
    public Object get(Object key) {
        if (super.get(key)!=null) {
            String oType = super.get(key).getClass().getSimpleName();
            if (oType.equals("String[]")){
                return ((String[])super.get(key))[0];
            }else if(oType.equals("byte[]")){
                return (new String((byte[])super.get(key)));
            }
        }
        return super.get(key);
    }
	
}
