package com.demo.ssm.mapper;

import com.demo.ssm.po.DicData;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public interface DicDataMapper {

    /**
     * 添加
     * @param dicData
     * @return
     */
    public int insert(DicData dicData);

    /**
     * 修改
     * @param dicData
     * @return
     */
    public int update(DicData dicData);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(String id);

    /**
     * 根据type code查询
     * @return
     */
    public List<DicData> findByTypeCode(String code);
}
