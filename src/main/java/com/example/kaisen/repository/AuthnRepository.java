package com.example.kaisen.repository;

import com.example.kaisen.model.Authn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Repository
public class AuthnRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public Authn select(String userId) {
        String sql = "select * from AUTHEN where user_id = ?";

        return jdbc.queryForObject(sql,newInstance(Authn.class),userId);

    }
}