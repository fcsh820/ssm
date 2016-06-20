package com.demo.ssm.mapper;

import java.util.Map;

/**
 * Created by Administrator on 2016/2/3.
 */
public interface RoleResourceMapper {

    /**
     * 角色授权
     * @param map
     * @return
     */
    public int insert(Map<String, Object> map);

    /**
     * 删除角色权限
     * @param roleId
     * @return
     */
    public int deleteByRoleId(String roleId);
}
