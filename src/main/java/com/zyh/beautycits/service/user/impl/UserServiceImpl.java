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
	public ResultMsg getUsersByType(Integer type) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select * from user where usertype = ? order by ischecked ASC, createtime desc";
		List<User> userList = userDao.getList(sql, User.class, type);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(userList);
		return resultMsg;
	}

	@Override
	public ResultMsg lockUser(Integer id, Integer ischecked) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update user u set u.ischecked = ?, u.updatetime = now() where u.id = ?";
		int num = userDao.commonUpdate(sql, ischecked, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg checkUser(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update user u set u.ischecked = 1, u.updatetime = now() where u.id = ?";
		int num = userDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg deleteUser(Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "delete user u where u.id = ?";
		int num = userDao.commonUpdate(sql, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg saveUser(User user) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuilder sql = new StringBuilder("insert into user(username, password, realname, sex, telephone, qq, email, usertype, ischecked, remark, createtime)")
				.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
		int num = userDao.commonUpdate(sql.toString(), user.getUsername(), user.getPassword(), user.getRealname(), user.getSex(), user.getTelephone(),
				 user.getQq(), user.getEmail(), user.getUsertype(), user.getIschecked(), user.getRemark());
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg checkUserName(String username, Integer usertype) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select * from user u where u.username = ? and u.usertype = ?";
		User user = userDao.getJavaBean(sql, User.class, username, usertype);
		if (user == null) {
			resultMsg.setState(Results.SUCCESS);
		} else {
			resultMsg.setState(Results.ERROR);
		}
		return resultMsg;
	}

}
