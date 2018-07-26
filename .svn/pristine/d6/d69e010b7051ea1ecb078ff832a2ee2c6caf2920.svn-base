package com.gzf.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by yangweiguang on 16/10/18.
 */
public class GlobalDefine {

	static SqlSessionFactory sqlSessionFactory = null;
//	static ApplicationContext context = null;
	/**
	 * 获取sqlSessionFactory
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory(){

		if (sqlSessionFactory==null) {
			ApplicationContext context = ApplicationContextUtil.getApplicationContext();
			sqlSessionFactory = (SqlSessionFactory) ApplicationContextUtil.getBean("sqlSessionFactory");
//			context.close();
		}
		return sqlSessionFactory ;
	}

}
