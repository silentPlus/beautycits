package com.zyh.beautycits.controller.line;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.line.LineShowService;
import com.zyh.beautycits.service.line.LineTypeService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.LineType;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("lineshow")
public class LineShowController extends BaseController {
	
	@Autowired
	private LineShowService lineShowService;
	
	@Autowired 
	private LineTypeService lineTypeService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (isLogin()) {
			// 获取登录用户信息
			User user = getSessionUser();
			mav.addObject("user", user);
		}
		
				
		// 获取线路信息
		ResultMsg resultMsg = lineShowService.getLinesShow(1, null, null);
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("lineShowsPage", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		List<LineType> listLineType = lineTypeService.getAllLineType();
		mav.addObject("listLineType", JSON.toJSONString(listLineType));
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("/line_show");
        return mav;
    }
	
	@RequestMapping(value = "/search.html")
    public JsonPackage search(HttpServletRequest request, HttpServletResponse response, Integer currentPage, 
    		Integer linetypeid, String linename){
		JsonPackage jsonPackage = new JsonPackage();
		
		ResultMsg resultMsg =  lineShowService.getLinesShow(currentPage, linename, linetypeid);
		
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
}
