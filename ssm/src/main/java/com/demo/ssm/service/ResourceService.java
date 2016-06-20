package com.demo.ssm.service;

import com.demo.ssm.po.Resource;

import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
public interface ResourceService {

    public int addResource(Resource resource) throws Exception;

    public int updateResource(String id, Resource resource) throws Exception;

    public int deleteResource(String id) throws Exception;

    public Resource getResource(String id) throws Exception;

    public List<Resource> getAllResource() throws Exception;

    public List getAllAndCheckedByRoleId(String roleId) throws Exception;

}
