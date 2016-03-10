package com.zyh.beautycits.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.vo.JsonPackage;

@RestController
@RequestMapping("/logout")
public class LogOutController extends BaseController{
	
	@RequestMapping(value = "/index.html")
    public JsonPackage index(HttpServletRequest request, HttpServletResponse response) {
		getSession().invalidate();
        return new JsonPackage();
    }
	
	@RequestMapping(value = "/dologout.html")
    public ModelAndView dologout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		getSession().invalidate();
		String url_login = getUrl_BizFunc("login", "index.html");
		mav.setViewName("redirect:"+url_login);
		return mav;
    }
}
