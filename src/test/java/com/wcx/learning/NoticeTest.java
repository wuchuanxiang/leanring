package com.wcx.learning;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.wcx.learning.utils.AClass;
import com.wcx.learning.utils.DateUtils;
import com.wcx.learning.utils.MsgDigestUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

//@SpringBootTest
class NoticeTest {

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
	void test1() {
		AClass aClass = new AClass();
		aClass.setId(1L);
		aClass.setSid("aaaa");
		testparam(aClass);
	}

	private void  testparam(AClass a){
		String sid = a.getSid();
		a.setSid("123");
		System.out.println(sid);
		System.out.println(a.getSid());

	}

private void  testparam2(AClass a){
		String sid = a.getSid();
		a.setSid("123");
		System.out.println(sid);
		System.out.println(a.getSid());

	}



}
