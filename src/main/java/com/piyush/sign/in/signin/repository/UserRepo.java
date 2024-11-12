package com.piyush.sign.in.signin.repository;

import com.piyush.sign.in.signin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,String> {
}
