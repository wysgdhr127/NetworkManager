package com.algoblu.sdwan.linkswitch.util.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者：王阳 E-mail:282404989@qq.com
 * @version 创建时间：2016年10月11日 下午4:53:48 类说明
 */
public class MapperGenerator {

	private static Logger logger = Logger.getLogger(MapperGenerator.class);

	private String argv;
	private String method;

	private String poPath;
	private String mapperPath;
	private String entityPath;

	private String poTemplatePath;
	private String mapperTemplatePath;

	public void init() {
		String propertyPath = "src/main/resources/config.properties"; // 使用默认路径
		init(propertyPath);
	}

	public void init(String propertyPath) {
		Map<String, String> properties = Property.getProperties(propertyPath);
		poPath = properties.get("poPath");
		mapperPath = properties.get("mapperPath");
		entityPath = properties.get("entityPath");

		poTemplatePath = properties.get("poTemplatePath");
		mapperTemplatePath = properties.get("mapperTemplatePath");
	}

	/**
	 * 获取指定制定数据库所有表名
	 * 
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public List<String> getTableDatas() throws SQLException {
		String sqlColumns = "SELECT	TABLE_NAME FROM	INFORMATION_SCHEMA. TABLES WHERE TABLE_SCHEMA = 'network_manager_1.0.x'";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> tableList = new ArrayList<String>();
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/network_manager_1.0.x?characterEncoding=utf-8";
			conn = DriverManager.getConnection(url, "root", "");
			pst = conn.prepareStatement(sqlColumns);
			rs = pst.executeQuery();
			while (rs.next()) {
				String tableName = rs.getString(1);
				tableList.add(tableName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tableList;
	}

	/**
	 * 获取指定表的所有列名
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<SqlColumnData> getColumnDatas(String tableName) throws SQLException {
		String sqlColumns = "SELECT COLUMN_NAME,DATA_TYPE FROM	information_schema. COLUMNS WHERE table_name = '"
				+ tableName + "' and TABLE_SCHEMA = 'network_manager_1.0.x'";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SqlColumnData> columnList = new ArrayList<SqlColumnData>();
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/network_manager_1.0.x?characterEncoding=utf-8";
			conn = DriverManager.getConnection(url, "root", "");
			pst = conn.prepareStatement(sqlColumns);
			rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				String type = rs.getString(2);
				type = this.getType(type);
				SqlColumnData cd = new SqlColumnData();
				cd.setColumnName(name.toLowerCase());
				cd.setColumnType(type);
				columnList.add(cd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pst != null)
					pst.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return columnList;
	}

	/**
	 * 将列名生成对应的field 和 method
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public String getBeanField(String tableName) throws SQLException {
		List<SqlColumnData> dataList = getColumnDatas(tableName);
		StringBuffer str = new StringBuffer();
		StringBuffer getset = new StringBuffer();
		for (SqlColumnData d : dataList) {
			String name = d.getColumnName().toLowerCase();
			name = firstCharLowerCase(getTableNameToClassName(name));
			String type = d.getColumnType();
			String maxChar = name.substring(0, 1).toUpperCase();
			str.append("\r\t").append("private ").append(type + " ").append(name).append(";\n");
			String method = maxChar + name.substring(1, name.length());
			getset.append("\r\t").append("public ").append(type + " ").append("get" + method + "()\n\t{\n");
			getset.append("\t\t").append("return this.").append(name).append(";\n\t}\n");
			getset.append("\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ")\n\t{\n");
			getset.append("\t\t").append("this." + name + "=").append(name).append(";\n\t}\n");
		}
		argv = str.toString();
		method = getset.toString();
		return argv + method;
	}

	/**
	 * 将数据库类型转换成对应的JAVA类型
	 * 
	 * @param type
	 * @return
	 */
	public String getType(String type) {
		type = type.toLowerCase();
		if ("char".equals(type) || "varchar".equals(type) || "nvarchar".equals(type) ||"text".equals(type)) {
			return "String";
		} else if ("int".equals(type)) {
			return "Integer";
		} else if ("bigint".equals(type)) {
			return "Long";
		} else if ("timestamp".equals(type) || "date".equals(type) || "datetime".equals(type)) {
			return "java.sql.Timestamp";
		} else if ("decimal".equals(type)) {
			return "Double";
		} else if ("image".equals(type)) {
			return "byte[]";
		} else if ("smallint".equals(type)) {
			return "int";
		}
		return null;
	}

	/**
	 * @param s
	 *            String
	 * @return 首字母小写
	 */
	public static String firstCharLowerCase(String s) {
		if (s == null || "".equals(s)) {
			return ("");
		}
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	/**
	 * 将表名转成class名称
	 * 
	 * @param tableName
	 * @return
	 */
	public static String getTableNameToClassName(String tableName) {
		String[] splits = tableName.toLowerCase().split("_");
		if (splits.length > 0) {
			StringBuffer className = new StringBuffer();
			for (String split : splits) {
				String tempTableName = split.substring(0, 1).toUpperCase() + split.substring(1);
				className.append(tempTableName);
			}
			return className.toString();
		} else {
			String className = splits[0].substring(0, 1).toUpperCase() + splits[0].substring(1);
			return className;
		}
	}

	/**
	 * @param path
	 * @return 根据包的路径得到package XXX中的XXX
	 */
	private String getImportPath(String path) {
		StringBuffer result = new StringBuffer();
		String[] strings = path.split("/");
		for (int i = 3, size = strings.length; i < size; i++) {
			result.append(".").append(strings[i]);
		}
		return result.substring(1);
	}

	/**
	 * 生成po文件
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	public void createPo(String tableName) throws IOException {
		MapperGenerator mapperGenerator = new MapperGenerator();
		Template template = new Configuration().getTemplate(poTemplatePath, "UTF-8");
		String className = getTableNameToClassName(tableName);
		String poImportPath = getImportPath(poPath);
		// 生成到指定的目录下
		DefaultTemplate defaultTemplate = new DefaultTemplate();
		defaultTemplate.setPackagePath(poImportPath);
		defaultTemplate.setEntityName(className);
		/****************************** 生成bean字段 *********************************/
		try {
			defaultTemplate.setFields(mapperGenerator.getBeanField(tableName)); // 生成bean
			logger.info("请稍侯，正在生成Bean属性及GET、SET方法");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// -------------------生成文件代码---------------------/
		createFile(defaultTemplate, poPath, className + ".java", template);
	}

	public String getQueryField(String tableName) throws SQLException {
		String qeryField;
		StringBuffer fields = new StringBuffer();
		List<SqlColumnData> dataList = getColumnDatas(tableName);
		for (SqlColumnData d : dataList) {
			String name = d.getColumnName().toLowerCase();
			fields.append("\t\t").append(name).append("\t").append(firstCharLowerCase(getTableNameToClassName(name)))
					.append(",\n");
		}
		qeryField = fields.deleteCharAt(fields.length() - 2).toString();
		return qeryField;
	}

	public String getWhereClause(String tableName) throws SQLException {
		String whereClause;
		StringBuffer fields = new StringBuffer();
		List<SqlColumnData> dataList = getColumnDatas(tableName);
		for (SqlColumnData d : dataList) {
			String name = d.getColumnName().toLowerCase();
			fields.append("\t\t\t").append("<if test='" + firstCharLowerCase(getTableNameToClassName(name)) + " !=null'>").append("\n\t\t\t")
					.append("   and " + name + "= #{" + firstCharLowerCase(getTableNameToClassName(name)) + "}")
					.append("\n\t\t\t</if>\n");
		}
		whereClause = fields.toString();
		return whereClause;
	}

	public String getUpdateClause(String tableName) throws SQLException {
		String updateClause;
		StringBuffer fields = new StringBuffer();
		List<SqlColumnData> dataList = getColumnDatas(tableName);
		for (SqlColumnData d : dataList) {
			if (!d.getColumnName().equals("id")) {
				String name = d.getColumnName().toLowerCase();
				fields.append("\t\t\t").append("<if test='" + firstCharLowerCase(getTableNameToClassName(name)) + " !=null'>").append("\n\t\t\t")
						.append("   " + name + "= #{" + firstCharLowerCase(getTableNameToClassName(name)) + "},")
						.append("\n\t\t\t</if>\n");
			}
		}
		// 去除最后一个回车
		updateClause = fields.deleteCharAt(fields.length() - 1).toString();
		return updateClause;
	}

	public String[] getInsertClause(String tableName) throws SQLException {
		String[] insertClause = new String[2];
		StringBuffer fields = new StringBuffer();
		StringBuffer clause = new StringBuffer();
		List<SqlColumnData> dataList = getColumnDatas(tableName);
		for (SqlColumnData d : dataList) {
			if (!d.getColumnName().equals("id")) {
				String name = d.getColumnName().toLowerCase();
				fields.append("\t\t\t").append(name + ",\n");
				clause.append("\t\t\t").append("#{" + firstCharLowerCase(getTableNameToClassName(name)) + "},\n");
			}
		}
		// 去除最后一个逗号和回车
		insertClause[0] = fields.deleteCharAt(fields.length() - 2).toString();
		insertClause[1] = clause.deleteCharAt(clause.length() - 2).toString();
		return insertClause;
	}

	/**
	 * 生成Mapper文件
	 * 
	 * @param tableName
	 * @throws IOException
	 * @throws SQLException
	 */
	public void createMapper(String tableName) throws IOException, SQLException {
		Template template = new Configuration().getTemplate(mapperTemplatePath, "UTF-8");
		String className = getTableNameToClassName(tableName);
		String poImportPath = getImportPath(poPath);
		String queryField = getQueryField(tableName);
		String whereClause = getWhereClause(tableName);
		String updateClause = getUpdateClause(tableName);
		String insertField = getInsertClause(tableName)[0];
		String insertClause = getInsertClause(tableName)[1];
		// 生成到指定的目录下
		DefaultTemplate defaultTemplate = new DefaultTemplate();
		defaultTemplate.setPackagePath(poImportPath);
		defaultTemplate.setEntityName(className);
		defaultTemplate.setModelPath(poImportPath + "." + className);
		defaultTemplate.setQueryField(queryField);
		defaultTemplate.setWhereClause(whereClause);
		defaultTemplate.setUpdateClause(updateClause);
		defaultTemplate.setEntityViable(tableName);
		defaultTemplate.setInsertClause(insertClause);
		defaultTemplate.setInsertField(insertField);
		createFile(defaultTemplate, mapperPath, className + "Mapper.xml", template);
	}

	/**
	 * 生成文件
	 * 
	 * @param object
	 * @param savePath
	 * @param fileName
	 * @param template
	 */
	public void createFile(Object object, String savePath, String fileName, Template template) {
		String realFileName = savePath + "/" + fileName;

		String realSavePath = savePath;

		File newsDir = new File(realSavePath);
		if (!newsDir.exists()) {
			logger.info("create dir :" + realSavePath);
			newsDir.mkdirs();
		}
		// 如果已經存在不创建
		File realFile = new File(realFileName);
		if (realFile.exists()) {
			logger.info(fileName + " exists , don't create !");
			return;
		}
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("defaultTemplate", object);
		try {
			// SYSTEM_ENCODING = "UTF-8";
			Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
			template.process(rootMap, out);
			logger.info(fileName + " create success !");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		MapperGenerator mapperGenerator = new MapperGenerator();

		mapperGenerator.init();
		try {			
			List<String> list = mapperGenerator.getTableDatas();
			for (String string : list) {
				if (!string.equals("c3p0testtable")) {
					mapperGenerator.createPo(string);
					mapperGenerator.createMapper(string);
				}
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
