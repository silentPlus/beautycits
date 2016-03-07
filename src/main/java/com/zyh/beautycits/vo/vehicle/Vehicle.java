package com.zyh.beautycits.vo.vehicle;

public class Vehicle {
	private Integer id;
	private Integer originid;
	private Integer destinationid;
	private Integer vehicletype;
	private String cost;
	private String remark;
	private String createtime;
	private String updatetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOriginid() {
		return originid;
	}
	public void setOriginid(Integer originid) {
		this.originid = originid;
	}
	public Integer getDestinationid() {
		return destinationid;
	}
	public void setDestinationid(Integer destinationid) {
		this.destinationid = destinationid;
	}
	public Integer getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(Integer vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
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
		return "Vehicle [id=" + id + ", originid=" + originid + ", destinationid=" + destinationid + ", vehicletype="
				+ vehicletype + ", cost=" + cost + ", remark=" + remark + ", createtime=" + createtime + ", updatetime="
				+ updatetime + "]";
	}
}
