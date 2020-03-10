package com.example.kaisen.service;

import com.example.kaisen.model.AttackPosition;
import com.example.kaisen.model.EnemyPosition;
import com.example.kaisen.model.MyPosition;
import com.example.kaisen.model.UserRecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Component
public class VictoryOrDefeatService {

    @Autowired
    private EnemyPosition enemyPosition;

    @Autowired
    private MyPosition myPosition;

    @Autowired
    private UserRecode userRecode;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserRecodeService userRecodeService;

    private int i = 0;

    public String shohai(AttackPosition attackPosition,String[][] base,String[][] enemyBase) {

        i += 1;
        userRecode.setHandling(i);

        //敵の攻撃位置を乱数で決定
        Random random = new Random();
        //int enemyAttackVertical = random.nextInt(5);
        //int enemyAttackSide = random.nextInt(5);
        int enemyAttackVertical = 0;
        int enemyAttackSide = 0;

        //勝ち
//        if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide()) {
//
//            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";
//
//            userRecode.setHantei(0);
//
//            String userId = (String)httpSession.getAttribute("userId");
//
//            System.out.println(userRecode.getHandling());
//
//            int rowNumber = userRecodeService.insert(userRecode,userId);
//
//            i = 0;
//
//            return "win";

         if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide() &&
                enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";
            base[enemyAttackVertical][enemyAttackSide] = "x";

            userRecode.setHantei(2);

            String userId = (String)httpSession.getAttribute("userId");

            int rowNumber = userRecodeService.insert(userRecode,userId);

            i = 0;

            return "draw";

            //負け
        } else if (enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

             base[enemyAttackVertical][enemyAttackSide] = "x";

             userRecode.setHantei(1);

             String userId = (String) httpSession.getAttribute("userId");

             int rowNumber = userRecodeService.insert(userRecode, userId);

             i = 0;

             return "lose";

             //引き分け
//        } else if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide() &&
//                enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {
//
//            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";
//            base[enemyAttackVertical][enemyAttackSide] = "x";
//
//            userRecode.setHantei(2);
//
//            String userId = (String)httpSession.getAttribute("userId");
//
//            int rowNumber = userRecodeService.insert(userRecode,userId);
//
//            i = 0;
//
//            return "draw";

         }else if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide()) {

            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";

            userRecode.setHantei(0);

            String userId = (String)httpSession.getAttribute("userId");

            System.out.println(userRecode.getHandling());

            int rowNumber = userRecodeService.insert(userRecode,userId);

            i = 0;

            return "win";


        } else {

            enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "・";
            base[enemyAttackVertical][enemyAttackSide] = "・";

            return "miss";
        }

    }
}
