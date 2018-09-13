package com.subha.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subha.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	
	private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;
	
	static {
		users = populateDummyUsers();
	}
	
	
	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Subhamkar", 23, 70000));
		users.add(new User(counter.incrementAndGet(), "ALS", 23, 80000));
		users.add(new User(counter.incrementAndGet(), "Laxmi", 23, 60000));
		users.add(new User(counter.incrementAndGet(), "Annapragada", 23, 90000));
		return users;
	}
	
	public void deleteAllUsers() {
		users.clear();
	}
	@Override
	public User findById(long id) {
		for(User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	@Override
	public User findByName(String name) {
		for(User user : users) {
			if(user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}
	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
		
	}
	@Override
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
		
	}
	@Override
	public void deleteUserById(long id) {
		for(Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
			}
		}
		
	}
	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return users;
	}
}
