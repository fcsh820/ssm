package com.demo.ssm.mapper;

import com.demo.ssm.po.Resource;

import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
public interface ResourceMapper {
    int insert(Resource resource) throws Exception;

    int update(Resource resource) throws Exception;

    int delete(String id) throws Exception;

    Resource find(String id) throws Exception;

    Resource findByPid(String id) throws Exception;

    List<Resource> findAll() throws Exception;

    List findAllAndCheckedByRoleId(String roleId) throws Exception;
}
