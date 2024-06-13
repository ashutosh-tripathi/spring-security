package com.spring.rest.repository;

import org.springframework.stereotype.Repository;

import com.spring.rest.model.Users;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UsersRepository extends CrudRepository<Users,Integer> {
	List<Users> findByEmail(String email);

}
