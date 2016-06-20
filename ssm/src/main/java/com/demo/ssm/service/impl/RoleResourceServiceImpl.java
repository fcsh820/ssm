package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.RoleResourceMapper;
import com.demo.ssm.service.RoleResourceService;
import com.demo.ssm.util.ChkTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/3.
 */
@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Transactional
    @Override
    public void add(String roleId, String resourceIds) {
        String[] arrIds = resourceIds.split(",");
        Map<String, Object> map  = new HashMap();
        map.put("roleId", roleId);
        map.put("resourceIds", arrIds);
        roleResourceMapper.deleteByRoleId(roleId);
        if(ChkTools.isNotNull(resourceIds)){
            roleResourceMapper.insert(map);
        }
    }

    public static void main(String[] args) {
        String[] arr = "".split(",");
        System.out.println(arr.length);

    }
}
