package com.serp.meirenji.commons.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SnowFlake {
//    Logger logger = LoggerFactory.getLogger(SnowFlake.class);
//    @Autowired
//    private BaseCatService baseCatService;
    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 897926400L;
//    private final static long START_STMP = 1546272000L;//2019年1月1日

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    private SnowFlake(){

    }
    private SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    private static class Singleton {
        private static  SnowFlake instance;
        public static void init(long machineId){
            instance = new SnowFlake(Integer.valueOf("9"), machineId);
        };
    }
    public static SnowFlake getInstance() {
        return Singleton.instance;
    }

    @PostConstruct
    public void init() {

        Singleton.init(Long.parseLong("9"));
//        String macId = WebHelper.getMacId();
//        if(StringUtils.isBlank(macId)){
//            throw new RuntimeException("获取不到当前服务器mac地址！");
//        }
//        if(logger.isDebugEnabled()){
//            logger.info("当前服务器macId = [{}]",macId);
//        }
//        List<BaseCat> list = baseCatService.listSonsByParentId("100");
//        boolean hasSet = false;
//        if(list != null && list.size() > 0){
//            for (BaseCat baseCat : list) {
//                String catValue = baseCat.getCatValue();
//                if(StringUtils.equals(macId,catValue)){
//                    int machineId = baseCat.getCatFlag();
//                    Singleton.init(machineId);
//                    hasSet = true;
//                    return;
//                }
//            }
//        }
//        if(!hasSet){
//            Singleton.init(Long.parseLong(Tester.random(1)));
//            logger.error("MacNotFound，未找到mac地址配置，请检查服务器参数配置");
//        }

    }

    public static void main(String[] args) {

        SnowFlake snowFlake = new SnowFlake(6, 1);

        for (int i = 0; i < (1 << 2); i++) {

            System.out.println(snowFlake.nextId());
        }
    }
}