package com.cy.store.entity;

import java.io.Serializable;
import java.util.Objects;

 /**地址实体类*/
public class Address extends BaseEntity  {
    /**收货地址id*/
    private Integer aid;
    /**归属的用户id*/
    private Integer uid;
    /**收货人姓名*/
    private String name;
    /**省-名称*/
    private String provinceName;
    /**省-行政代号*/
    private String provinceCode;
    /**市-名称*/
    private String cityName;
    /**市-行政代号*/
    private String cityCode;
    /**区-名称*/
    private String areaName;
    /**区-行政代号*/
    private String areaCode;
    /**邮政编码*/
    private String zip;
     /**详细地址*/
     private String address;
     /**手机*/
     private String phone;
     /**固话*/
     private String tel;
     /**标签*/
     private String tag;
     /**是否默认：0-不默认，1-默认*/
     private Integer isDefault;

     public Integer getAid() {
         return aid;
     }

     public void setAid(Integer aid) {
         this.aid = aid;
     }

     public Integer getUid() {
         return uid;
     }

     public void setUid(Integer uid) {
         this.uid = uid;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getProvinceName() {
         return provinceName;
     }

     public void setProvinceName(String provinceName) {
         this.provinceName = provinceName;
     }

     public String getProvinceCode() {
         return provinceCode;
     }

     public void setProvinceCode(String provinceCode) {
         this.provinceCode = provinceCode;
     }

     public String getCityName() {
         return cityName;
     }

     public void setCityName(String cityName) {
         this.cityName = cityName;
     }

     public String getCityCode() {
         return cityCode;
     }

     public void setCityCode(String cityCode) {
         this.cityCode = cityCode;
     }

     public String getAreaName() {
         return areaName;
     }

     public void setAreaName(String areaName) {
         this.areaName = areaName;
     }

     public String getAreaCode() {
         return areaCode;
     }

     public void setAreaCode(String areaCode) {
         this.areaCode = areaCode;
     }

     public String getZip() {
         return zip;
     }

     public void setZip(String zip) {
         this.zip = zip;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getPhone() {
         return phone;
     }

     public void setPhone(String phone) {
         this.phone = phone;
     }

     public String getTel() {
         return tel;
     }

     public void setTel(String tel) {
         this.tel = tel;
     }

     public String getTag() {
         return tag;
     }

     public void setTag(String tag) {
         this.tag = tag;
     }

     public Integer getIsDefault() {
         return isDefault;
     }

     public void setIsDefault(Integer isDefault) {
         this.isDefault = isDefault;
     }


     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         if (!super.equals(o)) return false;
         Address address1 = (Address) o;
         return Objects.equals(aid, address1.aid) && Objects.equals(uid, address1.uid) && Objects.equals(name, address1.name) && Objects.equals(provinceName, address1.provinceName) && Objects.equals(provinceCode, address1.provinceCode) && Objects.equals(cityName, address1.cityName) && Objects.equals(cityCode, address1.cityCode) && Objects.equals(areaName, address1.areaName) && Objects.equals(areaCode, address1.areaCode) && Objects.equals(zip, address1.zip) && Objects.equals(address, address1.address) && Objects.equals(phone, address1.phone) && Objects.equals(tel, address1.tel) && Objects.equals(tag, address1.tag) && Objects.equals(isDefault, address1.isDefault);
     }

     @Override
     public int hashCode() {
         return Objects.hash(super.hashCode(), aid, uid, name, provinceName, provinceCode, cityName, cityCode, areaName, areaCode, zip, address, phone, tel, tag, isDefault);
     }


     @Override
     public String toString() {
         return "Address{" +
                 "aid=" + aid +
                 ", uid=" + uid +
                 ", name='" + name + '\'' +
                 ", provinceName='" + provinceName + '\'' +
                 ", provinceCode='" + provinceCode + '\'' +
                 ", cityName='" + cityName + '\'' +
                 ", cityCode='" + cityCode + '\'' +
                 ", areaName='" + areaName + '\'' +
                 ", areaCode='" + areaCode + '\'' +
                 ", zip='" + zip + '\'' +
                 ", address='" + address + '\'' +
                 ", phone='" + phone + '\'' +
                 ", tel='" + tel + '\'' +
                 ", tag='" + tag + '\'' +
                 ", isDefault=" + isDefault +
                 '}';
     }
 }
