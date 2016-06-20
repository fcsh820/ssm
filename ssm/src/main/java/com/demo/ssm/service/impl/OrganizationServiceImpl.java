package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.OrganizationMapper;
import com.demo.ssm.po.Organization;
import com.demo.ssm.service.OrganizationService;
import com.demo.ssm.util.ChkTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/12/17.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public int addOrganization(Organization organization) throws Exception {
        Organization org = organizationMapper.find(organization.getId());
        if (org != null) {
            throw new RuntimeException("编号已存在");
        }

        String pid = organizationMapper.findByPid(organization.getPid());
        if(ChkTools.isNull(pid)) pid = "";
        organization.setPid(pid);
        organization.setId(UUID.randomUUID().toString());
        return organizationMapper.insert(organization);
    }

    @Override
    public int updateOrganization(String oid, Organization organization) throws Exception {
        Organization org = organizationMapper.find(organization.getId());
        if (org != null && !org.getId().equals(oid)) {
            throw new RuntimeException("编号已存在");
        }

        String pid = organizationMapper.findByPid(organization.getId());
        if(ChkTools.isNull(pid)) pid = "";
        organization.setPid(pid);
        organization.setId(oid);
        return organizationMapper.update(organization);
    }

    @Override
    public int deleteOrganization(String id) throws Exception {
        return organizationMapper.delete(id);
    }

    @Override
    public Organization getOrganization(String id) throws Exception {
        return organizationMapper.find(id);
    }

    @Override
    public List<Organization> getAllOrganization() throws Exception {
        return organizationMapper.findAll();
    }
}
