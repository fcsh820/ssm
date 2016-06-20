package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.UserMapper;
import com.demo.ssm.mapper.UserRoleMapper;
import com.demo.ssm.po.User;
import com.demo.ssm.po.UserRole;
import com.demo.ssm.service.UserService;
import com.demo.ssm.util.PageInfo;
import com.demo.ssm.util.config.Config;
import com.demo.ssm.util.security.Digest;
import com.demo.ssm.vo.UserVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/11/28.
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void addUser(UserVo userVo) throws Exception{
        User user = new User();
        try {
            PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            logger.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        String uid = UUID.randomUUID().toString();
        user.setId(uid);
        user.setSalt(UUID.randomUUID().toString());
//        String pwd = Digest.md5(MessageFormat.format("{0}:{1}", Config.INIT_PWD, user.getSalt()));  //加盐
        String pwd = Digest.md5(String.format("%s:%s", Config.INIT_PWD, user.getSalt()));
        user.setPwd(pwd);
        user.setStatus(1);
        userMapper.insert(user);

        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();
        for (String rid : roles) {
            userRole.setUserId(uid);
            userRole.setRoleId(rid);
            userRoleMapper.add(userRole);
        }
    }

    @Override
    public void updateUser(String id, UserVo userVo) throws Exception{
        User user = new User();
        user.setId(id);
        try {
            PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            logger.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        userMapper.update(user);
        userRoleMapper.deleteByUserId(id);

        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();
        for (String rid : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(rid);
            userRoleMapper.add(userRole);
        }
    }

    @Override
    public void deleteUser(String id) throws Exception {
        userMapper.delete(id);
    }

    @Override
    public void deleteUsers(String ids) throws Exception {
        String[] arrIds = ids.split(",");
        userRoleMapper.deleteByUserIds(arrIds);
        userMapper.deleteByIds(arrIds);
    }

    @Override
    public User getUser(String id) throws Exception {
        return userMapper.find(id);
    }

    @Override
    public List<User> getAllUser() throws Exception {
        return userMapper.findAll();
    }

    @Override
    public void getUserPage(PageInfo pageInfo) throws Exception {
        pageInfo.setRows(userMapper.findPage(pageInfo));
        pageInfo.setTotal(userMapper.findPageTotal(pageInfo));
    }
}
