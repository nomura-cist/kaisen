package com.example.kaisen.service;

import com.example.kaisen.model.AttackPosition;
import com.example.kaisen.model.EnemyPosition;
import com.example.kaisen.model.MyPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VictoryOrDefeatService {

    @Autowired
    private EnemyPosition enemyPosition;

    @Autowired
    private MyPosition myPosition;

    public String shohai(AttackPosition attackPosition,String[][] base,String[][] enemyBase) {

        //敵の攻撃位置を乱数で決定
        Random random = new Random();
        int enemyAttackVertical = random.nextInt(5);
        int enemyAttackSide = random.nextInt(5);

        //勝ち
        if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide()) {

            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";

            return "win";

            //負け
        } else if (enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

            base[enemyAttackVertical][enemyAttackSide] = "x";

            return "lose";

            //引き分け
        } else if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide() &&
                enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";
            base[enemyAttackVertical][enemyAttackSide] = "x";

            return "draw";

        } else {

            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "・";
            base[enemyAttackVertical][enemyAttackSide] = "・";

            return "miss";
        }

    }
}
