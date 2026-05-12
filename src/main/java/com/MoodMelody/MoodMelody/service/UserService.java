package com.MoodMelody.MoodMelody.service;

import java.util.List;

import com.MoodMelody.MoodMelody.dto.UserDto;
import com.MoodMelody.MoodMelody.entity.User;

public interface UserService {
	 public List<UserDto> getAllUsers();
	 UserDto getUserById(Long id);

	 UserDto updateUser(Long id, User user);

	 void deleteUser(Long id);
}
