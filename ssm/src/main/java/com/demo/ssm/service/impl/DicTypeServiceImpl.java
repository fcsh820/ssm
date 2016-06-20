package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.DicTypeMapper;
import com.demo.ssm.po.DicType;
import com.demo.ssm.service.DicTypeService;
import com.demo.ssm.vo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Resource
    private DicTypeMapper dicTypeMapper;

    @Override
    public List<Tree> getTypeTreeData() throws Exception {
        List<Tree> trees = new ArrayList<Tree>();
        List<DicType> list = dicTypeMapper.findAll();
        for (DicType dicType : list) {
            Tree tree = new Tree();
            tree.setId(dicType.getId());
            tree.setPid(dicType.getPid());
            tree.setName(dicType.getName());
            tree.setTag(dicType.getCode());
            tree.setOpen(true);
            trees.add(tree);
        }
        return trees;
    }
}
