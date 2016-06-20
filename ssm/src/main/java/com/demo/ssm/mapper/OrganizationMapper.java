package com.demo.ssm.mapper;

import com.demo.ssm.po.Organization;

import java.util.List;

public interface OrganizationMapper {

    int insert(Organization organization);

    int update(Organization organization);

    int delete(String id);

    Organization find(String id);

    String findByPid(String pid);

    List<Organization> findAll();
}