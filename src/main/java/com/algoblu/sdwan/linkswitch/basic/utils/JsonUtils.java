package com.algoblu.sdwan.linkswitch.basic.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

/**
 * JSON操作工具类
 */
public class JsonUtils {

	public final static JsonConfig DEFAULT_JSON_CONFIG = getDefaultJsonConfig();

	/**
	 * 默认JsonConfig
	 * 
	 * @return
	 */
	public static JsonConfig getDefaultJsonConfig() {

		JsonConfig jsonConfig = new JsonConfig();
		/************** 使用jsonConfig的setCycleDetectionStrategy（）方法进行忽略死循环 **************/
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		/************** 处理Integer,Long,Boolean等为null时输出为0或false的问题 **************/
		jsonConfig.registerJsonValueProcessor(Integer.class, new JsonUtilsJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Float.class, new JsonUtilsJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Double.class, new JsonUtilsJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class, new JsonUtilsJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Boolean.class, new JsonUtilsJsonValueProcessor());

		jsonConfig.setJsonPropertyFilter(new JsonUtilsPropertyFilter());

		return jsonConfig;
	}
	
	public static JSONObject objectByJsonConfig(Object object){
		
		if (object == null) {
			return null;
		}

		return JSONObject.fromObject(object, DEFAULT_JSON_CONFIG);
		
	}

	/**
	 * Object对象转换为Json
	 * 
	 * @param object
	 * @return json
	 */
	public static String objectToJson(Object object) {
		return JSONObject.fromObject(object).toString();
	}

	/**
	 * 
	 * Object对象转换为Json 空字段不被序列化
	 * 
	 * @param object
	 * @param jsonConfig
	 * @return
	 */
	public static String objectToJson(Object object, JsonConfig jsonConfig) {
		if (object == null) {
			return "";
		}

		if (jsonConfig == null) {
			jsonConfig = DEFAULT_JSON_CONFIG;
		}
		return JSONObject.fromObject(object, jsonConfig).toString();
	}
	

	/**
	 * Json转换为Object对象
	 * 
	 * @param json
	 * @return object
	 */
	@SuppressWarnings("rawtypes")
	public static Object jsonToObject(String json, Map<String, Class> classMap, Class classs) {
		if (json == null) {
			return null;
		}
		JSONObject jsonObject = JSONObject.fromObject(json);
		Object bean = new Object();
		if (classMap == null) {
			bean = JSONObject.toBean(jsonObject, classs);
		}else {
			bean = JSONObject.toBean(jsonObject, classs,classMap);
		}
		
		return bean;
	}

	/**
	 * List 转换为 Json
	 * 
	 * @param list
	 * @return json
	 */
	public static String ListToJson(@SuppressWarnings("rawtypes") List list) {
		if (list == null) {
			return "";
		}
		return JSONArray.fromObject(list).toString();
	}

	/**
	 * List 转换为 Json 空字段不被序列化
	 * 
	 * @param list
	 * @param jsonConfig
	 * @return json
	 */
	public static String ListToJson(@SuppressWarnings("rawtypes") List list, JsonConfig jsonConfig) {
		if (list == null) {
			return "";
		}

		if (jsonConfig == null) {
			jsonConfig = DEFAULT_JSON_CONFIG;
		}

		return JSONArray.fromObject(list, jsonConfig).toString();
	}

	/**
	 * 数组 转换为 Json
	 * 
	 * @param os
	 *            []
	 * @return json
	 */
	public static String ArrayToJson(Object[] os) {
		if (os == null) {
			return "";
		}
		return JSONArray.fromObject(os).toString();
	}

	/**
	 * 数组 转换为 Json 空字段不被序列化
	 * 
	 * @param os
	 *            []
	 * @param jsonConfig
	 * @return json
	 */
	public static String ArrayToJson(Object[] os, JsonConfig jsonConfig) {
		if (os == null) {
			return "";
		}

		if (jsonConfig == null) {
			jsonConfig = DEFAULT_JSON_CONFIG;
		}

		return JSONArray.fromObject(os, jsonConfig).toString();
	}

	/**
	 * 将json字符串转换为List对象
	 * 
	 * @param json
	 * @return list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List jsonToList(String json, Class classs) {
		if (json == null) {
			return null;
		}

		Object bean = null;
		ArrayList list = new ArrayList();

		JSONArray jsonArray = JSONArray.fromObject(json);
		int size = jsonArray.size();
		for (int i = 0; i < size; i++) {
			bean = JSONObject.toBean((JSONObject) jsonArray.get(i), classs);
			list.add(bean);
		}
		return list;
	}

	/**
	 * Map 转换为 Json
	 * 
	 * @param map
	 * @return json
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToJson(Map map) {
		if (map == null) {
			return "";
		}
		return objectToJson(map);
	}

	/**
	 * Map 转换为 Json 空字段不被序列化
	 * 
	 * @param map
	 * @return json
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToJson(Map map, JsonConfig jsonConfig) {
		if (map == null) {
			return "";
		}

		if (jsonConfig == null) {
			jsonConfig = DEFAULT_JSON_CONFIG;
		}

		return objectToJson(map, jsonConfig);
	}

	/**
	 * 将json字符串转换为Map对象
	 * 
	 * @param json
	 * @return map<String, Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json) {
		if (json == null) {
			return null;
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(json);
		Iterator<String> iterator = jsonObject.keys();
		String key = null;
		Object value = null;
		while (iterator.hasNext()) {
			key = iterator.next();
			value = jsonObject.get(key);
			resultMap.put(key, value);
		}
		return resultMap;
	}

	/**
	 * Map转换List
	 * 
	 * @param _map
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static List mapToList(Map _map) {
		if (_map == null) {
			return null;
		}

		Object o = null;

		List list = new ArrayList();
		Set<String> _set = _map.keySet();
		int size = _map.size();
		for (int i = 0; i < size; i++) {
			o = _map.get(String.valueOf(i));
			list.add(o);
		}
		return list;
	}

	/**
	 * List转换Map
	 * 
	 * @param _list
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map JsonToMap(List _list) {
		if (_list == null) {
			return null;
		}

		Map map = new HashMap();
		for (int i = 0; i < _list.size(); i++) {
			map.put(String.valueOf(i), _list.get(i));
		}
		return map;
	}

}

class JsonUtilsJsonValueProcessor implements JsonValueProcessor {

	public Object processArrayValue(Object obj, JsonConfig arg1) {
		if (obj == null) {
			return "";
		}
		return obj;
	}

	public Object processObjectValue(String arg0, Object obj, JsonConfig arg2) {
		if (obj == null) {
			return "";
		}
		return obj;
	}
}

class JsonUtilsPropertyFilter implements PropertyFilter {
	@Override
	public boolean apply(Object arg0, String name, Object value) {
		if (value == null) {
			return true; // 返回 true, 表示这个属性将被过滤掉
		}
		return false;
	}
}
