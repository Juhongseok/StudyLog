package com.fc.bigtraffic.repository;

import com.fc.bigtraffic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
