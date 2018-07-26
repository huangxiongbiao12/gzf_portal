package com.gzf.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CloneReflectionHelper {

	/**
	 * 克隆一个实例
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E clone(E instance){
		try {
			E t = (E) instance.getClass().newInstance();
			cloneProp(t, instance);
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 克隆对象属性
	 * @param target
	 * 		克隆属性至目标对象
	 * @param origin
	 * 		从源对象克隆属性
	 */
	public static <T, O> void cloneProp(T target, O origin){
		try {
			//防止"No value specified for Date"报错
			ConvertUtils.register(new DateConverter(null), java.util.Date.class);
			ConvertUtils.register(new DateConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(target, origin);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重构对象，并继承原有属性
	 * @param origin
	 * 		被重构的源对象
	 * @param clz
	 * 		重构对象的类型
	 * @return
	 */
	public static <T, O> T refact(O origin, Class<T> clz){
		try {
			T t = clz.newInstance();
			cloneProp(t, origin);
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 重构list
	 * @param origins
	 * 		被重构的源对象list
	 * @param clz
	 * 		重构对象的类型
	 * @return
	 */
	public static <T, O> List<T> refactList(List<O> origins, Class<T> clz){
		List<T> tList = new ArrayList<T>();
		for(O o : origins){
			tList.add(refact(o, clz));
		}
		return tList;
	}
}
