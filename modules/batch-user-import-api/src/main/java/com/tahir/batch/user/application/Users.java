package com.tahir.batch.user.application;

import java.util.List;

public class Users {
	private List<LiferayUser> users;

	public List<LiferayUser> getUsers() {
		return users;
	}

	public void setUsers(List<LiferayUser> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Users [users=" + users + "]";
	}

}
