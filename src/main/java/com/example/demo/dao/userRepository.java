package com.example.demo.dao;

import com.example.demo.entity.user;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepository extends JpaRepository<user, Integer > {
    public user findBylogname(String logname);
    public Page<user> findBylognameLike(String name, Pageable p);
    public void deleteByLogname(String logname);
}
