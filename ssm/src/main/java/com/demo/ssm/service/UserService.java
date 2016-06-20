package com.demo.ssm.service;

import com.demo.ssm.po.User;
import com.demo.ssm.util.PageInfo;
import com.demo.ssm.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/11/28.
 */
public interface UserService {

    /**
     * 添加
     * @param userVo
     */
    public void addUser(UserVo userVo) throws Exception;

    /**
     * 修改
     * @param id
     * @param userVo
     */
    public void updateUser(String id, UserVo userVo) throws Exception;

    /**
     * 删除
     * @param id
     */
    public void deleteUser(String id) throws Exception;

    /**
     * 批量删除
     * @param ids
     */
    public void deleteUsers(String ids) throws Exception;

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public User getUser(String id) throws Exception;

    /**
     * 查询所有记录
     * @return
     */
    public List<User> getAllUser() throws Exception;

    /**
     * 查询分页记录
     * @param pageInfo
     */
    public void getUserPage(PageInfo pageInfo) throws Exception;

}
