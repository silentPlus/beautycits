package com.zyh.beautycits.vo.bus;

public class Bus {
	private Integer id;
	private Integer userid;
	private String name;
	private Integer bustype;  // 0：小型客车、1：大型客车、2：双层客车
	private String type;
	private String cost;
	private Integer isused;
	private String used;
	private String remark;
	private String createtiem;
	private String updatetime;
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Integer getBustype() {
		return bustype;
	}
	public void setBustype(Integer bustype) {
		this.bustype = bustype;
		switch(bustype){
		case 0:this.type = "小型客车";break;
		case 1:this.type = "大型客车";break;
		case 2:this.type = "双层客车";break;
		default : this.type = "";break;
		}
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
		return "Bus [id=" + id + ", userid=" + userid + ", name=" + name + ", bustype=" + bustype + ", type=" + type
				+ ", cost=" + cost + ", isused=" + isused + ", used=" + used + ", remark=" + remark + ", createtiem="
				+ createtiem + ", updatetime=" + updatetime + "]";
	}
}
