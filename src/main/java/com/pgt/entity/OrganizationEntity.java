package com.pgt.entity;

import java.io.File;
import java.io.Serializable;

/**
 * @program: pgtsdk
 * @description: 票管通用户实体
 * @author: LL
 * @create: 2018-03-22 14:51
 **/
public class OrganizationEntity implements Serializable {
    private String accountNo;//账号
    private String orgName;//公司名称
    private String settleId;//清算联行号
    private int roleType;//角色类型 角色类型：0:全部 1:买家 2:卖家
    private String email;//邮箱
    private String legalName;//法人姓名
    private String legalIdcard;//法人身份证号
    private String legalMobile;//法人手机号
    private String businessContactName;//业务联系人姓名
    private String businessContactIdcard;//业务联系人身份证号
    private String businessContactMobile;//业务联系人手机号
    private String businessLicenseNo;//营业执照号
    private String address;//地址
    private File file;//上传文件 上传文件：用于上传证件材料，格式必须为ZIP文件，用于审核实名认证。

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSettleId() {
        return settleId;
    }

    public void setSettleId(String settleId) {
        this.settleId = settleId;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalIdcard() {
        return legalIdcard;
    }

    public void setLegalIdcard(String legalIdcard) {
        this.legalIdcard = legalIdcard;
    }

    public String getLegalMobile() {
        return legalMobile;
    }

    public void setLegalMobile(String legalMobile) {
        this.legalMobile = legalMobile;
    }

    public String getBusinessContactName() {
        return businessContactName;
    }

    public void setBusinessContactName(String businessContactName) {
        this.businessContactName = businessContactName;
    }

    public String getBusinessContactIdcard() {
        return businessContactIdcard;
    }

    public void setBusinessContactIdcard(String businessContactIdcard) {
        this.businessContactIdcard = businessContactIdcard;
    }

    public String getBusinessContactMobile() {
        return businessContactMobile;
    }

    public void setBusinessContactMobile(String businessContactMobile) {
        this.businessContactMobile = businessContactMobile;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
