package com.zyh.beautycits.vo.quote;

public class TravelQuote {
	private Integer id;
	private Integer userid;
	private Integer linedetailid;
	private Integer iscost;
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
	public Integer getLinedetailid() {
		return linedetailid;
	}
	public void setLinedetailid(Integer linedetailid) {
		this.linedetailid = linedetailid;
	}
	public Integer getIscost() {
		return iscost;
	}
	public void setIscost(Integer iscost) {
		this.iscost = iscost;
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
		return "TravelQuote [id=" + id + ", userid=" + userid + ", linedetailid=" + linedetailid + ", iscost=" + iscost
				+ ", remark=" + remark + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}
