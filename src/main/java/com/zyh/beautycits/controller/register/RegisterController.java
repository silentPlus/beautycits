package com.zyh.beautycits.controller.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zyh.beautycits.service.user.UserService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/register");
        return mav;
    }
	
	@RequestMapping(value = "/doregister.html")
    public JsonPackage doRegister(HttpServletRequest request, HttpServletResponse response, User user) {
		JsonPackage jsonPackage = new JsonPackage();
		ResultMsg resultMsg = userService.checkUserName(user.getUsername(), user.getUsertype());
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("该用户名已存在！");
			return jsonPackage;
		}
		resultMsg = userService.saveUser(user);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		return jsonPackage;
    }
}
