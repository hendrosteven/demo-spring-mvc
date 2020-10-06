package com.bca.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bca.entity.User;
import com.bca.repo.UserRepo;
import com.bca.utility.MD5Generator;

@Service("userService")
@Transactional
public class UserService {

	@Autowired
	private UserRepo repo;
	
	public User register(User user) throws Exception {
		
		System.out.println(user.getEmail()+" "+ user.getPassword());
		
		if(repo.findByEmail(user.getEmail())==null) {
			String hash = MD5Generator.generate(user.getPassword());
			user.setPassword(hash);
			return repo.save(user);
		}else {
			throw new Exception("Email already registered");
		}
	}
	
	public User login(String email, String password) throws Exception {
		return repo.findByEmailAndPassword(email, MD5Generator.generate(password));
	}
}
