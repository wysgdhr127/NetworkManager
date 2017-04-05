<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${defaultTemplate.modelPath}">

	<select id="select" parameterType="${defaultTemplate.entityName}" resultType="${defaultTemplate.entityName}">
		select		
${defaultTemplate.queryField}
		from ${defaultTemplate.entityViable}
		<where>
${defaultTemplate.whereClause}
		</where>
		<if test="orderByClause != null">
			order by ${r'${orderByClause}'}
		</if>
		<if test="limitByClause != null">
			limit ${r'${limitByClause}'}
		</if>
	</select>
	
	<select id="count" parameterType="${defaultTemplate.entityName}" resultType="int">
		select count(1) from ${defaultTemplate.entityViable}
		<where>
${defaultTemplate.whereClause}
		</where>
	</select>

	<select id="selectByMap" parameterType="HashMap" resultType="${defaultTemplate.entityName}">
		select
${defaultTemplate.queryField}
		from ${defaultTemplate.entityViable}
		<where>
${defaultTemplate.whereClause}
		</where>
		<if test="orderByClause != null">
			order by ${r'${orderByClause}'}
		</if>
		<if test="limitByClause != null">
			limit ${r'${limitByClause}'}
		</if>
	</select>

	<update id="update" parameterType="${defaultTemplate.entityName}">
		update ${defaultTemplate.entityViable}
		<set>
${defaultTemplate.updateClause}
		</set>
		<where>
			<if test="id != null">
				id = ${r'#{id}'}
			</if>
		</where>
	</update>

	<insert id="insert" keyProperty="id" useGeneratedKeys="true">
		insert
		into ${defaultTemplate.entityViable}(
${defaultTemplate.insertField}
		)
		values(
${defaultTemplate.insertClause}
		)
	</insert>

	<!-- 自定义运行sql -->
	<update id="runSql" parameterType="SqlAdapter">
		${r'${sql}'}
	</update>

	<delete id="delete" parameterType="${defaultTemplate.entityName}">
		delete from ${defaultTemplate.entityViable}
		<where>
${defaultTemplate.whereClause}
		</where>
	</delete>

	<delete id="deleteByMap" parameterType="HashMap">
		delete from ${defaultTemplate.entityViable}
		<where>
${defaultTemplate.whereClause}
		</where>
	</delete>


</mapper>