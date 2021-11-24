package com.wcx.learning;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.wcx.learning.utils.AClass;
import com.wcx.learning.utils.DateUtils;
import com.wcx.learning.utils.MsgDigestUtil;
import com.wcx.learning.utils.ObjectId;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

//@SpringBootTest
class LearningApplicationTests {

	@Test
	void contextLoads() {
		ArrayList<Integer> integers = buildNotLotteryArray();
		for (int i = 0; i < integers.size(); i++) {
			System.out.println(integers.get(i));
		}
	}
	@Test
	void test2() {
		int i=30;
		int totalman=1200;
		int number =totalman/i;
		System.out.println("第几个会中奖"+number);
		int now =300;
		System.out.println("取余"+now % number);

		System.out.println();

	}
	@Test
	void test21() {
		String url = "http://47.96.86.67:678/ICBC/ConsumePurchaseShowPayNotify";

		String cstring = "587533f9f50f416ba85dc6330ff69068";

		System.out.println(MsgDigestUtil.SHA.digest2HEX(url+cstring));
		System.out.println(MsgDigestUtil.MD5.digest2HEX(url+cstring));


		Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
//签名
		byte[] signed = sign.sign((url+cstring).getBytes(StandardCharsets.UTF_8));
		System.out.println(signed.toString());
//验证签名
		boolean verify = sign.verify((url+cstring).getBytes(StandardCharsets.UTF_8), signed);
		System.out.println(verify);
	}

	@Test
	void test212() {
		Map<String, Object> map = new HashMap<>();

		map.put("activityCode", "HD202106000011");
		map.put("weixinOpenId", "123");

		String url = "127.0.0.1:9100/lottery/draw";

		for (int i = 1; i <= 10; i++) {
			map.put("logisticsCode", i);
			String post = HttpUtil.post(url, JSONUtil.toJsonStr(map));
			System.out.println(post);

		}
	}
	@Test
	void test3() {
		Date now = new Date();
		Date after = DateUtils.addSeconds(now, 86400);

		System.out.println(now.compareTo(after));
		System.out.println(after.compareTo(now));
		System.out.println(after.compareTo(after));

		System.out.println( DateUtil.now());

		Date nowDate = new Date();
		String nowString = DateUtil.formatDateTime(nowDate);
		System.out.println(nowString);
	}

	@Test
	void test4() {
		StringBuffer params = new StringBuffer();

		params.append("123456");

		StringBuffer stringBuffer = params.deleteCharAt(params.length() - 1);
		System.out.println(stringBuffer.toString());

	}
	@Test
	void test5() {
		BigDecimal bigDecimal = new BigDecimal(-1);
		System.out.println(bigDecimal.compareTo(BigDecimal.ZERO));
		BigDecimal bigDecimal1 = new BigDecimal(1);
		System.out.println(bigDecimal1.compareTo(BigDecimal.ZERO));

		BigDecimal bigDecimal2 = new BigDecimal(0);
		System.out.println(bigDecimal2.compareTo(BigDecimal.ZERO));

	}
	@Test
	void test6() {
		System.out.println(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		System.out.println(IdUtil.simpleUUID());
	}

	@Test
	void test61() {
		BigDecimal b1 = new BigDecimal(123.123); //这种用法 123.123 将被视为 double 类型，产生精度丢失
		BigDecimal b2 = BigDecimal.valueOf(123.123); //return new BigDecimal(Double.toString(val)); 源码先转成字符串，不会丢失精度

		System.out.println(b1);
		//123.1230000000000046611603465862572193145751953125
		System.out.println(b2);
		//123.123
	}
	@Test
	void redission() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		config.useSingleServer().setPassword("redis1234");

		final RedissonClient client = Redisson.create(config);
		RLock lock = client.getLock("lock1");

		try{
			lock.lock();
		}finally{
			lock.unlock();
		}
	}


	@Test
	void objectIdTest() {
		System.out.println(new ObjectId().toString());
		System.out.println(IdUtil.objectId());

	}


















	private ArrayList<Integer> buildNotLotteryArray(){
		ArrayList<Integer> notLottery = new ArrayList<>();
		Random rand = new Random();
		int numberOne = rand.nextInt(3) + 1;
		notLottery.add(numberOne);
		if (numberOne >= 2) {
			// numberOne=  3||2
			int numberTwo = numberOne - 1;
			// 2||1
			notLottery.add(numberTwo);
		} else {
			// numberOne=  1
			int numberTwo = numberOne + 1;
			// 2
			notLottery.add(numberTwo);
		}
		int numberThree = rand.nextInt(3) + 1;
		notLottery.add(numberThree);

		return notLottery;
	}
}
