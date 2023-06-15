package com.example.demoExample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demoExample.entity.UserEntity;

@Component
public class UserService {
	
	public static List<UserEntity> list= new ArrayList<>();
//	static {
//		list.add(new UserEntity("1","101","jhon","20"));
//		list.add(new UserEntity("2","102","Rim","21"));
//	}

	public List<UserEntity> getAllUsers(){
		return list;
	}
	
	public UserEntity addUsers(UserEntity u) {
		 list.add(u);
		 return u;
	}
}
