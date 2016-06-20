package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.DicDataMapper;
import com.demo.ssm.mapper.DicTypeMapper;
import com.demo.ssm.po.DicData;
import com.demo.ssm.po.DicType;
import com.demo.ssm.service.DicDataService;
import com.demo.ssm.vo.ComboTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/2/19.
 */
@Service
public class DicDataServiceImpl implements DicDataService {

    @Resource
    private DicTypeMapper dicTypeMapper;
    @Resource
    private DicDataMapper dicDataMapper;

    @Override
    public void addDicData(DicData dicData) {
        dicData.setId(UUID.randomUUID().toString());
        dicDataMapper.insert(dicData);
    }

    public void updateDicData(String id, DicData dicData) {
        dicData.setId(id);
        dicDataMapper.update(dicData);
    }

    @Override
    public void deleteDicData(String id) {
        dicDataMapper.delete(id);
    }

    @Override
    public List<DicData> getByTypeCode(String code) {
        return dicDataMapper.findByTypeCode(code);
    }

    @Override
    public List<ComboTree> getComboTreeDataByTypeCode(String code) {
        List<ComboTree> nodes = new LinkedList<ComboTree>();
        List<DicData> list = dicDataMapper.findByTypeCode(code);
        ComboTree comboTree;
        for (DicData dicData : list) {
            comboTree = new ComboTree();
            comboTree.setId(dicData.getId());
            comboTree.setPid(dicData.getPid());
            comboTree.setText(dicData.getName());
            nodes.add(comboTree);
        }
        DicType dicType = dicTypeMapper.findByCode(code);
        comboTree = new ComboTree();
        comboTree.setId(dicType.getId());
        comboTree.setPid("");
        comboTree.setText(dicType.getName());
        nodes.add(comboTree);
        List<ComboTree> treeNodes = ComboTree.formatTree(nodes, "");
        return treeNodes;
    }
}
