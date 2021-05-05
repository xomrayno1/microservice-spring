package com.tampro.PhotoAppService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tampro.PhotoAppService.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>{
	Users findByEmail(String email);
}
