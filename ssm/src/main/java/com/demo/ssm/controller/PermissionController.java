package com.demo.ssm.controller;

import com.demo.ssm.service.RoleResourceService;
import com.demo.ssm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/2/1.
 */
@Controller
@Scope("prototype")
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private RoleResourceService roleResourceService;

    @RequestMapping("/index")
    public String index() {
        return "/jsp/permission/index";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(String roleId, String resourceIds) {
        Result result = new Result();
        try {
            roleResourceService.add(roleId, resourceIds);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
