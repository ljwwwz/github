package com.coolshow.jeesite.common.tool;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description json格式转换工具类
 * ClassName: JsonHelper.java
 * @author LJW
 * @date 下午5:24:28
 */
public class JsonHelper {
	
	//将list转化为json格式的字符串
	public static String listToJson(List list){
		JSONArray array = JSONArray.fromObject(list);
		return array.toString();
	}
	//将普通的bean对象转化为json格式的字符串
	public static String beanToJson(Object object){
		JSONObject obj = JSONObject.fromObject(object);
		return obj.toString();
	}

}
