package com.cy.store.service;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IDistrictServiceTest {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent(){
        //86表示中国，所有省父代号都是86
        List<District> districts = districtService.getByParent("86");
        for (District district : districts) {
            System.out.println(district);
        }
    }






}



