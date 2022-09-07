/*
* SysUserRole.java
* Copyright(C) 2018-2025 org.rafael
* @date 2018-07-20
*/
package org.rafael.modules.sys.entity;

import java.util.Date;

public class SysUserRole extends SysUserRoleKey {
    /**
     * @Fields createBy null
     */
    private String createBy;

    /**
     * @Fields createDate null
     */
    private Date createDate;

    /**
     * @Fields updateBy null
     */
    private String updateBy;

    /**
     * @Fields updateDate null
     */
    private Date updateDate;

    /**
     * 获取 null 字段:dbo.sys_user_role.create_by
     *
     * @return dbo.sys_user_role.create_by, null
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置 null 字段:dbo.sys_user_role.create_by
     *
     * @param createBy the value for dbo.sys_user_role.create_by, null
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取 null 字段:dbo.sys_user_role.create_date
     *
     * @return dbo.sys_user_role.create_date, null
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置 null 字段:dbo.sys_user_role.create_date
     *
     * @param createDate the value for dbo.sys_user_role.create_date, null
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取 null 字段:dbo.sys_user_role.update_by
     *
     * @return dbo.sys_user_role.update_by, null
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置 null 字段:dbo.sys_user_role.update_by
     *
     * @param updateBy the value for dbo.sys_user_role.update_by, null
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取 null 字段:dbo.sys_user_role.update_date
     *
     * @return dbo.sys_user_role.update_date, null
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置 null 字段:dbo.sys_user_role.update_date
     *
     * @param updateDate the value for dbo.sys_user_role.update_date, null
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}