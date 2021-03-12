package com.example.demo.dao;


import com.example.demo.entity.userfollower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface userfollowerRepository extends JpaRepository<userfollower, Integer > {
    public userfollower findByu_logname_AndBu_Logname(String u_logname,String Bu_Logname);
    public void deleteByu_logname_AndBu_Logname(String u_logname,String Bu_Logname);
}
