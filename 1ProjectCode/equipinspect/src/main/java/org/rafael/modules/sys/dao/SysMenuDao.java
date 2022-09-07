package org.rafael.modules.sys.dao;

import java.util.List;

import org.rafael.modules.sys.entity.SysMenu;
import org.rafael.modules.sys.entity.SysMenuExample;
import org.rafael.modules.sys.entity.SysUser;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface SysMenuDao extends BaseDao<SysMenu, SysMenuExample, String> {
	List<SysMenu> selectBySysUser(SysUser sysUser);
}