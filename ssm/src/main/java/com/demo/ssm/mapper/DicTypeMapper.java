package com.demo.ssm.mapper;

import com.demo.ssm.po.DicType;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public interface DicTypeMapper {

    /**
     * 查询单条记录
     * @param code
     * @return
     */
    public DicType findByCode(String code);

    /**
     * 查询所有记录
     * @return
     */
    public List<DicType> findAll();
}
