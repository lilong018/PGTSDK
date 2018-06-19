package com.pgt.entity;

import java.io.Serializable;

/**
 * @program: ypmdemo
 * @description:
 * @author: LL
 * @create: 2018-02-25 17:43
 **/
public class DraftDealEntity implements Serializable {
    private String buyerWalletNo;//买方机构账号
    private String sellerWalletNo;//买方机构账号
    private long transAmount;//交易金额 单位分
    private long transFee;//交易手续费 单位分
    private Double buyerFeePercent;//买方手续费比例
    private String invoiceNo;//票号
    private long invoiceAmount;//票面金额 单位分
    private String issueDate;//开票日期
    private String expireDate;//到期日
    private String acceptanceBank;//承兑银行
    private String orderNumber;//订单编号
    private String redirectPlatformUrl;//汇票交易重定向地址
    //选填信息
    private String drawerName;//出票人全称
    private String drawerAccountNo;//出票人账号
    private String drawerBank;//出票人开户行
    private String acceptanceBankNo;//承兑银行行号
    private String acceptanceBankAccountNo;//承兑银行账号
    private String acceptanceBankSimpleName;//承兑银行名称
    private String takerName;//收票人全称
    private String takerAccountNo;//收票人账号
    private String takerBank;//收票人开户行

    public String getBuyerWalletNo() {
        return buyerWalletNo;
    }

    public void setBuyerWalletNo(String buyerWalletNo) {
        this.buyerWalletNo = buyerWalletNo;
    }

    public String getSellerWalletNo() {
        return sellerWalletNo;
    }

    public void setSellerWalletNo(String sellerWalletNo) {
        this.sellerWalletNo = sellerWalletNo;
    }

    public long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(long transAmount) {
        this.transAmount = transAmount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public long getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getAcceptanceBank() {
        return acceptanceBank;
    }

    public void setAcceptanceBank(String acceptanceBank) {
        this.acceptanceBank = acceptanceBank;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getTransFee() {
        return transFee;
    }

    public void setTransFee(long transFee) {
        this.transFee = transFee;
    }



    public String getDrawerName() {
        return drawerName;
    }

    public void setDrawerName(String drawerName) {
        this.drawerName = drawerName;
    }

    public String getDrawerAccountNo() {
        return drawerAccountNo;
    }

    public void setDrawerAccountNo(String drawerAccountNo) {
        this.drawerAccountNo = drawerAccountNo;
    }

    public String getDrawerBank() {
        return drawerBank;
    }

    public void setDrawerBank(String drawerBank) {
        this.drawerBank = drawerBank;
    }

    public String getAcceptanceBankNo() {
        return acceptanceBankNo;
    }

    public void setAcceptanceBankNo(String acceptanceBankNo) {
        this.acceptanceBankNo = acceptanceBankNo;
    }

    public String getAcceptanceBankAccountNo() {
        return acceptanceBankAccountNo;
    }

    public void setAcceptanceBankAccountNo(String acceptanceBankAccountNo) {
        this.acceptanceBankAccountNo = acceptanceBankAccountNo;
    }

    public String getAcceptanceBankSimpleName() {
        return acceptanceBankSimpleName;
    }

    public void setAcceptanceBankSimpleName(String acceptanceBankSimpleName) {
        this.acceptanceBankSimpleName = acceptanceBankSimpleName;
    }

    public String getTakerName() {
        return takerName;
    }

    public void setTakerName(String takerName) {
        this.takerName = takerName;
    }

    public String getTakerAccountNo() {
        return takerAccountNo;
    }

    public void setTakerAccountNo(String takerAccountNo) {
        this.takerAccountNo = takerAccountNo;
    }

    public String getTakerBank() {
        return takerBank;
    }

    public void setTakerBank(String takerBank) {
        this.takerBank = takerBank;
    }

    public Double getBuyerFeePercent() {
        return buyerFeePercent;
    }

    public void setBuyerFeePercent(Double buyerFeePercent) {
        this.buyerFeePercent = buyerFeePercent;
    }

    public String getRedirectPlatformUrl() {
        return redirectPlatformUrl;
    }

    public void setRedirectPlatformUrl(String redirectPlatformUrl) {
        this.redirectPlatformUrl = redirectPlatformUrl;
    }

    @Override
    public String toString() {
        return "DraftDealEntity{" +
                "buyerWalletNo='" + buyerWalletNo + '\'' +
                ", sellerWalletNo='" + sellerWalletNo + '\'' +
                ", transAmount=" + transAmount +
                ", transFee=" + transFee +
                ", buyerFeePercent=" + buyerFeePercent +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", issueDate='" + issueDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", acceptanceBank='" + acceptanceBank + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", redirectPlatformUrl='" + redirectPlatformUrl + '\'' +
                ", drawerName='" + drawerName + '\'' +
                ", drawerAccountNo='" + drawerAccountNo + '\'' +
                ", drawerBank='" + drawerBank + '\'' +
                ", acceptanceBankNo='" + acceptanceBankNo + '\'' +
                ", acceptanceBankAccountNo='" + acceptanceBankAccountNo + '\'' +
                ", acceptanceBankSimpleName='" + acceptanceBankSimpleName + '\'' +
                ", takerName='" + takerName + '\'' +
                ", takerAccountNo='" + takerAccountNo + '\'' +
                ", takerBank='" + takerBank + '\'' +
                '}';
    }
}
