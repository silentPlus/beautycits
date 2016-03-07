package com.zyh.beautycits.vo.ticket;

public class TicketType {
	private Integer id;
	private String name;
	private String remark;
	private String createtiem;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "TicketType [id=" + id + ", name=" + name + ", remark=" + remark + ", createtiem=" + createtiem + "]";
	}
}
