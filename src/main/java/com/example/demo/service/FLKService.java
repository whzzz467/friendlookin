package com.example.demo.service;

import com.example.demo.entity.user;

import java.util.List;
import java.util.Map;

public interface FLKService {
    public void save(user user);
    public int findBylogname(String logname,String password);
    public boolean ensurefindBylogname(String logname);
    public Map<String,Object> findAlluser(Integer page);
    public Map<String,Object> findByNameLike(String name,Integer page);
    public String followOne(String follower,String befollowed);
    public String deleteMyinfo(String logname);
}
