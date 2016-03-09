package com.zyh.beautycits.service.user;

import com.zyh.beautycits.vo.ResultMsg;

public interface UserService {
	
	/**
	 * 
	 * getUserByName(根据用户名密码查询用户)
	 *
	 * @param username
	 * @param password
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg getUserByName(String username, String password);
}
