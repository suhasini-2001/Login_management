package com.demo.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.UserEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;
import com.demo.repository.UserDAO;

@Service("userservice")
public class UserEntityServiceImp implements UserService {

	@SuppressWarnings("rawtypes")
	@Autowired
	UserDAO userDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> showUserEntity() {
		System.out.println("Service layer entity called");
		return (List<UserEntity>) userDao.findAll();

	}

	@SuppressWarnings("unchecked")
	public UserEntity add(UserEntity request) throws DuplicateRecordException {
		Optional<UserEntity> user = userDao.findById(request.getId());
		if (user.isPresent()) {

			throw new DuplicateRecordException("Delivery Entity with this name " + request.getId() + "already Exist");
		} else {
			return (UserEntity) userDao.save(request);
		}

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public UserEntity update(UserEntity bean) throws RecordNotFoundException {
		// userDao.save(bean);
		Optional<UserEntity> user = userDao.findById(bean.getId());
		if (user.isPresent()) {
			return (UserEntity) userDao.save(bean);
		} else {
			throw new RecordNotFoundException("User Entity  id " + bean.getId() + "Doesn't Exist");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteUserById(long UserId) {
		userDao.deleteById(UserId);
		return false;
	}
	@Override
    public UserEntity findById(long userEntityTestId) {
        @SuppressWarnings("unchecked")
        Optional <UserEntity> user=userDao.findById(userEntityTestId);
        return user.get();
    }



public void save(UserEntity testUserEntity) {
    
    
}
}