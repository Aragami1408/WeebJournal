package com.weebindustry.weebjournal.repositories;


import java.util.*; 

import com.weebindustry.weebjournal.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByUsername(String username);
}