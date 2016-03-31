package com.zyh.beautycits.controller.innerquote;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.innerquote.InnerQuoteService;
import com.zyh.beautycits.service.line.LineService;
import com.zyh.beautycits.vo.JsonPackage;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.line.Line;
import com.zyh.beautycits.vo.user.User;

@RestController
@RequestMapping("innerquote")
public class InnerQuoteController extends BaseController{
	
	@Autowired
	private InnerQuoteService innerQuoteService;
	
	@Autowired
	private LineService lineService;
	
	@RequestMapping(value = "/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 判断是否登录
		if (!isLogin()) {
			String url_login = getUrl_BizFunc("login", "index.html");
			mav.setViewName("redirect:"+url_login);
			return mav;
		}
		
		// 获取登录用户信息
		User user = getSessionUser();
		mav.addObject("user", user);
		
		// 获取旅行社参与线路的报价信息
		ResultMsg resultMsg = innerQuoteService.getInnerQuotes(1, null, user.getId());
		if (resultMsg.getState() == Results.ERROR) {
			return goToErrorPage(resultMsg.getMsg());
		}
		mav.addObject("innerQuotesPage", JSON.toJSONString(resultMsg.getMsgEntity()));
		
		List<Line> listLine = lineService.getAllLine();
		mav.addObject("listLine", JSON.toJSONString(listLine));
		
		// 一些链接
		String url_logout = getUrl_BizFunc("logout", "dologout.html");
		String url_editUser = getUrl_BizFunc("admin", "edituser.html");
		mav.addObject("url_logout", url_logout);
		mav.addObject("url_editUser", url_editUser);
		mav.setViewName("/innerquote");
        return mav;
    }
	
	@RequestMapping(value = "/search.html")
    public JsonPackage search(HttpServletRequest request, HttpServletResponse response, Integer currentPage, 
    		Integer lineid){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		
		User user = getSessionUser();
		ResultMsg resultMsg = innerQuoteService.getInnerQuotes(currentPage, lineid, user.getId());
		
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/offercost.html")
    public JsonPackage offerCost(HttpServletRequest request, HttpServletResponse response, Integer id, String offercost, String grossprofit, String remark){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = innerQuoteService.updateInnerQuote(id, offercost, grossprofit, remark);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
	
	@RequestMapping(value = "/quote.html")
    public JsonPackage quote(HttpServletRequest request, HttpServletResponse response, Integer id){
		JsonPackage jsonPackage = new JsonPackage();
		// 判断是否登录
		if (!isLogin()) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage("请先登录");
			return jsonPackage;
		}
		ResultMsg resultMsg = innerQuoteService.quoter(id);
		if (resultMsg.getState() == Results.ERROR) {
			jsonPackage.setStatus(1);
			jsonPackage.setMessage(resultMsg.getMsg());
			return jsonPackage;
		}
		jsonPackage.setResult(resultMsg.getMsgEntity());
		return jsonPackage;
    }
}
