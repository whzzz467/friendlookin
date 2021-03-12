package com.example.demo.service;

import com.example.demo.dao.userRepository;
import com.example.demo.dao.userfollowerRepository;
import com.example.demo.entity.user;
import com.example.demo.entity.userfollower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FLKServiceImpl implements FLKService{
    @Autowired
    private userRepository userRepository;
    @Autowired
    private userfollowerRepository userfollowerRepository;
    @Override
    public void save(user user) {
        userRepository.save(user);
    }

    @Override
    public int findBylogname(String logname, String password) {
        if(userRepository.findBylogname(logname)!=null){
            if(userRepository.findBylogname(logname).getPassword().equals(password)){
                return 1;
            }else{
                return 2;
            }
        }else{
            return 3;
        }
    }

    @Override
    public boolean ensurefindBylogname(String logname) {
        if(userRepository.findBylogname(logname)!=null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Map<String, Object> findAlluser(Integer page) {
        if (page==null) {
            page=1;
        }
        int size=3;
        Sort sort=Sort.by(Sort.Direction.DESC,"logname");
        Page<user> pageData = (Page<user>) userRepository.findAll(PageRequest.of(page-1, size, sort));
        List<user> alluser=pageData.getContent();
        int pages=pageData.getTotalPages();
        Map<String, Object> result=new HashMap<>();
        result.put("alluser",alluser);
        result.put("TotalPages",pages);
        return result;
    }

    @Override
    public Map<String, Object> findByNameLike(String name,Integer page) {
        if (page==null) {
            page=1;
        }
        int size=3;
        Sort sort=Sort.by(Sort.Direction.DESC,"logname");
        Page<user> pageData = (Page<user>) userRepository.findBylognameLike(name,PageRequest.of(page-1, size, sort));
        List<user> alluser=pageData.getContent();
        int pages=pageData.getTotalPages();
        Map<String, Object> result=new HashMap<>();
        result.put("alluser",alluser);
        result.put("TotalPages",pages);
        return result;
    }

    @Override
    @Transactional
    public String followOne(String follower, String befollowed) {
        if(userfollowerRepository.findByu_logname_AndBu_Logname(follower,befollowed)!=null){
            userfollowerRepository.deleteByu_logname_AndBu_Logname(follower,befollowed);
            return "0";
        }
        user u1=userRepository.findBylogname(follower);
        user u2=userRepository.findBylogname(befollowed);
        userfollower followerRecord = new userfollower();
        followerRecord.setU(u1);
        followerRecord.setBu(u2);
        userfollowerRepository.save(followerRecord);
        return "1";
    }

    @Override
    @Transactional
    public String deleteMyinfo(String logname) {
        userRepository.deleteByLogname(logname);
        return "done!";
    }
}
