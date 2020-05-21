package com.multicampus.teamProj4.bank.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multicampus.teamProj4.bank.login.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {
	LoginEntity findByUniqueStr(String uniqueStr);
}
