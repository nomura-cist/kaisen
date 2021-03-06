package com.example.kaisen.service;

import com.example.kaisen.model.UserRecode;
import com.example.kaisen.repository.IUserRecodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRecodeService {

    @Autowired
    IUserRecodeRepository iUserRecodeRepository;

    public int insert(UserRecode userRecode,String userId) {

        return iUserRecodeRepository.insert(userRecode,userId);
    }

    public List<UserRecode> selectMany(String userId) {

        return iUserRecodeRepository.selectMany(userId);
    }
}
