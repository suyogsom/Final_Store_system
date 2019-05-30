package com.bookstore.app.repositories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.bookstore.app.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class UserRepository {
	
	private List<User> dataStore = new ArrayList<>(); 
	
	@PostConstruct
	public void init() throws IOException {
		 
		ObjectMapper objMapper = new ObjectMapper();
		TypeFactory typeFactory = objMapper.getTypeFactory();
		 
		//List<User> user = objMapper.readValue(new File("/Users/suyogsomavanshi/git/BookStore/bookstore_app/bookstore-server/src/resources/Json/User.json"), typeFactory.constructCollectionType(List.class, User.class));
		  
		//dataStore = user;
	 }

	public List<User> getAllUsers() {
		return dataStore;
	}

	public List<User> findByName(String name) {
		List<User> userByName = new ArrayList<>();
		for(int i=0;i<dataStore.size();i++) {
			if(name.trim().equalsIgnoreCase(dataStore.get(i).getName().substring(0, dataStore.get(i).getName().indexOf(" ")))) {
				userByName.add(dataStore.get(i));
			}
			else if(name.trim().equalsIgnoreCase(dataStore.get(i).getName().substring(dataStore.get(i).getName().indexOf(" ")+1))) {
				userByName.add(dataStore.get(i));
			}
			else if(name.isEmpty()){
				userByName = dataStore;
			}
		}
		return userByName;
	}
	
	public void addUser(User user) {
		dataStore.add(user);		
	}

	public void updateUser(User user, String id) {
		dataStore.set(setIndex(id), user);		
	}

	public void deleteById(String id) {
		dataStore.remove(setIndex(id));
	}

	public int setIndex(String id) {
		int index = 0;
		for(int i=0;i<dataStore.size();i++) {
			if(id.trim().equals(dataStore.get(i).getUserId())) {
				index = i;
			}
		}		
		return index;
	}

}
