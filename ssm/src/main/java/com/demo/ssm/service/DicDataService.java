package com.demo.ssm.service;

import com.demo.ssm.po.DicData;
import com.demo.ssm.vo.ComboTree;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public interface DicDataService {

    public void addDicData(DicData dicData) throws Exception;

    public void updateDicData(String id, DicData dicData) throws Exception;

    public void deleteDicData(String id) throws Exception;

    public List<DicData> getByTypeCode(String code) throws Exception;

    public List<ComboTree> getComboTreeDataByTypeCode(String code) throws Exception;

}
