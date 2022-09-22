package com.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.demo.entities.UserEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;

@Service
public interface UserService {
	List<UserEntity> showUserEntity();

	public UserEntity add(UserEntity bean) throws DuplicateRecordException;

	public UserEntity update(UserEntity bean) throws RecordNotFoundException;

	public boolean deleteUserById(long UserId);
	public UserEntity findById(long Id);
	
}