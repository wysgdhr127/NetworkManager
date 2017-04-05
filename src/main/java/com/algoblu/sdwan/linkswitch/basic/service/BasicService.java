package com.algoblu.sdwan.linkswitch.basic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * @author 
 * 
 * @param <T>--VO
 */
public class BasicService {

	protected static Logger logger = Logger.getLogger(BasicService.class);

	/**
	 * 获取属性的get方法(String类型)
	 * 
	 * @param key
	 *            --属性名
	 * @return
	 */
	public String createGetMethodName(String key) {
		StringBuffer sb = new StringBuffer(key);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		String getMethodName = "get" + sb.toString();
		return getMethodName;
	}

	/**
	 * 拷贝
	 * @param dest
	 * @param orig
	 */
	public void copyProperties(Object dest, Object orig) {
		if(null == orig){return;}
		try {
			//BeanUtils.copyProperties(dest, orig);
			PropertyUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			logger.error("common-beanutils copyProperties error!");
			e.printStackTrace();
		}
	}


	/**
	 * 比较po和vo共同参数是否相同
	 * 
	 * @param po
	 *            --比较po
	 * @param vo
	 *            --比较vo
	 * @param removeParams
	 *            --需排除比较的参数
	 * @return
	 */
	public boolean equalsWithVo(Object po, Object vo, String... removeParams) {
		try {
			Object poForVo = po.getClass().newInstance();
			this.copyProperties(poForVo, vo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param keyMaxLength
	 * @return
	 */
	@SuppressWarnings("unused")
	private String printMapFormat(String key,String value,int keyMaxLength){
		StringBuffer sb = new StringBuffer(key);
		int leaveLength = keyMaxLength - key.length();
		for(int i = 0;i<leaveLength;i++){
			sb.append(" ");
		}
		sb.append(value);
		return sb.toString();
	}
	
	/**
	 * 判断PO相等
	 * @param po1
	 * @param po2
	 * @param removeParams
	 * @return
	 */
	public boolean equalsWithPo(Object po1, Object po2, String... removeParams) {
		
		JSONObject jo1 = JSONObject.fromObject(po1);
		JSONObject jo2 = JSONObject.fromObject(po2);
		for (String removeParam : removeParams) {
			jo1.remove(removeParam);
			jo2.remove(removeParam);
		}

		int to = jo1.compareTo(jo2);

		if (to == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 替换模板内容中变量的名字
	 * @param parametersMap : 需要替换的变量集合
	 * @param strContent ： 变量文本内容
	 * @return
	 */
	public String contentTemplate(Map<String, String> parametersMap,String strContent) {
		for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
			if (strContent.indexOf(entry.getKey()) > 0) {
				String value = entry.getValue();
				if ("".equals(value) || null == value) {
					value = " ";
				}
				strContent = strContent.replace("^&" + entry.getKey() + "^&",value);
			}
		}
		return strContent;
	}
	
	/**
	 * 将制定字符串内容，根据需要的格式截成数组
	 * @param String[]
	 * @return
	 */
	public String[] strTransform(String[] strContent, String subType) {
		String str = null;
		List<String> strList = new ArrayList<String>();
		for (int i = 0; i < strContent.length; i++) {
			str = strContent[i];
			if (null == str) {
				continue;
			}
			String[] arr = str.trim().split(subType);
			for (int j = 0; j < arr.length; j++) {
				if (strList.contains(arr[j])) {
					continue;
				}
				strList.add(arr[j]);
			}
		}
		
		Object objs[] = strList.toArray();
		String strArray[] = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			strArray[i] = objs[i].toString();
		}
		return strArray;
	}
}
