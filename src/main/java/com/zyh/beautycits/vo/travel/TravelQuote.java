package com.zyh.beautycits.vo.travel;

public class TravelQuote {
	private Integer id;
	private Integer userid;
	private String linename;
	private Integer linedetailid;
	private Integer iscost;   // 0:未交费；1：已缴费，订单进行中；2：已结束
	private Integer num;
	private String remark;
	private String createtime;
	private String updatetime;
	private String time;
	public String getLinename() {
		return linename;
	}
	public void setLinename(String linename) {
		this.linename = linename;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
		return "TravelQuote [id=" + id + ", userid=" + userid + ", linename=" + linename + ", linedetailid="
				+ linedetailid + ", iscost=" + iscost + ", num=" + num + ", remark=" + remark + ", createtime="
				+ createtime + ", updatetime=" + updatetime + ", time=" + time + "]";
	}
}
