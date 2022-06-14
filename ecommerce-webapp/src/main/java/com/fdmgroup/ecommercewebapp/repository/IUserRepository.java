package com.fdmgroup.ecommercewebapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ecommercewebapp.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	

}
