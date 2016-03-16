package com.zyh.beautycits.test;

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
		int i = 0;
		while(i != 9) {
			if(i == 3) {
				System.out.println(i);
				return;
			}
			System.out.println(i);
			i++;
		}
		System.out.println("jsdfls");
	}
}
