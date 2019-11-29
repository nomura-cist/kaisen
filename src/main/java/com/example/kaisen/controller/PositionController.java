package com.example.kaisen.controller;

import com.example.kaisen.position.EnemyPosition;
import com.example.kaisen.position.MyPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller
public class PositionController {

    //自分の陣地の配列
    String[][] base = new String[5][5];

    //敵の陣地の配列
    String[][] enemyBase = new String[5][5];

    @Autowired
    private EnemyPosition enemyPosition;

    @Autowired
    private MyPosition myPosition;

    @GetMapping("positionSetting")
    public String getPosition(Model model) {return "positionSetting";}

    @PostMapping("positionSetting")
    public String postPosition(int vertical,int side,Model model) {

        //自分の位置設定
        base[vertical][side] = "W";
        model.addAttribute("base",base);
        myPosition.setVertical(vertical);
        myPosition.setSide(side);

        //敵の戦艦の位置を乱数で決定
        Random random = new Random();
        model.addAttribute("enemyBase",enemyBase);
//        enemyPosition.setEnemyVertical(random.nextInt(5));
//        enemyPosition.setEnemySide(random.nextInt(5));
        enemyPosition.setEnemyVertical(0);
        enemyPosition.setEnemySide(0);

        return "positionConfirmation";
    }


    @PostMapping("fight")
    public String fight(int attackVertical, int attackSide, Model model) {

        //敵の攻撃位置を乱数で決定
        Random random = new Random();
        int enemyAttackVertical = random.nextInt(5);
        int enemyAttackSide = random.nextInt(5);

        //勝ち
        if (attackVertical == enemyPosition.getEnemyVertical() && attackSide == enemyPosition.getEnemySide()) {

            enemyBase[attackVertical][attackSide] = "×";
            model.addAttribute("enemyBase", enemyBase);
            model.addAttribute("base",base);

            return "win";

        //負け
        } else if (enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

            base[enemyAttackVertical][enemyAttackSide] = "x";
            model.addAttribute("enemyBase", enemyBase);
            model.addAttribute("base",base);

            return "lose";

        //引き分け
        } else if (attackVertical == enemyPosition.getEnemyVertical() && attackSide == enemyPosition.getEnemySide() &&
                enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

            enemyBase[attackVertical][attackSide] = "×";
            model.addAttribute("enemyBase", enemyBase);

            base[enemyAttackVertical][enemyAttackSide] = "x";
            model.addAttribute("base",base);

            return "draw";

        } else {

            enemyBase[attackVertical][attackSide] = "・";
            model.addAttribute("enemyBase", enemyBase);

            base[enemyAttackVertical][enemyAttackSide] = "・";
            model.addAttribute("base",base);

            return "miss";
        }

    }

}
