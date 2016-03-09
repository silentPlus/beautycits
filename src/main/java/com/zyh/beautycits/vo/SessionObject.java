package com.zyh.beautycits.vo;

import java.io.Serializable;

import com.zyh.beautycits.vo.user.User;

public class SessionObject implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	
	private static final long serialVersionUID = 1L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
