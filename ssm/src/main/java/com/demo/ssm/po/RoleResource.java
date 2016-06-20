package com.demo.ssm.po;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/3.
 */
public class RoleResource implements Serializable {
    private String role_id;
    private String resource_id;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }
}
