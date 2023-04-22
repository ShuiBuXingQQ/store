package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/** 省市区实现类 */
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;


    @Override
    public List<District> getByParent(String parent) {
        /**
         * 在进行网络数据进行传输时，为了尽量避免无效数据的传输，可将不用的数据设置为null
         * 可以节省浏览，另一方面提升效率
          */
        List<District> list = districtMapper.findByParent(parent);
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }

        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return  districtMapper.findCodeByName(code);
    }
}
