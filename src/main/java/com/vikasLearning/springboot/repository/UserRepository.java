package com.vikasLearning.springboot.repository;

import com.vikasLearning.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
