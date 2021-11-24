package com.wcx.learning;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcx.learning.response.ICBCCustomerResponse;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wuchuanxiang
 * @date 2021/9/8
 */
public class MeirenjiTest {

    @Test
    public void test1() {

        String date1 = "{\"Code\":200,\"Message\":\"操作成功!\",\"Data\":\"<form name=\\\"auto_submit_form\\\" method=\\\"post\\\" action=\\\"https://gw.open.icbc.com.cn/ui/cardbusiness/aggregatepay/b2c/online/ui/consumepurchaseshowpay/V1?app_id=10000000000003176049&sign_type=RSA2&charset=UTF-8&format=json&msg_id=b4874c449ba544768902c3bc0cd83b7d&timestamp=2021-09-08+16%3a33%3a28&sign=bd48KPbZS%2f76MG3AT%2fS08J7akW14o8oXzHoWZ%2bx8QnP0HLznthVZa48%2bcZJ6NHWth8Q52uKpc4Sf5wOicrCrHl9MfESkecgSdEvsump3l4AqhRoW9GAD240XgB%2bAVkf4EkWqkZZnbmvT1AsUDxTYxfUtc3rNNpbybqWnSGFF2AzZ5VsXdUN6ltjPPnfHqt5wTSSiTRA00UvsWYFj7cEO7l3RUHKPKbr%2fEvytkVwiLd%2fmzGC4ZnYw8mTYGbJKfyztTFZmvWE30v518qscIppBisSWIWRcX%2b6llXL78KsPajGYEeYek82UFfnsjsYAV9mg%2fn7k8jVPQeMAOcxhzFiAdQ%3d%3d\\\">\\n<input type=\\\"hidden\\\" name=\\\"biz_content\\\" value=\\\"{&quot;mer_id&quot;:&quot;120204049752&quot;,&quot;out_trade_no&quot;:&quot;CZ20210000000808&quot;,&quot;mer_prtcl_no&quot;:&quot;1202040497520201&quot;,&quot;order_amt&quot;:&quot;1&quot;,&quot;notify_url&quot;:&quot;http://47.96.86.67:679/beinotify/ShowPay_Notify_ML.aspx&quot;,&quot;icbc_appid&quot;:&quot;10000000000003176049&quot;,&quot;openId&quot;:&quot;&quot;,&quot;saledepname&quot;:&quot;A026浙江白酒6S商贸公司&quot;,&quot;body&quot;:&quot;充值&quot;,&quot;subject&quot;:&quot;&quot;,&quot;notify_type&quot;:null,&quot;result_type&quot;:&quot;1&quot;,&quot;mer_acct&quot;:null,&quot;expireTime&quot;:null,&quot;attach&quot;:null,&quot;return_url&quot;:null,&quot;pay_limit&quot;:null,&quot;shop_appid&quot;:&quot;&quot;,&quot;order_apd_inf&quot;:&quot;附加信息&quot;}\\\">\\n<input type=\\\"submit\\\" value=\\\"立刻提交\\\" style=\\\"display:none\\\" >\\n</form>\\n<script>document.forms[0].submit();</script>\"}";
        String date2 = "{\"code\":401,\"data\":\"\",\"message\":\"appkey 或 sign 参数有误，请核对\"}";


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ICBCCustomerResponse icbcCustomerResponse1 = objectMapper.readValue(date1, ICBCCustomerResponse.class);
            System.out.println(icbcCustomerResponse1);

            ICBCCustomerResponse icbcCustomerResponse2 = objectMapper.readValue(date2, ICBCCustomerResponse.class);
            System.out.println(icbcCustomerResponse2);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //ICBCCustomerResponse icbcCustomerResponse = JSONUtil.toBean(date1, ICBCCustomerResponse.class);
        //Result result = JSONUtil.toBean(date1, Result.class);
        //
        //
        //ICBCCustomerResponse icbcCustomerResponse2 = JSONUtil.toBean(date2, ICBCCustomerResponse.class);
        //Result result2 = JSONUtil.toBean(date2, Result.class);
        //

        //System.out.println(icbcCustomerResponse);
        //System.out.println(result);
        //System.out.println();
        //System.out.println(icbcCustomerResponse2);
        //System.out.println(result2);

    }


    @Test
    public void test11() {
        BigDecimal f = new BigDecimal("-1000");
        BigDecimal z = new BigDecimal("100");

        BigDecimal subtract = z.subtract(f.abs());
        System.out.println(subtract);


        System.out.println(z.compareTo(f.abs()));

    }

    @Test
    public void test112() {
        TreeSet<String> set = new TreeSet<>();
        set.add("set1");
        set.add("开始");
        set.add("金融");
        System.out.println(set);
        if (!set.isEmpty()) {
            System.out.println(set.iterator().next());//迭代取第一个
        }


    }



    @Test
    public void test11222() {
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(d.getTime() - 3 * 24 * 60 * 60 * 1000);
        System.out.println("请假开始日期：" + df.format(date));
        System.out.println("请假结束日期：" + df.format(new Date(date.getTime() + (157L * 24 * 60 * 60 * 1000))));
    }

    @Test
    public void test112222() {
    }



}
