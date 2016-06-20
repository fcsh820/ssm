package com.demo.ssm.po;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/15.
 */
public class UserRole implements Serializable {
    private String userId;
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
