package com.multicampus.teamProj4.bank.user.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multicampus.teamProj4.bank.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	UserEntity fsByfind(String uniquestr);
}
