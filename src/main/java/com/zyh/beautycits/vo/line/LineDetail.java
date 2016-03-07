package com.zyh.beautycits.vo.line;

public class LineDetail {
	private Integer id;
	private Integer userid;
	private Integer lineid;
	private Integer govehicleid;
	private Integer backvehicleid;
	private String insurance;
	private Integer guideid;
	private String remark;
	private String createtime;
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
		return "LineDetail [id=" + id + ", userid=" + userid + ", lineid=" + lineid + ", govehicleid=" + govehicleid
				+ ", backvehicleid=" + backvehicleid + ", insurance=" + insurance + ", guideid=" + guideid + ", remark="
				+ remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
