/*
* SysRoleMenuKey.java
* Copyright(C) 2018-2025 org.rafael
* @date 2018-07-20
*/
package org.rafael.modules.sys.entity;

public class SysRoleMenuKey {
    /**
     * @Fields roleId null
     */
    private String roleId;

    /**
     * @Fields menuId null
     */
    private String menuId;

    /**
     * 获取 null 字段:dbo.sys_role_menu.role_id
     *
     * @return dbo.sys_role_menu.role_id, null
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置 null 字段:dbo.sys_role_menu.role_id
     *
     * @param roleId the value for dbo.sys_role_menu.role_id, null
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取 null 字段:dbo.sys_role_menu.menu_id
     *
     * @return dbo.sys_role_menu.menu_id, null
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置 null 字段:dbo.sys_role_menu.menu_id
     *
     * @param menuId the value for dbo.sys_role_menu.menu_id, null
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}