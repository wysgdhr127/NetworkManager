<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
	<package name="${defaultTemplate.entityViable}" namespace="/${defaultTemplate.entityViable}" extends="json-default">
		<!-- 进入查询列表 -->
		<action name="InSearch" class="${defaultTemplate.actionName}" method="inSearch">
			<result name="success" type="redirectAction">
				<param name="actionName">Search</param>
			</result>
		</action>

		<!-- 查询列表 -->
		<action name="Search" class="${defaultTemplate.actionName}" method="search">
			<result name="success">/jsp/${defaultTemplate.entityName}Page/list.jsp</result>
		</action>

		<!-- ajax次查询 -->
		<action name="AjaxSearch" class="${defaultTemplate.actionName}" method="ajaxSearch">
			<result type="json">
				<param name="root">resultList</param>
			</result>
		</action>




		<!-- 添加列表 -->
		<action name="InAdd" class="${defaultTemplate.actionName}" method="inAdd">
			<result name="success">/jsp/${defaultTemplate.entityName}Page/add.jsp</result>

		</action>
		
		<action name="Add" class="${defaultTemplate.actionName}" method="add">
			<result name="success" type="redirectAction">
				<param name="namespace">/${defaultTemplate.entityViable}</param>
				<param name="actionName">Search</param>
				<param name="method">search</param>
				<param name="error.success">${r'${error.success}'}</param>
				<param name="error.errorMessage">${r'${error.errorMessage}'}</param>
			</result>
		</action>

		<!-- 修改列表 -->
		<action name="InUpdate" class="${defaultTemplate.actionName}" method="inUpdate">
			<result name="success">/jsp/${defaultTemplate.entityName}Page/update.jsp</result>
		</action>
		
		<action name="Update" class="${defaultTemplate.actionName}" method="update">
			<result name="success" type="redirectAction">
				<param name="namespace">/${defaultTemplate.entityViable}</param>
				<param name="actionName">Search</param>
				<param name="method">search</param>
				<param name="error.success">${r'${error.success}'}</param>
				<param name="error.errorMessage">${r'${error.errorMessage}'}</param>
			</result>
		</action>

		<!-- 删除列表 -->
		<action name="Delete" class="${defaultTemplate.actionName}" method="delete">
			<result name="success" type="redirectAction">
				<param name="namespace">/${defaultTemplate.entityViable}</param>
				<param name="actionName">Search</param>
				<param name="method">search</param>
				<param name="error.success">${r'${error.success}'}</param>
				<param name="error.errorMessage">${r'${error.errorMessage}'}</param>
			</result>
		</action>

		<!-- 重发列表 -->
		<action name="reSend" class="${defaultTemplate.actionName}" method="reSend">
			<result name="success" type="redirectAction">
				<param name="namespace">/${defaultTemplate.entityViable}</param>
				<param name="actionName">Search</param>
				<param name="method">search</param>
				<param name="error.success">${r'${error.success}'}</param>
				<param name="error.errorMessage">${r'${error.errorMessage}'}</param>
			</result>
		</action>


	</package>
</struts>

