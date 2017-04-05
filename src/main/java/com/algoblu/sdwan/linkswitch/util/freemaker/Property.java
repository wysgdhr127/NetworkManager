package com.algoblu.sdwan.linkswitch.util.freemaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



/** 
* @package com.algoblu.template.util.freemaker
* @author 作者:王阳   E-mail: 282404989@qq.com 
* @version 创建时间：2016年10月11日 下午3:41:53 
* 类说明 ：
*/

public class Property {
	public static Map<String, String> getProperties(String fileName) {
		// 传入参数fileName是要读取的资源文件的文件名如(file.properties)
		InputStream in = null;
		Properties pros = new Properties();
		File file = new File(fileName);
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (null != fileName) {
				// 下面这种方式前提是资源文件必须类在同一个包下
				// in = ReadProperties.class.getResourceAsStream(fileName);
				in = new FileInputStream(file);
				// 得到当前类的路径，并把资源文件名作为输入流
				pros.load(in);
				Enumeration en = pros.propertyNames();// 得到资源文件中的所有key值
				while (en.hasMoreElements()) {
					String key = (String) en.nextElement();
					map.put(key, pros.getProperty(key));
					// 输出资源文件中的key与value值
					// System.out.println("key=" + key + " value=" +
					// pros.getProperty(key));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("读取资源文件出错");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("关闭流失败");
			}
		}
		return map;
	}
}
