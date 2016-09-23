package com.model;

import javax.persistence.*;

@Entity
@Table(name="myUser")
public class UserInfo {
	private Integer id;
	private String user_name;
	private String user_pwd;
	private Integer user_age;

	public UserInfo() {
		super();
	}
	public UserInfo(Integer id, String user_name, String user_pwd, Integer user_age) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_age = user_age;
	}

	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="user_name",length=50)
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Column(name="user_pwd",length=100)
	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	@Column(name="user_age")
	public Integer getUser_age() {
		return user_age;
	}

	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}
}
