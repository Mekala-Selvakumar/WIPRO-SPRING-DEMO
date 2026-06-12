package com.learn.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
