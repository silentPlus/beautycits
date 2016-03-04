package com.zyh.beautycits.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.base.BaseClass;
import com.zyh.beautycits.vo.PageInfo;

@Controller
public class BaseController extends BaseClass{
	/**
     * 重置分页参数
     */
    public PageInfo<?> resetPageInfo(HttpServletRequest request) {
        int currentPage = 1;// 当前页
        int pageSize = 10;// 每页显示的数据条数
        String requestURL = request.getRequestURL().toString();// 请求过来的URL

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        return new PageInfo<Object>(currentPage, 9999l, pageSize, null, requestURL + "?");
    }

    /**
     * getRealClientIp:(获得客户端真实IP). <br/>
     */
	public String getRealClientIp(javax.servlet.http.HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

    /* 客户端IP地址*/
    public String getClientIp(HttpServletRequest request){
    	return request.getRemoteAddr().toString();
    }

	protected void StringPrint(HttpServletResponse response, String content) {
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = null;;
		try {
			out = response.getWriter();
			out.print(content);
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(),e);
		} finally {
			if (null != out) {
                out.flush();
                out.close();
            }
		}
	}

	/**
	 * jSonPrint:输出JSON. <br/>
	 */
    protected void jSonPrint(HttpServletResponse response, Object object) {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
        	out = response.getWriter();
            out.println(JSON.toJSONString(object));
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(),e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }


    /**
     * 获取所有request请求参数key-value
     */
    protected Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }

}
