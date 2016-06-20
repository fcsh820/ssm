package com.demo.ssm.mapper;

import com.demo.ssm.po.UserRole;

/**
 * Created by Administrator on 2016/2/15.
 */
public interface UserRoleMapper {

    /**
     * 添加
     * @param userRole
     * @return
     */
    int add(UserRole userRole);

    /**
     * 删除用户的角色
     * @param userId
     * @return
     */
    int deleteByUserId(String userId);

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    int deleteByUserIds(String... userIds);
}
