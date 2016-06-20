package com.demo.ssm.controller;

import com.demo.ssm.po.User;
import com.demo.ssm.service.UserService;
import com.demo.ssm.util.ChkTools;
import com.demo.ssm.util.PageInfo;
import com.demo.ssm.vo.Result;
import com.demo.ssm.vo.UserVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/28.
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public String list() {
        return "/jsp/user/list";
    }

    @RequestMapping("/get_list")
    @ResponseBody
    public PageInfo getList(UserVo userVo, Integer page, Integer rows) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();
        if(ChkTools.isNotNull(userVo.getUname())){
            condition.put("uname", userVo.getUname());
        }
        pageInfo.setCondition(condition);
        userService.getUserPage(pageInfo);
        return pageInfo;
    }

    @RequestMapping("add_page")
    public String addPage() {
        return "/jsp/user/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(UserVo userVo) {
        Result result = new Result();
        try {
            userService.addUser(userVo);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("添加用户失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("edit_page")
    public String editPage() {
        return "/jsp/user/edit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(String id, UserVo userVo) {
        Result result = new Result();
        try {
            userService.updateUser(id, userVo);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("编辑用户失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/deletes")
    @ResponseBody
    public Result deleteByIds(String ids) {
        Result result = new Result();
        try{
            userService.deleteUsers(ids);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("删除用户失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
