package com.demo.ssm.mapper;

import com.demo.ssm.po.User;
import com.demo.ssm.util.PageInfo;
import com.demo.ssm.vo.UserVo;

import java.util.List;

/**
 * Created by Administrator on 2015/11/28.
 */
public interface UserMapper {
    /**
     * 添加
     * @param user
     * @return
     */
    public int insert(User user) throws Exception;

    /**
     * 修改
     * @param user
     * @return
     */
    public int update(User user) throws Exception;

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(String id) throws Exception;

    /**
     * 第一种写法
     * 批量删除
     * @param ids
     * @return
     */
    /*public int deleteByIds(String[] ids) throws Exception;*/

    /**
     * 第二种写法
     * @param ids
     * @return
     */
    public int deleteByIds(String... ids) throws Exception;

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public User find(String id) throws Exception;

    /**
     * 查询所有记录
     * @return
     */
    public List<User> findAll() throws Exception;

    /**
     * 查询分页记录
     * @param pageInfo
     * @return
     */
    public List<UserVo> findPage(PageInfo pageInfo) throws Exception;

    /**
     * 查询分页记录总数
     * @param pageInfo
     * @return
     */
    public Integer findPageTotal(PageInfo pageInfo) throws Exception;
}
