package com.zyh.beautycits.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: UserLoginController
 * @Description: 游客登录
 *
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {

	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index");
        return mav;
    }
}
