package com.birol.user.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.birol.user.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);

}
