package com.pgt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: pgtsdk
 * @description: 票管通资源信息配置文件
 * @author: LL
 * @create: 2018-03-26 09:01
 **/
@Component
@ConfigurationProperties(prefix = "pgt.baseRequest")
public class PgtConfig {
    private String url;
    private String registerUrl;
    private String callbackUrl;
    private String keystore_password;
    private String privatekey_password;
    private String appId;
    private String version;
    private String countWalletAccountNo;
    private String cmbc_wallet_account_no;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getKeystore_password() {
        return keystore_password;
    }

    public void setKeystore_password(String keystore_password) {
        this.keystore_password = keystore_password;
    }

    public String getPrivatekey_password() {
        return privatekey_password;
    }

    public void setPrivatekey_password(String privatekey_password) {
        this.privatekey_password = privatekey_password;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCountWalletAccountNo() {
        return countWalletAccountNo;
    }

    public void setCountWalletAccountNo(String countWalletAccountNo) {
        this.countWalletAccountNo = countWalletAccountNo;
    }

    public String getCmbc_wallet_account_no() {
        return cmbc_wallet_account_no;
    }

    public void setCmbc_wallet_account_no(String cmbc_wallet_account_no) {
        this.cmbc_wallet_account_no = cmbc_wallet_account_no;
    }
}
