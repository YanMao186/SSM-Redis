package com.ssm.chapter6.pojo;

public class Role {

	private Integer id;
	private String roleNo;
	private String roleName;
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", roleNo='" + roleNo + '\'' +
				", roleName='" + roleName + '\'' +
				", note='" + note + '\'' +
				'}';
	}
}
