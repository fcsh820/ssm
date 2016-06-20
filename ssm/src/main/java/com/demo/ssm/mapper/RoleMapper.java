package com.demo.ssm.mapper;

import com.demo.ssm.po.Role;

import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
public interface RoleMapper {
    /**
     * 添加
     * @param role
     * @return
     */
    public int insert(Role role) throws Exception;

    /**
     * 修改
     * @param role
     * @return
     */
    public int update(Role role) throws Exception;

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(String id) throws Exception;

    /**
     * 第二种写法，有错误，只能删除一个
     * @param ids
     * @return
     */
    public int deleteByIds(String... ids) throws Exception;

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public Role find(String id) throws Exception;

    /**
     * 查询所有记录
     * @return
     */
    public List<Role> findAll() throws Exception;
}
