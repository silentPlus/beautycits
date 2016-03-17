package com.zyh.beautycits.service.user.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyh.beautycits.dao.JdbcBaseDao;
import com.zyh.beautycits.service.base.impl.BaseServiceImpl;
import com.zyh.beautycits.service.user.UserService;
import com.zyh.beautycits.vo.PageInfo;
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
	public ResultMsg getUsersByType(Integer currentPage, Integer type, String username, String realname) {
		ResultMsg resultMsg = new ResultMsg();
		StringBuffer countSql = new StringBuffer("Select count(*) from user where usertype = ? ");
		StringBuffer querySql = new StringBuffer("select * from user where usertype = ? ");
		if (StringUtils.isNotBlank(username)) {
			querySql.append("and username like '%").append(username).append("%' ");
			countSql.append("and username like '%").append(username).append("%' ");
		}
		if (StringUtils.isNotBlank(realname)) {
			querySql.append("and realname like '%").append(realname).append("%' ");
			countSql.append("and realname like '%").append(realname).append("%' ");
		}
		querySql.append("order by ischecked ASC, createtime desc");
		PageInfo<User> pageUser = new PageInfo<User>();
		pageUser.setCurrentPage(currentPage);
		pageUser.setPageSize(2);
		pageUser = userDao.getPageModel(pageUser, querySql, countSql, User.class, type);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(pageUser);
		return resultMsg;
	}
	
	@Override
	public ResultMsg getUsersByType(Integer type) {
		ResultMsg resultMsg = new ResultMsg();
		String querySql = "select * from user where usertype = ? order by ischecked ASC, createtime desc";
		List<User> list = userDao.getList(querySql,User.class, type);
		resultMsg.setState(Results.SUCCESS);
		resultMsg.setMsgEntity(list);
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
		String sql = "select * from user u where u.username = ?";
		User user = userDao.getJavaBean(sql, User.class, username);
		if (user == null) {
			resultMsg.setState(Results.SUCCESS);
		} else {
			resultMsg.setState(Results.ERROR);
		}
		return resultMsg;
	}

	@Override
	public ResultMsg updateUser(User user, Integer id) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "update user u set u.realname = ?, u.sex = ?, u.telephone = ?, u.qq = ?, u.email = ?, u.remark = ?, u.updatetime = now() where u.id = ?";
		int num = userDao.commonUpdate(sql, user.getRealname(), user.getSex(), user.getTelephone(),
				 user.getQq(), user.getEmail(), user.getRemark(), id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

	@Override
	public ResultMsg changePwd(Integer id, String password, String newpassword) {
		ResultMsg resultMsg = new ResultMsg();
		String sql = "select * from user where id = ? and password = ?";
		User user = userDao.getJavaBean(sql, User.class, id, password);
		if (user == null) {
			resultMsg.setState(Results.ERROR);
			resultMsg.setMsg("原密码错误！");
			return resultMsg;
		}
		sql = "update user u set u.password = ?, u.updatetime = now() where u.id = ?";
		int num = userDao.commonUpdate(sql, newpassword, id);
		if (num == 1) {
			resultMsg.setState(Results.SUCCESS);
			return resultMsg;
		}
		resultMsg.setState(Results.ERROR);
		resultMsg.setMsg("操作失败！");
		return resultMsg;
	}

}
