package com.pgt.PgtCollection;

import com.chinaums.fsp.base.pgt.TransType;
import com.chinaums.fsp.exception.NotFoundException;
import com.chinaums.fsp.sdk.base.RequestData;
import com.chinaums.fsp.sdk.base.ResponseDataWrapper;
import com.chinaums.fsp.sdk.base.SignType;
import com.chinaums.fsp.sdk.ca.CaGenerator;
import com.chinaums.fsp.sdk.pgt.params.*;
import com.chinaums.fsp.sdk.request.PgtRequestFactory;
import com.pgt.config.PgtConfig;
import com.pgt.entity.DraftDealEntity;
import com.pgt.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xiajinsuo.avro.specific.SpecificRecordBase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: pgtsdk
 * @description: 调用票管通提供的链接类
 * @author: LL
 * @create: 2018-03-22 14:29
 **/
@Component
public class PgtCollection {
    @Autowired
    PgtConfig pgtConfig;

    protected Certificate serverCertificate;
    protected KeyStore clientKeyStore;

    // 机构注册
//218.5.69.218:9088/xrl-pgt-api/api/v1/service.json
//	protected final String	url					= "http://218.5.69.218:9088/xrl-pgt-api/api/v1/register.json";
    //	protected final String	url					= "http://127.0.0.1:8093/api/v1/service.json";
//	protected final String	url					= "http://127.0.0.1:8093/api/v1/register.json";
    // 回调地址
    protected final String callbackUrl = "http://172.16.208.205:30011/xrl-pgt-api/api/v1/callback.json"; // http://127.0.0.1:8761/callback
    //	protected final String callbackUrl = "192.168.199.111:8080/api/v1/pgt/callback/draftDealPay"; // http://127.0.0.1:8761/callback
    protected final String countWalletAccountNo = "0100100010005";//"0100100010005";
    protected final String CMBC_WALLET_ACCOUNT_NO = "695603264000105";
    protected PgtRequestFactory FACTORY;

    protected void setUp(String url, String registerCallbackUrl) throws Exception {
        String KEYSTORE_PASSWORD = pgtConfig.getKeystore_password();
        String PRIVATEKEY_PASSWORD = pgtConfig.getPrivatekey_password();
        String version = pgtConfig.getVersion();
        String appId = pgtConfig.getAppId();
        //D:/finance-pgt-sdk-demo/target/classes/certs/fsp_server.cer
        this.serverCertificate = CaGenerator
                .getCertificate(this.getInputStream("/certs/fsp_server.cer"));
        this.clientKeyStore = CaGenerator.getKeyStore(
                CaGenerator.KEYSTORE_PKCS12,
                this.getInputStream("/certs/fsp_client.pfx"),
                KEYSTORE_PASSWORD.toCharArray());

        FACTORY = new PgtRequestFactory(appId, registerCallbackUrl, version, url, CaGenerator.getPrivateKey(clientKeyStore, PRIVATEKEY_PASSWORD), this.serverCertificate.getPublicKey());
    }


    private InputStream getInputStream(String path) throws Exception {
        InputStream in = PgtCollection.class.getResourceAsStream(path);
        if (null == in) {
            throw new NotFoundException("未找到" + path + "证书");
        }
        return in;
    }

    protected RequestData.Builder createRequestDataBuilder() {
        String appId = pgtConfig.getAppId();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String nanoTime = System.nanoTime() + "";
        RequestData.Builder builder = RequestData.newBuilder();
        builder.setAppId(appId).setCallbackUrl("")
                .setClientTransId(
                        time + nanoTime.substring(nanoTime.length() - 6))
                .setSignType(SignType.RSA)
                .setTransTimestamp(System.currentTimeMillis())
                .setVersion("1.0.0");
        return builder;
    }

    protected void print(ResponseDataWrapper rdw) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append(rdw.toString())
                .append("\r\n");
        SpecificRecordBase responseData = rdw.getResponseData();
        if (null != responseData) {
            sb.append(responseData)
                    .append("\r\n");
        }
        System.out.println(sb.toString());
    }

    /*
    * 机构注册
    *
    * */
    public ResponseDataWrapper organizationRegRequest(OrganizationEntity organizationEntity) throws Exception {
        //注册url
        String url = pgtConfig.getRegisterUrl();
        String callbackUrl = pgtConfig.getCallbackUrl();
        this.setUp(url, callbackUrl);
        PgtRequestFactory rf = FACTORY.factory();
        //组装请求数据
        RequestData.Builder builder = this.createRequestDataBuilder();
        builder.setTransType(TransType.ORGANIZATION_REGISTER.getTransCode());
        OrganizationRegRequest data = OrganizationRegRequest.newBuilder()
                // 测试环境中，银行账号请勿以1和2结尾，否则实名认证会失败
                .setAccountNo(organizationEntity.getAccountNo())
                .setOrgName(organizationEntity.getOrgName())
                .setSettleId(organizationEntity.getSettleId())
                .setRoleType(organizationEntity.getRoleType())
                .setEmail(organizationEntity.getEmail())
                .setLegalName(organizationEntity.getLegalName())
                .setLegalIdcard(organizationEntity.getLegalIdcard())
                .setLegalMobile(organizationEntity.getLegalMobile())
                .setBusinessContactName(organizationEntity.getBusinessContactName())
                .setBusinessContactIdcard(organizationEntity.getBusinessContactIdcard())
                .setBusinessContactMobile(organizationEntity.getBusinessContactMobile())
                .setBusinessLicenseNo(organizationEntity.getBusinessLicenseNo())
                .setAddress(organizationEntity.getAddress())
                .setSpecifiedAccountNo("695603264910011")//测试环境必要条件
                .build();
        rf.transType(TransType.ORGANIZATION_REGISTER.getTransCode())
                .responseClass(OrganizationRegResponse.class)
                .data(data)
        ;
        File file = new File(PgtCollection.class.getClassLoader().getResource("org_cert.zip").getPath());
        ResponseDataWrapper rdw = rf.post(file);
        this.print(rdw);
        return rdw;
    }

    /*
   * 机构注册状态查询
   *
   * */
    public ResponseDataWrapper organizationQueryRequest(String orgName, String businessLicenseNo) throws Exception {

        String url = pgtConfig.getUrl();
//        String callbackUrl = pgtConfig.getCallbackUrl();
        this.setUp(url, callbackUrl);
        PgtRequestFactory rf = FACTORY.factory();

        RequestData.Builder builder = this.createRequestDataBuilder();
        builder.setTransType(TransType.ORGANIZATION_REGISTER_QUERY.getTransCode());

        OrganizationRegQueryRequest data = OrganizationRegQueryRequest.newBuilder()
                .setOrgName(orgName)
                .setBusinessLicenseNo(businessLicenseNo)
                .build();
        rf.transType(TransType.ORGANIZATION_REGISTER_QUERY.getTransCode())
                .responseClass(OrganizationRegQueryResponse.class)
                .data(data)
        ;
        ResponseDataWrapper rdw = rf.post();
        this.print(rdw);
        return rdw;
    }

    /*
   * 票据交易
   * */
    public ResponseDataWrapper draftDealRequest(DraftDealEntity draftDealEntity) throws Exception {
        String url = pgtConfig.getUrl();
        String callbackUrl = "http://16090977.nat123.cc/api/v1/pgt/callback/draftDealPay";
        this.setUp(url, callbackUrl);
        PgtRequestFactory rf = FACTORY.factory();
        RequestData.Builder builder = this.createRequestDataBuilder();
        builder.setTransType(TransType.TRANSACTION_CREATE_ORDER.getTransCode());
        DraftDealRequest data = DraftDealRequest.newBuilder()
                .setBuyerWalletNo(draftDealEntity.getBuyerWalletNo())
                .setSellerWalletNo(draftDealEntity.getSellerWalletNo())
//                .setTransAmount((long) (draftDealEntity.getTransAmount()))
                .setTransAmount(draftDealEntity.getTransAmount())//将交易金额设置为1分
                .setTransFee(draftDealEntity.getTransFee())
                .setBuyerFeePercent(draftDealEntity.getBuyerFeePercent())
                // 票据信息：选填
//															.setDrawerName("深圳市天音通讯发展有限公司")
//															.setDrawerAccountNo("180201401400001784")
//															.setDrawerBank("民生123123123")
//															.setAcceptanceBankNo("301623")
//															.setAcceptanceBankAccountNo("123123123")
//															.setAcceptanceBankSimpleName("shenzhenfenhang")
//															.setTakerName("广东物资燃料有限公司")
//															.setTakerAccountNo("03010141700012347")
//															.setTakerBank("广州分行")
                // 票据信息：五要素
                .setInvoiceNo(draftDealEntity.getInvoiceNo())
//                .setInvoiceAmount(draftDealEntity.getInvoiceAmount())
                .setInvoiceAmount(draftDealEntity.getInvoiceAmount())//将票面金额设置为5分
                .setIssueDate(draftDealEntity.getIssueDate())
                .setExpireDate(draftDealEntity.getExpireDate())
                .setAcceptanceBank(draftDealEntity.getAcceptanceBank())
//                .setRedirectPlatformUrl("http://www.baidu.com/home/news/data/newspage?nid=11706522163785828321&n_type=0&p_from=1")
                .setRedirectPlatformUrl(draftDealEntity.getRedirectPlatformUrl())
                .build();
        rf.transType(TransType.TRANSACTION_CREATE_ORDER.getTransCode())
                .responseClass(DraftDealResponse.class)
                .data(data)
        ;
        ResponseDataWrapper rdw = rf.post();
        //处理票管通的返回参数
        this.print(rdw);
        return rdw;
    }

    /*
    * 汇票交易状态更新
    * orderStatus // 0:待背书 1:待签收 2:待核验
    * */
    public ResponseDataWrapper draftStatusModify(String id, int orderStatus) throws Exception {
        String url = pgtConfig.getUrl();
//        String callbackUrl = pgtConfig.getRegisterUrl();
        this.setUp(url, callbackUrl);
        PgtRequestFactory rf = FACTORY.factory();

        RequestData.Builder builder = this.createRequestDataBuilder();
        builder.setTransType(TransType.TRANSACTION_STATUS_MODIFY.getTransCode());
        DraftStatusModifyRequest data = new DraftStatusModifyRequest();
        data = DraftStatusModifyRequest.newBuilder()
                .setOrigServerTransId(id)
                .setCheckStatus(orderStatus)
                .build();
        rf.transType(TransType.TRANSACTION_STATUS_MODIFY.getTransCode())
                .responseClass(SpecificRecordBase.class)
                .data(data);
        ResponseDataWrapper rdw = rf.post();
        this.print(rdw);
        return rdw;
    }

    /* 测试汇票交易核验查询
     * idType 0：serverProcessId 1：clientTransId
      * */
    public ResponseDataWrapper draftEndorse(String id) throws Exception {
        String url = pgtConfig.getUrl();
        String callbackUrl = "http://16090977.nat123.cc/api/v1/pgt/callback/draftDealPay";
        this.setUp(url, callbackUrl);
        PgtRequestFactory rf = FACTORY.factory();

        RequestData.Builder builder = this.createRequestDataBuilder();
        builder.setTransType(TransType.TRANSACTION_ENDORSE.getTransCode());
        DraftEndorseRequest data = new DraftEndorseRequest();
        data = DraftEndorseRequest.newBuilder()
                .setOrigServerTransId(id)
                .build();

        rf.transType(TransType.TRANSACTION_ENDORSE.getTransCode())
                .responseClass(DraftEndorseResponse.class)
                .data(data)
        ;
        ResponseDataWrapper rdw = rf.post();
        this.print(rdw);
        return rdw;
    }


    /* 测试汇票订单关闭
     * idType 0：serverProcessId 1：clientTransId
      * */
    public ResponseDataWrapper draftDealClose(String id) throws Exception {
        String url = pgtConfig.getUrl();
//        String callbackUrl = pgtConfig.getRegisterUrl();
        this.setUp(url, callbackUrl);
        PgtRequestFactory rf = FACTORY.factory();

        RequestData.Builder builder = this.createRequestDataBuilder();
        builder.setTransType(TransType.TRANSACTION_CLOSE.getTransCode());
        DraftDealCloseRequest data = new DraftDealCloseRequest();
        data = DraftDealCloseRequest.newBuilder()
                .setOrigServerTransId(id)
                .build();

        rf.transType(TransType.TRANSACTION_CLOSE.getTransCode())
                .responseClass(SpecificRecordBase.class)
                .data(data)
        ;
        ResponseDataWrapper rdw = rf.post();
        this.print(rdw);
        return rdw;
    }

    public ResponseDataWrapper balanceQuery(String accountNO) throws Exception {
      String url = pgtConfig.getUrl();
//        String callbackUrl = pgtConfig.getRegisterUrl();
        this.setUp(url, callbackUrl);

        PgtRequestFactory rf = FACTORY.factory();

        RequestData.Builder builder = this.createRequestDataBuilder();
        builder.setTransType(TransType.BALANCE_QUERY.getTransCode());

        OrganizationBalanceQueryRequest data = OrganizationBalanceQueryRequest.newBuilder()
                .setWalletNo(accountNO)
                .build()
                ;
        rf.transType(TransType.ORGANIZATION_BALANCE_QUERY.getTransCode())
                .responseClass(OrganizationBalanceQueryResponse.class)
                .data(data)
        ;
        ResponseDataWrapper rdw = rf.post();

        this.print(rdw);
        return rdw;
    }


    /* 机构角色变更：卖家->全部 */
    public void orgRoleModify(String cardAccNo, String settleId, String walletNo) throws Exception {

        PgtRequestFactory rf = FACTORY.factory();

        OrgRoleModifyRequest data = OrgRoleModifyRequest.newBuilder()
                .setCardAccNo(cardAccNo)
                .setSettleId(settleId)
                .setWalletNo(walletNo)
                .build();
        rf.transType(TransType.ORGANIZATION_ROLE_MODIFY.getTransCode())
                .responseClass(SpecificRecordBase.class).data(data);
        ResponseDataWrapper rdw = rf.post();

        this.print(rdw);
    }
}
