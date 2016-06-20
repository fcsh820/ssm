package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.ResourceMapper;
import com.demo.ssm.po.Resource;
import com.demo.ssm.service.ResourceService;
import com.demo.ssm.util.ChkTools;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/1.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    @Override
    public int addResource(Resource resource) throws Exception {
        Resource res = resourceMapper.find(resource.getId());
        if (res != null) {
            throw new RuntimeException("编号已存在");
        }

        String pid = resourceMapper.findByPid(resource.getId()).getPid();
        if(ChkTools.isNull(pid)) pid = "";
        resource.setPid(pid);
        resource.setId(UUID.randomUUID().toString());
        return resourceMapper.insert(resource);
    }

    @Override
    public int updateResource(String id, Resource resource) throws Exception {
        Resource res = resourceMapper.find(resource.getId());
        if (res != null && !res.getId().equals(id)) {
            throw new RuntimeException("编号已存在");
        }

        String pid = resourceMapper.findByPid(resource.getId()).getPid();
        if(ChkTools.isNull(pid)) pid = "";
        resource.setId(id);
        resource.setPid(pid);
        return resourceMapper.update(resource);
    }

    @Override
    public int deleteResource(String id) throws Exception {
        return resourceMapper.delete(id);
    }

    @Override
    public Resource getResource(String id) throws Exception {
        return resourceMapper.find(id);
    }

    @Override
    public List<Resource> getAllResource() throws Exception {
        return resourceMapper.findAll();
    }

    @Override
    public List getAllAndCheckedByRoleId(String roleId) throws Exception {
        return resourceMapper.findAllAndCheckedByRoleId(roleId);
    }
}
