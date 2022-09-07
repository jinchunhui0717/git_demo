/*
* SysUserRoleKey.java
* Copyright(C) 2018-2025 org.rafael
* @date 2018-07-20
*/
package org.rafael.modules.sys.entity;

public class SysUserRoleKey {
    /**
     * @Fields userId null
     */
    private String userId;

    /**
     * @Fields roleId null
     */
    private String roleId;

    /**
     * 获取 null 字段:dbo.sys_user_role.user_id
     *
     * @return dbo.sys_user_role.user_id, null
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 null 字段:dbo.sys_user_role.user_id
     *
     * @param userId the value for dbo.sys_user_role.user_id, null
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取 null 字段:dbo.sys_user_role.role_id
     *
     * @return dbo.sys_user_role.role_id, null
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置 null 字段:dbo.sys_user_role.role_id
     *
     * @param roleId the value for dbo.sys_user_role.role_id, null
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}