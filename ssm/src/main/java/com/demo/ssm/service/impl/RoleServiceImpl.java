package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.RoleMapper;
import com.demo.ssm.po.Role;
import com.demo.ssm.service.RoleService;
import com.demo.ssm.vo.ComboTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/1.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int addRole(Role role) throws Exception {
        role.setId(UUID.randomUUID().toString());
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(String id, Role role) throws Exception {
        role.setId(id);
        return roleMapper.update(role);
    }

    @Override
    public int deleteRoles(String ids) throws Exception {
        String[] arrIds = ids.split(",");
        return roleMapper.deleteByIds(arrIds);
    }

    @Override
    public Role getRole(String id) throws Exception {
        return roleMapper.find(id);
    }

    @Override
    public List<Role> getAllRole() throws Exception {
        return roleMapper.findAll();
    }

    @Override
    public List<ComboTree> getComboTreeData() throws Exception {
        List<ComboTree> trees = new ArrayList<ComboTree>();
        List<Role> roles = roleMapper.findAll();
        for (Role role : roles) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(role.getId());
            comboTree.setText(role.getName());

            trees.add(comboTree);
        }
        return trees;
    }
}
