package com.MoodMelody.MoodMelody.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MoodMelody.MoodMelody.dto.UserDto;
import com.MoodMelody.MoodMelody.entity.Role;
import com.MoodMelody.MoodMelody.entity.User;
import com.MoodMelody.MoodMelody.repository.UserRepository;
import com.MoodMelody.MoodMelody.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;

	
	@Override
	public List<UserDto> getAllUsers() {
		return userRepo.findAll().stream().map(user -> new UserDto(user.getId(),user.getName(),user.getEmail())).toList();
	}
	
	@Override
	public UserDto getUserById(Long id) {

	    User user = userRepo.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("User not found"));

	    return new UserDto(
	            user.getId(),
	            user.getName(),
	            user.getEmail()
	    );
	}
	
	@Override
	public UserDto updateUser(Long id, User updatedUser) {

	    User user = userRepo.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("User not found"));

	    user.setName(updatedUser.getName());
	    user.setEmail(updatedUser.getEmail());

	    User savedUser = userRepo.save(user);

	    return new UserDto(
	            savedUser.getId(),
	            savedUser.getName(),
	            savedUser.getEmail()
	    );
	}
	
	@Override
	public void deleteUser(Long id) {

	    User user = userRepo.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("User not found"));

	    userRepo.delete(user);
	}

}
