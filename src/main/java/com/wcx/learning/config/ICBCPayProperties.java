package com.wcx.learning.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wuchuanxiang
 * @date 2021/9/8
 */
@Data
@ConfigurationProperties(prefix = "icbc.pay")
@Component
public class ICBCPayProperties {
    /**
     *商户在微信开放平台的APPID，微信支付且openId有值时必送
     */
    private String  shopAppId ;

    /**
     * appKey
     */
    private String appKey ;

    /**
     * AppSecret
     */
    private String appSecret ;

    /**
     * 客户提供的调用他们的url地址
     */
    private String customerUrl ;

    /**
     * 工行响应成功后回调的URL地址
     */
    private String NotifyUrl ;

}
