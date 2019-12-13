package com.example.kaisen.repository;

import com.example.kaisen.model.UserRecode;

import java.util.List;

public interface IUserRecodeRepository {

    public int insert(UserRecode userRecode,String userId);

    public List<UserRecode> selectMany(String userId);
}
