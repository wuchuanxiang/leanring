package com.wcx.learning.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

@Slf4j
public enum MsgDigestUtil {
    MD2("MD2"),
    MD5("MD5"),
    SHA("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512");

    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final String name;
    final ThreadLocal<MessageDigest> mdLocal = new ThreadLocal();

    private MsgDigestUtil(String name) {
        this.name = name;
    }

    public byte[] digest(byte[] targets) {
        MessageDigest md = (MessageDigest) this.mdLocal.get();

        if (md == null) {
            try {
                md = MessageDigest.getInstance(this.name);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("无法获取摘要算法:" + this.name, e);
            }
            this.mdLocal.set(md);
        } else {
            md.reset();
        }
        return md.digest(targets);
    }

    public String digest2HEX(byte[] targets, boolean toLowerCase) {
        return new String(encodeHex(digest(targets), toLowerCase));
    }

    public String digest2HEX(String targets, boolean toLowerCase) {
        try {
            return digest2HEX(targets.getBytes("UTF-8"), toLowerCase);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String digest2HEX(String targets) {
        return digest2HEX(targets, true);
    }

    public static final char[] encodeHex(byte[] data, boolean toLowerCase) {
        char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        int l = data.length;
        char[] out = new char[l << 1];

        int i = 0;
        for (int j = 0; i < l; i++) {
            out[(j++)] = toDigits[((0xF0 & data[i]) >>> 4)];
            out[(j++)] = toDigits[(0xF & data[i])];
        }
        return out;
    }
    /**
     * 按约定对参数进行处理
     * @param paramMap
     * @return
     */
    public static String handleParam(TreeMap paramMap,String appSecret){

        StringBuffer params = new StringBuffer();
        //遍历，拼接，md5
        paramMap.forEach((k, v)-> params.append(k).append("=").append(v).append("&"));

        params.deleteCharAt(params.length() - 1).append(appSecret);
        log.info("拼接生成MD5之前的参数串{},",params.toString());

        String sign = MsgDigestUtil.MD5.digest2HEX(params.toString());

        paramMap.put("Sign", sign);


        return JSONObject.toJSONString(paramMap);
    }

    public static void main(String[] args) {
        String cstring = "AppKey=587533f9f50f416ba85dc6330ff69068&AuthorizationCode=A030&Body=充值&LSAuthorizationCode=A026&Name=A030&Notify_url=http://api.guokai.online/inner_api/wallet/payNotify&OpenID=OpenID&Order_amt=0.01&Order_apd_inf=附加信息&Out_trade_no=CZ20210000000707&PaymentCode=hk&Saledepname=A026浙江白酒6S商贸公司&Shop_appid=wx09bd28b0bab5d1c5&TimeStamp=1624359598000&key=266B4P82HNRJ2H8RZ006T28L0682TTPT2H2R8BNNRXDPD66PF";

        System.out.println(MsgDigestUtil.SHA.digest2HEX(cstring));
        System.out.println(MsgDigestUtil.MD5.digest2HEX(cstring).toUpperCase());


    }
}




