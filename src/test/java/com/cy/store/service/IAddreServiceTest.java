package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IAddreServiceTest {
    @Autowired
    private IAddressService addressService;


    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setName("name");
        address.setModifiedTime(new Date());
        addressService.addNewAddress(1,"33",address);
    }

    @Test
    public void setDefault() {
        addressService.setDefault(2,1,"abc");
    }

    @Test
    public void deleteByAid() {
        addressService.deleteByAid(4,7,"abc");
    }


    @Test
    public void updateInfoAddress() {
        Address address = new Address();
        address.setName("name");
        address.setModifiedTime(new Date());
        addressService.updateInfoAddress(10,"33",address);
    }

}

