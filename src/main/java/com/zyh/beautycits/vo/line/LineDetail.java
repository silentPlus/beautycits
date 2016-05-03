package com.zyh.beautycits.vo.line;

public class LineDetail {
	private Integer id;
	private Integer userid;
	private Integer lineid;
	private String linename;
	private Integer govehicleid;
	private String govehicle;
	private String backvehicle;
	private Integer gtype;
	private Integer btype;
	private Integer backvehicleid;
	private String insurance;
	private Integer guideid;
	private String guidename;
	private String remark;
	private String gocost;
	private String backcost;
	private String guidecost;
	private Integer number;
	
	private String createtime;
	private String updatetime;
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getGocost() {
		return gocost;
	}
	public void setGocost(String gocost) {
		this.gocost = gocost;
	}
	public String getBackcost() {
		return backcost;
	}
	public void setBackcost(String backcost) {
		this.backcost = backcost;
	}
	public String getGuidecost() {
		return guidecost;
	}
	public void setGuidecost(String guidecost) {
		this.guidecost = guidecost;
	}
	public Integer getGtype() {
		return gtype;
	}
	public void setGtype(Integer gtype) {
		this.gtype = gtype;
		if (gtype != null) {
			switch (gtype) {
			case 1 : this.govehicle = "汽车";break;
			case 2 : this.govehicle = "火车";break;
			case 3 : this.govehicle = "飞机";break;
			case 4 : this.govehicle = "轮船";break;
			default : this.govehicle = "-";break;
			}
		} else {
			this.govehicle = "-";
		}
	}
	public Integer getBtype() {
		return btype;
	}
	public void setBtype(Integer btype) {
		this.btype = btype;
		if (btype != null) {
			switch (btype) {
			case 1 : this.backvehicle = "汽车";break;
			case 2 : this.backvehicle = "火车";break;
			case 3 : this.backvehicle = "飞机";break;
			case 4 : this.backvehicle = "轮船";break;
			default : this.backvehicle = "-";break;
			}
		} else {
			this.backvehicle = "-";
		}
	}
	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public String getGovehicle() {
		return govehicle;
	}
	public void setGovehicle(String govehicle) {
		this.govehicle = govehicle;
	}
	public String getBackvehicle() {
		return backvehicle;
	}
	public void setBackvehicle(String backvehicle) {
		this.backvehicle = backvehicle;
	}
	public String getGuidename() {
		return guidename;
	}
	public void setGuidename(String guidename) {
		this.guidename = guidename;
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
	public Integer getLineid() {
		return lineid;
	}
	public void setLineid(Integer lineid) {
		this.lineid = lineid;
	}
	public Integer getGovehicleid() {
		return govehicleid;
	}
	public void setGovehicleid(Integer govehicleid) {
		this.govehicleid = govehicleid;
	}
	public Integer getBackvehicleid() {
		return backvehicleid;
	}
	public void setBackvehicleid(Integer backvehicleid) {
		this.backvehicleid = backvehicleid;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public Integer getGuideid() {
		return guideid;
	}
	public void setGuideid(Integer guideid) {
		this.guideid = guideid;
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
		return "LineDetail [id=" + id + ", userid=" + userid + ", lineid=" + lineid + ", linename=" + linename
				+ ", govehicleid=" + govehicleid + ", govehicle=" + govehicle + ", backvehicle=" + backvehicle
				+ ", gtype=" + gtype + ", btype=" + btype + ", backvehicleid=" + backvehicleid + ", insurance="
				+ insurance + ", guideid=" + guideid + ", guidename=" + guidename + ", remark=" + remark + ", gocost="
				+ gocost + ", backcost=" + backcost + ", guidecost=" + guidecost + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + "]";
	}
}
