package com.demo.ssm.controller;


import com.demo.ssm.po.Resource;
import com.demo.ssm.service.ResourceService;
import com.demo.ssm.vo.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/1.
 */
@Controller
@Scope("prototype")
@RequestMapping("/resource")
public class ResourceController {

    private static Logger logger = Logger.getLogger(ResourceController.class);

    @javax.annotation.Resource
    private ResourceService resourceService;

    @RequestMapping("/index")
    public String index() {
        return "/jsp/resource/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map list() throws Exception {
        List<Resource> list = resourceService.getAllResource();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list.size());
        map.put("rows", list);
        return map;
    }

    @RequestMapping("/get_tree")
    @ResponseBody
    public List getTreeDataByRoleId(String role_id) throws Exception {
        return resourceService.getAllAndCheckedByRoleId(role_id);
    }

    @RequestMapping("/add_page")
    public String addPage() {
        return "/jsp/resource/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Resource resource) {
        Result result = new Result();
        try {
            resourceService.addResource(resource);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("添加资源失败：", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/edit_page")
    public String editPage(Model model, String id) throws Exception {
        Resource resource = resourceService.getResource(id);
        model.addAttribute("resource", resource);
        return "/jsp/resource/edit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result eidt(String id, Resource resource) {
        Result result = new Result();
        try {
            resourceService.updateResource(id, resource);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("编辑资源失败：", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result del(String id) {
        Result result = new Result();
        try {
            resourceService.deleteResource(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("删除资源失败：", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
