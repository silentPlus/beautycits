package com.zyh.beautycits.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.vo.JsonPackage;

@Controller
@RequestMapping("/logout")
public class LogOutController extends BaseController{
	
	@RequestMapping(value = "/index.html")
    public JsonPackage index(HttpServletRequest request, HttpServletResponse response) {
		getSession().invalidate();
        return new JsonPackage();
    }
}
