package com.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.entities.UserEntity;

@Repository
public interface UserDAO<U> extends CrudRepository<UserEntity, Long> {
}

