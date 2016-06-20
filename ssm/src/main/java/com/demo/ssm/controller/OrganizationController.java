package com.demo.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.demo.ssm.po.Organization;
import com.demo.ssm.service.OrganizationService;
import com.demo.ssm.vo.ComboTree;
import com.demo.ssm.vo.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/16.
 */
@Controller
@Scope("prototype")
@RequestMapping("/organization")
public class OrganizationController {

    private static Logger logger = Logger.getLogger(OrganizationController.class);

    @Resource
    private OrganizationService organizationService;

    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/jsp/organization/list");
        return modelAndView;
    }

    @RequestMapping("/get_combotree")
    @ResponseBody
    public List<ComboTree> getComboTreeData() throws Exception {
        List<ComboTree> nodes = new ArrayList<ComboTree>();
        List<Organization> list = organizationService.getAllOrganization();
        for (Organization organization : list) {
            ComboTree comboTree = new ComboTree();
            comboTree.setId(organization.getId());
            comboTree.setPid(organization.getPid());
            comboTree.setText(organization.getName());
            nodes.add(comboTree);
        }
        List<ComboTree> treeNodes = ComboTree.formatTree(nodes, "");
        return treeNodes;
    }

    @RequestMapping("/add_page")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView("/jsp/organization/add");
        return modelAndView;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Organization organization) {
        Result result = new Result();
        try {
            organizationService.addOrganization(organization);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("添加组织失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 第一种写法
     * @param id
     * @return
     */
    /*@RequestMapping("/edit_page")
    public ModelAndView editPage(String id) {
        Organization organization = organizationService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("/jsp/organization/edit");
        modelAndView.addObject("organization", organization);
        return modelAndView;
    }*/

    /**
     * 第二种写法
     * @param request
     * @param id
     * @return
     */
    /*@RequestMapping("/edit_page")
    public String editPage(HttpServletRequest req, String id) {
        Organization organization = organizationService.selectById(id);
        req.setAttribute("organization", organization);
        return "/jsp/organization/edit";
    }*/

    /**
     * 第三种写法
     * @param oid
     * @param model
     * @return
     */
    @RequestMapping("/edit_page")
    public String editPage(String oid, Map model) throws Exception {
        Organization organization = organizationService.getOrganization(oid);
        model.put("organization", organization);
        return "/jsp/organization/edit";
    }

    /**
     * 第一种写法
     * url：var purl = '${basePath}organization/edit/${organization.id}.do';
     * @param id
     * @param organization
     * @return
     */
    /*@RequestMapping("/edit/{id}")
    @ResponseBody
    public Map<String, Object> edit(@PathVariable("id") String id, Organization organization) {
        Map<String, Object> map = new HashMap<String, Object>();
        organization.setId(id);
        try {
            organizationService.update(id, organization);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("error", e.getMessage());
        }
        return map;
    }*/

    /**
     * 第二种写法
     * url：var purl = '${basePath}organization/edit.do?id=${organization.id}';
     * @RequestParam 注解获取GET请求或POST请求提交的参数
     * @CookieValue 获取Cookie的值
     * @param id
     * @param organization
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(@RequestParam("id") String id, Organization organization) {
        Result result = new Result();
        organization.setId(id);
        try {
            organizationService.updateOrganization(id, organization);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("编辑组织失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result delete(String id) {
        Result result = new Result();
        try {
            organizationService.deleteOrganization(id);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("删除组织失败：{}", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 第一种写法
     * @return
     */
    @RequestMapping(value = "/get_list")
    @ResponseBody
    public Map getList() throws Exception {
        List<Organization> list = organizationService.getAllOrganization();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list.size());
        map.put("rows", list);
        return map;
    }

    /**
     * 第二种写法
     * @return
     */
    /*@RequestMapping(value = "/get_list")
    @ResponseBody
    public String getList() {
        List<Organization> list = organizationService.selectAll();
        String json = JSON.toJSONString(list);
        return json;
    }*/

    /**
     * 第三种写法
     * @param out
     */
    /*@RequestMapping(value = "/get_list")
    @ResponseBody
    public void getList(PrintWriter out) {
        List<Organization> list = organizationService.selectAll();
        String json = JSON.toJSONString(list);
        out.write(json);
    }*/

    /**
     * 第四种写法
     * @param req
     * @param resp
     */
    /*@RequestMapping(value = "/get_list")
    @ResponseBody
    public void getList(HttpServletRequest req,
                        HttpServletResponse resp) {
        List<Organization> list = organizationService.selectAll();
        String json = JSON.toJSONString(list);

        PrintWriter out = null;
        resp.setContentType("application/json");

        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(json);
    }*/
}
