package com.zyh.beautycits.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.user.UserService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.Results;
import com.zyh.beautycits.vo.user.User;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	@Autowired
	private JdbcBaseDao<User> userDao;

	@Override
	public ResultMsg getUserByName(String username, String password) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select * from user u where u.username = ? ";
		User user = userDao.getJavaBean(sql, User.class, username);
		if (user == null) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("未查询到该账号信息，请先注册后再登录！");
			return resultMsg;
		}
		if (!password.equals(user.getPassword())) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("密码错误！");
			return resultMsg;
		}

		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(user);
		return resultMsg;
	}

	@Override
	public ResultMsg getUsers() {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select * from user where usertype <> 3 order by createtime desc";
		List<User> userList = userDao.getList(sql, User.class);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(userList);
		return resultMsg;
	}

}
