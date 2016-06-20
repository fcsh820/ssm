package com.demo.ssm.controller;

import com.demo.ssm.po.DicData;
import com.demo.ssm.service.DicDataService;
import com.demo.ssm.service.DicTypeService;
import com.demo.ssm.vo.ComboTree;
import com.demo.ssm.vo.Result;
import com.demo.ssm.vo.Tree;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
@Controller
@Scope("prototype")
@RequestMapping("/dic")
public class DicController {

    private static Logger logger = Logger.getLogger(OrganizationController.class);

    @Resource
    private DicTypeService dicTypeService;
    @Resource
    private DicDataService dicDataService;

    @RequestMapping("/index")
    public String index() {
        return "/jsp/dic/index";
    }

    @RequestMapping("/get_typetree")
    @ResponseBody
    public List<Tree> getTypeTreeData() throws Exception {
        return dicTypeService.getTypeTreeData();
    }

    @RequestMapping("/get_list")
    @ResponseBody
    public List<DicData> getListByTypeCode(String code) throws Exception {
        return dicDataService.getByTypeCode(code);
    }

    @RequestMapping("/get_type_combotree")
    @ResponseBody
    public List<ComboTree> getComboTreeDataByTypeCode(String code) throws Exception {
        return dicDataService.getComboTreeDataByTypeCode(code);
    }

    @RequestMapping("/add_page")
    public String addPage(String code, Model model) {
        model.addAttribute("code", code);
        return "/jsp/dic/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(DicData dicData) {
        Result result = new Result();
        try {
            dicDataService.addDicData(dicData);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("添加字典失败", e);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
