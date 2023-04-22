package com.cy.store.mapper;


import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(7);
        address.setName("san02");
        address.setPhone("234");

        Integer row = addressMapper.insert(address);
        System.out.println(row);
    }

    @Test
    public void countByUid() {
        Integer row = addressMapper.countByUid(1);
        System.out.println(row);
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(7);

        for (Address address : list) {
            System.out.println(address);
        }
    }

    @Test
    public void findByAid(){
        Address byAid = addressMapper.findByAid(1);
        System.out.println(byAid);
    };


    @Test
    public void updateNoDefault(){
         addressMapper.updateNoDefault(1);

    };


    @Test
    public void  updateDefaultByAid(){
        addressMapper.updateDefaultByAid(1,new Date(),"2323");

    }


    @Test
    public void  deleteByAid(){
        addressMapper.deleteByAid(6);
    };


    @Test
    public void  findLastModified(){
        System.out.println(addressMapper.findLastModified(7));

    };


    @Test
    public void  updateInfoByAid(){
        Address address = new Address();
        address.setAid(10);
        address.setName("5555");
        address.setAreaName("东城区");
        address.setPhone("55555");
        System.out.println(addressMapper.updateInfoByAid(address));

    };

}


