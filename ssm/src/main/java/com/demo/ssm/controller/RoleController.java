package com.demo.ssm.controller;

import com.demo.ssm.po.Role;
import com.demo.ssm.service.RoleService;
import com.demo.ssm.vo.ComboTree;
import com.demo.ssm.vo.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleController {

    private static Logger logger = Logger.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

    @RequestMapping("/list")
    public String list() {
        return "/jsp/role/list";
    }

    @RequestMapping("/get_list")
    @ResponseBody
    public List getList() throws Exception {
        List<Role> list = roleService.getAllRole();
        return list;
    }

    @RequestMapping("/get_combotree")
    @ResponseBody
    public List<ComboTree> getComboTreeData() throws Exception {
        return roleService.getComboTreeData();
    }

    @RequestMapping("/add_page")
    public String addPage() {
        return "/jsp/role/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Role role) {
        Result result = new Result();
        try {
            roleService.addRole(role);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("添加角色失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 编辑页：第一种写法
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit_page")
    public String editPage(Model model, String id) throws Exception{
        Role role = roleService.getRole(id);
        model.addAttribute("role", role);
        return "/jsp/role/edit";
    }

//    /**
//     * 编辑页：第二种写法
//     * @param request
//     * @param id
//     * @return
//     */
//    @RequestMapping("/edit_page")
//    public String editPage(HttpServletRequest request, String id) {
//        Role role = roleService.selectById(id);
//        request.setAttribute("role", role);
//        return "/jsp/role/edit";
//    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(String id, Role role) {
        Result result = new Result();
        try {
            roleService.updateRole(id, role);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("编辑角色失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(String ids) {
        Result result = new Result();
        try {
            roleService.deleteRoles(ids);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("删除角色失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
