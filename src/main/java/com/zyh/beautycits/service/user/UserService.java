package com.zyh.beautycits.service.user;

import com.zyh.beautycits.service.base.BaseService;
import com.zyh.beautycits.vo.ResultMsg;
import com.zyh.beautycits.vo.user.User;

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
	 * getUsersByType(根据type获取用户信息)
	 *
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg getUsersByType(Integer type);
	
	/**
	 * 
	 * lockUser(锁定或解锁用户)
	 *
	 * @param id
	 * @param ischecked
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg lockUser(Integer id, Integer ischecked);
	
	/**
	 * 
	 * checkUser(审核用户)
	 *
	 * @param id
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg checkUser(Integer id);
	
	/**
	 * 
	 * deleteUser(删除用户)
	 *
	 * @param id
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg deleteUser(Integer id);
	
	/**
	 * 
	 * saveUser(保存用户信息)
	 *
	 * @param user
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg saveUser(User user);
	
	/**
	 * 
	 * checkUserName(检测用户名是否重复)
	 *
	 * @param username
	 * @param usertype
	 * @return
	 * @return ResultMsg
	 */
	public ResultMsg checkUserName(String username, Integer usertype);
}
