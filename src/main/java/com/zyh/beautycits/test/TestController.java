package com.zyh.beautycits.test;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zyh.beautycits.controller.BaseController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-conf/spring-applicationContext.xml","classpath:logback.xml"})
public class TestController extends BaseController{
	
	@Test
	public void test(){
		logger.error("error");
	}
	
	public static void main(String[] args) {
		String a = "";
		BigDecimal b = new BigDecimal(StringUtils.isBlank(a)? "0":a);
		System.out.println(b);
	}
}
