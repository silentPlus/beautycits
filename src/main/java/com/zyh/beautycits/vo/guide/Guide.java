package com.zyh.beautycits.vo.guide;

public class Guide {
	private Integer id;
	private Integer userid;
	private String guidename;
	private String telephone;
	private String cost;
	private Integer isused;
	private String used;
	private String remark;
	private String createtime;
	private String updatetime;
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getGuidename() {
		return guidename;
	}
	public void setGuidename(String guidename) {
		this.guidename = guidename;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public Integer getIsused() {
		return isused;
	}
	public void setIsused(Integer isused) {
		this.isused = isused;
		switch(isused){
		case 0:this.used = "不可用";break;
		case 1:this.used = "可用";break;
		default : this.used = "";break;
		}
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	@Override
	public String toString() {
		return "Guide [id=" + id + ", userid=" + userid + ", guidename=" + guidename + ", telephone=" + telephone
				+ ", cost=" + cost + ", isused=" + isused + ", used=" + used + ", remark=" + remark + ", createtime="
				+ createtime + ", updatetime=" + updatetime + "]";
	}
}
