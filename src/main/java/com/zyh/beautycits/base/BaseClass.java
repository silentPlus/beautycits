package com.zyh.beautycits.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.beautycits.vo.SessionObject;
import com.zyh.beautycits.vo.user.User;

public class BaseClass {
	
    protected final Logger logger = LoggerFactory . getLogger (getClass());

	 /**
     * 全局获取request
     */
	public HttpServletRequest getRequest() {
    	return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

	 /**
     * 全局获取Session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /* 项目目录 http://127.0.0.1/beautycits 注:结尾不带 / */
    public String getProjectPath() {
        return getFullPath().replace(getRequest().getServletPath(), "");
    }

    /* 项目目录 http://127.0.0.1/beautycits/ 注:结尾带/ */
    public String getProjectDir() {
        return getProjectPath() + "/";
    }

    /* 全目录 http://127.0.0.1/beautycits/a/b/c/d.html */
    public String getFullPath() {
    	return getRequest().getRequestURL().toString();
    }

    /* 带上参数的URL  http://127.0.0.1/beautycits/a/b/c/d.html?a=123&b=456&c=789#redict */
    public String getFullPathWithParam() {
    	String queryString = StringUtils.isBlank(getRequest().getQueryString())?"":"?" + getRequest().getQueryString();
    	return getFullPath() + queryString;
    }

    /**
     * goToErrorPage:service返回结果为错误时，可以直接用此方法跳转到错误页面返回错误信息. <br/>
     */
    public ModelAndView goToErrorPage(String errorMsg) {
    	logger.error(errorMsg);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorMsg", errorMsg);
        return new ModelAndView("login", map);
    }
    
    protected String getUrl_BizFunc(String sBiz, String sFunc) {
        return getProjectDir() + sBiz.trim() + "/" + sFunc.trim();
    }
    
    protected SessionObject getSessionAttribute(){
		Object object = getSession().getAttribute("Session_User_Info");
		return (object == null) ? new SessionObject() : (SessionObject) object;
	}


	protected void setSessionAttribute(SessionObject sessionObject){
		if (sessionObject == null) {
			throw new IllegalArgumentException("SessionObject is null");
		}
		getSession().setAttribute("Session_User_Info", sessionObject);
	}
	
	protected boolean isLogin() {
		Object object = getSession().getAttribute("Session_User_Info");
		return (object == null) ? false : true;
	}
	
	protected User getSessionUser() {
		SessionObject sessionObject = getSessionAttribute();
		return sessionObject.getUser();
	}

}
