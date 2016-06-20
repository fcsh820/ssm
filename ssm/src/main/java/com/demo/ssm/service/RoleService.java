package com.demo.ssm.service;

import com.demo.ssm.po.Role;
import com.demo.ssm.vo.ComboTree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
public interface RoleService {

    public int addRole(Role role) throws Exception;

    public int updateRole(String id, Role role) throws Exception;

    public int deleteRoles(String ids) throws Exception;

    public Role getRole(String id) throws Exception;

    public List<Role> getAllRole() throws Exception;

    public List<ComboTree> getComboTreeData() throws Exception;

}
