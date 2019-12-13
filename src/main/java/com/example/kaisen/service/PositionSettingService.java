package com.example.kaisen.service;

import com.example.kaisen.model.EnemyPosition;
import com.example.kaisen.model.MyPosition;
import com.example.kaisen.model.UserRecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PositionSettingService {

    @Autowired
    private EnemyPosition enemyPosition;

    @Autowired
    private UserRecode userRecode;

    int i = 0;

    public void myPositionSetting(MyPosition myPosition, String[][] base) {

        i += 1;
        userRecode.setPlayTime(i);

        //自分の位置設定
        base[myPosition.getVertical()][myPosition.getSide()] = "W";

    }

    public void enemyPositionSetting() {

        //敵の戦艦の位置を乱数で決定
        Random random = new Random();
//        enemyPosition.setEnemyVertical(random.nextInt(5));
//        enemyPosition.setEnemySide(random.nextInt(5));
        enemyPosition.setEnemyVertical(0);
        enemyPosition.setEnemySide(0);
    }

}
