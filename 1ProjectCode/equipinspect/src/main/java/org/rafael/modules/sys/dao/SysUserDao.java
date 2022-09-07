package org.rafael.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.rafael.modules.sys.entity.SysUser;
import org.rafael.modules.sys.entity.SysUserExample;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface SysUserDao extends BaseDao<SysUser, SysUserExample, String> {
	public List<Map<String,Object>>  selectUserAndRole(Map<String,Object> params);
	public int  selectCountUserAndRole(Map<String,Object> params);
	
}