package com.demo.ssm.service;

import com.demo.ssm.po.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public interface OrganizationService {

    public int addOrganization(Organization organization) throws Exception;

    public int updateOrganization(String id, Organization organization) throws Exception;

    public int deleteOrganization(String id) throws Exception;

    public Organization getOrganization(String id) throws Exception;

    public List<Organization> getAllOrganization() throws Exception;

}
