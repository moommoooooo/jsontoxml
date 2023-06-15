package com.example.demoExample.entity;

public class UserEntity {
	public String id;
	public String userId;
	public String name;
	public String age;
	
	public UserEntity(String id, String userId, String name, String age) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.age = age;
	}
	
	public UserEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userId=" + userId + ", name=" + name + ", age=" + age + "]";
	}
	

}
