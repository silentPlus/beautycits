package com.zyh.beautycits.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.beautycits.base.BaseClass;

@Component
public class MyExceptionHandler extends BaseClass implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
	    if(handler != null){
			HandlerMethod method = (HandlerMethod) handler;
			logger.error("异常拦截的方法:" + method.getMethod());
	    }
	    logger.error("错误信息为:" + ex.toString());

		if (ex instanceof MyException) {//自定义异常
			if (StringUtils.isNotBlank(ex.getMessage())) {
				return goToErrorPage(ex.getMessage());
			}
			return goToErrorPage("异常，请尽快联系管理员！");
		} else if (ex instanceof IOException) {//网络异常或者流异常
			if (StringUtils.isNotBlank(ex.getMessage())) {
				return goToErrorPage(ex.getMessage());
			}
			return goToErrorPage("网络或者流系统出现异常，请稍后重试！");
		} else {//其他异常
			if (StringUtils.isNotBlank(ex.getMessage())) {
				return goToErrorPage(ex.getMessage());
			}
			return goToErrorPage("出现未知错误，请稍后重试或联系管理员！");
		}
	}

}
