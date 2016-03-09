package com.zyh.beautycits.service.user;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;

public interface UserService extends BaseService{
	
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
	
	/**
	 * 
	 * getUsers(获取全部用户信息)
	 * @author Mobile Web Group-许倩
	 * @date 2016年3月9日 下午4:18:16
	 *
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg getUsers();
}
