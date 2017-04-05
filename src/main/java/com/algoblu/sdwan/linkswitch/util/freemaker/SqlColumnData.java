package com.algoblu.sdwan.linkswitch.util.freemaker;

/**
 * @author 作者：王阳 E-mail:282404989@qq.com
 * @version 创建时间：2016年10月11日 下午4:48:31 
 * 类说明: sql类
 */
public class SqlColumnData {

	private String columnName;

	private String columnType;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	@Override
	public String toString() {
		return "SqlColumnData [columnName=" + columnName + ", columnType=" + columnType + "]";
	}

}
