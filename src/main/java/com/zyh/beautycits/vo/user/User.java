package com.zyh.beautycits.vo.user;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String realname;
	private Integer sex;
	private String telephone;
	private String qq;
	private String email;
	private String remark;
	private Integer usertype;  // 用户类型  0：游客；1：合作单位；2：网站工作人员；3：管理员
	private Integer ischecked;  // 是否通过校验，0：未通过；1：通过；2：锁定
	private String createtime;
	private String updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getIschecked() {
		return ischecked;
	}
	public void setIschecked(Integer ischecked) {
		this.ischecked = ischecked;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", sex=" + sex + ", telephone=" + telephone + ", qq=" + qq + ", email=" + email 
				+ ", remark=" + remark + ", usertype=" + usertype + ", ischecked=" + ischecked + ", createtime="
				+ createtime + ", updatetime=" + updatetime + "]";
	}
}
