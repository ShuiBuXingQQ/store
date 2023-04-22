package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;

import java.util.List;

/** 省市区业务层接口 */
public interface IDistrictService {


    /**
     * 根据父代号来查询区域信息
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);


    /**
     * 根据code来查name
     * @param code
     * @return
     */
    String getNameByCode(String code);


}
