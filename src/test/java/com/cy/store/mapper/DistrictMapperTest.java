package com.cy.store.mapper;


import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> parent = districtMapper.findByParent("110100");

        for (District district : parent) {
            System.out.println(district);
        }

    }

    @Test
    public void findCodeByName() {
        String nameCode = districtMapper.findCodeByName("110101");
        System.out.println(nameCode);
    }


}


