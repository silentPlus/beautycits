package com.zyh.beautycits.controller.export;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyh.beautycits.controller.BaseController;
import com.zyh.beautycits.service.user.UserService;
import com.zyh.beautycits.utils.DateUtil;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.user.User;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@RestController
@RequestMapping("export")
public class ExportUserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "users.html")
	public void export(HttpServletRequest request, HttpServletResponse response, Integer type) throws Exception {
		String title = "公司人员信息表";
		if (type == 0) {
			title = "游客信息表";
		}
		if (type == 1) {
			title = "旅行社信息表";
		}
		
		// 查询用户信息
		ResultMsg resultMsg = userService.getUsersByType(type);
		if (resultMsg.getState() == Results.ERROR) {
			goToErrorPage(resultMsg.getMsg());
		}
		List<User> userList = (List<User>) resultMsg.getMsgEntity();
		try {
			 
			String encodeName=	dlFileNameEncode(title,response);
			response.setHeader("Content-Disposition","attachment; filename=" + encodeName + DateUtil.getDateTime() + ".xls");
			WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());	
		    WritableSheet sheet = workbook.createSheet(title, 0);
		    sheet.getSettings().setDefaultRowHeight(20);
		    
	    	// 参数最全的是         WritableFont的常量,字体的大小，字体的粗细，是否斜体，下划线 样式,颜色以及脚本
			WritableCellFormat wcf_title04 = new WritableCellFormat(new WritableFont(WritableFont.createFont("微软雅黑"),15, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE )); 
			wcf_title04.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
			// 四边边框范围   边框的粗细  边框的颜色           
			wcf_title04.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //设置边框  
			wcf_title04.setWrap(true); // 自动换行
			
            sheet.mergeCells(0, 0, 12, 0);
			sheet.addCell(new Label(0, 0, title,wcf_title04));
            
   		    // 参数最全的是         WritableFont的常量,字体的大小，字体的粗细，是否斜体，下划线 样式,颜色以及脚本
			WritableCellFormat wcf_l = new WritableCellFormat(new WritableFont(WritableFont.TIMES,12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE ,Colour.BLACK)); 
			wcf_l.setAlignment(jxl.format.Alignment.LEFT); // 设置对齐方式  
			// 四边边框范围   边框的粗细  边框的颜色           
			wcf_l.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //设置边框  
			wcf_l.setWrap(true); // 自动换行
			
			// 参数最全的是         WritableFont的常量,字体的大小，字体的粗细，是否斜体，下划线 样式,颜色以及脚本
			WritableCellFormat wcf_c = new WritableCellFormat(new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE ,Colour.BLACK)); 
			wcf_c.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
			// 四边边框范围   边框的粗细  边框的颜色           
			wcf_c.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //设置边框  
			wcf_c.setWrap(true); // 自动换行
			
			// 表头
			sheet.addCell(new Label(0, 1, "#", wcf_c));
			sheet.addCell(new Label(1, 1, "姓名", wcf_c));
		    sheet.addCell(new Label(2, 1, "性别", wcf_c));
		    sheet.mergeCells(3, 1, 4, 1);
		    sheet.addCell(new Label(3, 1, "手机", wcf_c));
		    sheet.mergeCells(5, 1, 6, 1);
		    sheet.addCell(new Label(5, 1, "qq", wcf_c));
		    sheet.mergeCells(7, 1, 8, 1);
		    sheet.addCell(new Label(7, 1, "email", wcf_c));
		    sheet.addCell(new Label(9, 1, "状态", wcf_c));
		    sheet.mergeCells(10, 1, 12, 1);
		    sheet.addCell(new Label(10, 1, "备注", wcf_c));
			for (int i = 0; i < userList.size(); i++) {
				User user = userList.get(i);
				sheet.addCell(new Number(0,i+2,i+1,wcf_c));
				sheet.addCell(new Label(1, i+2, user.getRealname(), wcf_c));
				if (user.getSex() == 1) {
					sheet.addCell(new Label(2, i+2, "男", wcf_c));
				} else {
					sheet.addCell(new Label(2, i+2, "女", wcf_c));
				}
			    sheet.mergeCells(3, i+2, 4, i+2);
			    String telephone = StringUtils.isBlank(user.getTelephone())?"":user.getTelephone();
			    if (StringUtils.isBlank(telephone)) {
			    	sheet.addCell(new Label(3, i+2, "", wcf_l));
			    } else {
			    	sheet.addCell(new Number(3, i+2, Double.parseDouble(telephone), wcf_l));
			    }
			    sheet.mergeCells(5, i+2, 6, i+2);
			    String qq = StringUtils.isBlank(user.getQq())?"":user.getQq();
			    if (StringUtils.isBlank(qq)) {
			    	sheet.addCell(new Label(5, i+2, "", wcf_l));
			    } else {
			    	sheet.addCell(new Number(5, i+2, Double.parseDouble(qq), wcf_l));
			    }
			    sheet.mergeCells(7, i+2, 8, i+2);
			    sheet.addCell(new Label(7, i+2, user.getEmail(), wcf_l));
			    if (user.getIschecked() == 0) {
			    	sheet.addCell(new Label(9, i+2, "未审核", wcf_c));
			    } else if (user.getIschecked() == 1){
			    	sheet.addCell(new Label(9, i+2, "正常", wcf_c));
			    } else {
			    	sheet.addCell(new Label(9, i+2, "锁定", wcf_c));
			    }
			    sheet.mergeCells(10, i+2, 12, i+2);
			    sheet.addCell(new Label(10, i+2, user.getRemark(), wcf_c));
			}
   			workbook.write();
			workbook.close();
			
		} catch (Exception e) {
			logger.error("导出用户信息表异常", e);
		}
	}
	
	
}
