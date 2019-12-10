package com.project.manage.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.project.manage.entity.UserEntity;
import com.project.manage.model.PermissionVo;
import com.project.manage.model.RoleVo;
import com.project.manage.model.UserVo;
import com.project.manage.service.ILoginService;

/**
 * 自定义shiro
 * @author Administrator
 *
 */
public class CustomRealm extends AuthorizingRealm{

	@Autowired
    private ILoginService loginService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//获取登录用户名
		String name = (String)principal.getPrimaryPrincipal();
		//根据用户名去数据库查询用户信息
		UserEntity userEntity = loginService.getUserByName(name);
		UserVo user = JSONObject.toJavaObject(JSONObject.parseObject(userEntity.toString()), UserVo.class);
		//添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (RoleVo role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (PermissionVo permissions : role.getPermission()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = token.getPrincipal().toString();
        UserEntity userEntity = loginService.getUserByName(name);
        UserVo user = JSONObject.toJavaObject(JSONObject.parseObject(userEntity.toString()), UserVo.class);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getUserPwd().toString(), getName());
            return simpleAuthenticationInfo;
        }
	}

}