package com.zyh.beautycits.vo.vehicle;

public class Vehicle {
	private Integer id;
	private Integer oareaid;
	private Integer ocityid;
	private Integer oprovinceid;
	private Integer dareaid;
	private Integer dcityid;
	private Integer dprovinceid;
	private Integer vehicletype;   //交通工具类别，1：汽车；2：火车；3：飞机；4：轮船
	private String cost;
	private String remark;
	private String createtime;
	private String updatetime;
	private String origin;
	private String destination;
	private String type;
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
	public Integer getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(Integer vehicletype) {
		this.vehicletype = vehicletype;
		switch (vehicletype) {
			case 1 : this.type = "汽车";break;
			case 2 : this.type = "火车";break;
			case 3 : this.type = "飞机";break;
			case 4 : this.type = "轮船";break;
			default : this.type = "-";break;
		}
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
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Integer getOareaid() {
		return oareaid;
	}
	public void setOareaid(Integer oareaid) {
		this.oareaid = oareaid;
	}
	public Integer getOcityid() {
		return ocityid;
	}
	public void setOcityid(Integer ocityid) {
		this.ocityid = ocityid;
	}
	public Integer getOprovinceid() {
		return oprovinceid;
	}
	public void setOprovinceid(Integer oprovinceid) {
		this.oprovinceid = oprovinceid;
	}
	public Integer getDareaid() {
		return dareaid;
	}
	public void setDareaid(Integer dareaid) {
		this.dareaid = dareaid;
	}
	public Integer getDcityid() {
		return dcityid;
	}
	public void setDcityid(Integer dcityid) {
		this.dcityid = dcityid;
	}
	public Integer getDprovinceid() {
		return dprovinceid;
	}
	public void setDprovinceid(Integer dprovinceid) {
		this.dprovinceid = dprovinceid;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", oareaid=" + oareaid + ", ocityid=" + ocityid + ", oprovinceid=" + oprovinceid
				+ ", dareaid=" + dareaid + ", dcityid=" + dcityid + ", dprovinceid=" + dprovinceid + ", vehicletype="
				+ vehicletype + ", cost=" + cost + ", remark=" + remark + ", createtime=" + createtime + ", updatetime="
				+ updatetime + ", origin=" + origin + ", destination=" + destination + ", type=" + type + "]";
	}
}
