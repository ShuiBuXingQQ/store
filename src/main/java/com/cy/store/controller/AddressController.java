package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;


    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getAddressUid( HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    //RestFul风格的请求编写
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,HttpSession session) {
        addressService.setDefault(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    //RestFul风格的请求编写
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session) {
        addressService.deleteByAid(aid,getuidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }


    @RequestMapping("update")
    public JsonResult<Void> update(Integer aid,Address address,HttpSession session) {
        addressService.updateInfoAddress(aid,getUsernameFromSession(session),address);
        return new JsonResult<>(OK);
    }

    @GetMapping("get_by_aid")
    public JsonResult<Address> getByAid(Integer aid,HttpSession session) {
        Address data = addressService.getByAid(aid);
        return new JsonResult<>(OK, data);
    }

}
