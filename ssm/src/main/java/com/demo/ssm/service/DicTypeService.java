package com.demo.ssm.service;

import com.demo.ssm.po.DicType;
import com.demo.ssm.vo.Tree;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public interface DicTypeService {

    public List<Tree> getTypeTreeData() throws Exception;

}
