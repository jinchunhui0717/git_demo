package org.rafael.modules.sys.dao;

import org.rafael.modules.sys.entity.SysUserRole;
import org.rafael.modules.sys.entity.SysUserRoleExample;
import org.rafael.modules.sys.entity.SysUserRoleKey;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface SysUserRoleDao extends BaseDao<SysUserRole, SysUserRoleExample, String> {
	public int deleteByPrimaryKey(SysUserRoleKey entity);
}