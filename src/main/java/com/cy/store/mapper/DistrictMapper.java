package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/** 省市区持久层接口 */
public interface DistrictMapper {

    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 某个区域下的所有列表
     */
    List<District> findByParent(String parent);

    /**
     * 通过code找到对应name
     * @param code
     * @return
     */
    String findCodeByName(String code);

}
