package com.zyh.beautycits.vo.bus;

public class Bus {
	private Integer id;
	private Integer userid;
	private Integer bustype;
	private String cost;
	private Integer isused;
	private String remark;
	private String createtiem;
	private String updatetime;
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
	public Integer getBustype() {
		return bustype;
	}
	public void setBustype(Integer bustype) {
		this.bustype = bustype;
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
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatetiem() {
		return createtiem;
	}
	public void setCreatetiem(String createtiem) {
		this.createtiem = createtiem;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	@Override
	public String toString() {
		return "Bus [id=" + id + ", userid=" + userid + ", bustype=" + bustype + ", cost=" + cost + ", isused=" + isused
				+ ", remark=" + remark + ", createtiem=" + createtiem + ", updatetime=" + updatetime + "]";
	}
}
