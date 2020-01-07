package com.example.kaisen.repository;

import com.example.kaisen.model.UserRecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRecodeRepository implements IUserRecodeRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insert(UserRecode userRecode,String userId) {

        int roeNumber = jdbcTemplate.update("insert into USERRECODE(userid, handling, hantei, playtime) values (?,?,?,?)"
        ,userId
        ,userRecode.getHandling()
        ,userRecode.getHantei()
        ,userRecode.getPlayTime());

        return roeNumber;
    }

    //todo Map関係の調査
    @Override
    public List<UserRecode> selectMany(String userId) {

        List<Map<String,Object>> getList = jdbcTemplate.queryForList("select * from USERRECODE " +
                "inner join AUTHEN " +
                "on USERRECODE.USERID = AUTHEN.USER_ID " +
                "where AUTHEN.USER_ID = ?",userId);

        List<UserRecode> userRecodeList = new ArrayList<>();

        for (Map<String,Object> map: getList){

            UserRecode userRecode = new UserRecode();

            userRecode.setHandling((Integer) map.get("handling"));
            userRecode.setHantei((Integer) map.get("hantei"));
            userRecode.setPlayTime((Integer) map.get("playtime"));
            userRecodeList.add(userRecode);
        }

        return userRecodeList;
    }

    @Override
    public int selectOne(String userId) {

        String count = "select count(*) from USERRECODE where USERID = ?";

        int playTime = jdbcTemplate.queryForObject(count,new Object[]{ userId },Integer.class);

        return playTime;
    }
}
