//package com.wcx.learning.utils;
//
//
//import groovy.util.Eval;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.util.StringUtils;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class SequenceUtil {
//    private static Logger logger = LoggerFactory.getLogger(SequenceUtil.class);
//
//    public SequenceUtil() {
//    }
//
//    public static  String generateCode(){
//        return generateCode(Constants.TYPE);
//    }
//
//    public static String generateCode(String type) {
//        Date date = new Date();
//        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String nowTime = sd.format(date);
//        return createSequence(type, type + nowTime, "day", SpringUtil.getBean(StringRedisTemplate.class));
//    }
//
//    public static String createSequence(String type, String value, String dateStrategy, StringRedisTemplate stringRedisTemplate) {
//        String result = nextFormatted(type, value, dateStrategy, stringRedisTemplate);
//        value = Eval.me('"' + result + '"').toString();
//        logger.info("create sequence " + value + ", date: " + new Date());
//        return value;
//    }
//
//    public static String lastSequence(String type, String value, String dateStrategy, StringRedisTemplate stringRedisTemplate) {
//        String result = lastFormatted(type, value, dateStrategy, stringRedisTemplate);
//        value = Eval.me('"' + result + '"').toString();
//        return value;
//    }
//
//    public static String nextFormatted(String type, String value, String dateStrategy, StringRedisTemplate stringRedisTemplate) {
//        String field = checkType(type, dateStrategy);
//        Long number = stringRedisTemplate.opsForHash().increment("sequenceKey", field, StatusConstant.BaseNumber);
//        return String.format(value, number);
//    }
//
//    public static String lastFormatted(String type, String value, String dateStrategy, StringRedisTemplate stringRedisTemplate) {
//        String field = checkType(type, dateStrategy);
//        String number = (String)stringRedisTemplate.opsForHash().get("sequenceKey", field);
//        return String.format(value, Integer.valueOf(number));
//    }
//
//    public static String checkType(String type, String dateStrategy) {
//        if (StringUtils.isEmpty(type)) {
//            throw new NullPointerException("type must is not null");
//        } else {
//            return type + Strategy.seqKeyStrategy(dateStrategy);
//        }
//    }
//}