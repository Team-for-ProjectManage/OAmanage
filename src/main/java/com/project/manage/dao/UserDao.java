package com.project.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.manage.entity.UserEntity;
import com.project.manage.model.UserVo;

@Mapper
public interface UserDao {

	public List<UserEntity> findAll();

	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public UserEntity getUserByName(@Param("userName")String userName); 

}
