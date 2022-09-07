package org.rafael.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.rafael.modules.sys.entity.SysUser;
import org.rafael.modules.sys.entity.SysUserExample;
import org.rafael.modules.sys.entity.SysUserRole;
import org.rafael.modules.sys.entity.SysUserRoleKey;
import org.rafael.modules.util.mvcbase.BaseService;

public interface SysUserService extends BaseService<SysUser, SysUserExample, String> {
	public List<Map<String,Object>>  selectUserAndRole(Map<String,Object> params);
	public int  selectCountUserAndRole(Map<String,Object> params);
	public int deleteUserRoleById(SysUserRoleKey entity);
	public int insertSelective(SysUserRole sysUserRole);
}