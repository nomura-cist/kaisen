package com.example.kaisen.controller;

import com.example.kaisen.position.AttackPosition;
import com.example.kaisen.position.EnemyPosition;
import com.example.kaisen.position.MyPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
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

    @GetMapping("/positionSetting")
    public String getPosition(Model model, @ModelAttribute @Validated MyPosition myPosition) {

        model.addAttribute("enemyBase", enemyBase = new String[5][5]);
        model.addAttribute("base", base = new String[5][5]);


        return "positionSetting";
    }

    @PostMapping("/positionSetting")
    public String postPosition(Model model, @ModelAttribute @Validated MyPosition myPosition, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "positionSetting";
        }

        //自分の位置設定
        base[myPosition.getVertical()][myPosition.getSide()] = "W";
        model.addAttribute("base",base);

        //敵の戦艦の位置を乱数で決定
        Random random = new Random();
        model.addAttribute("enemyBase",enemyBase);
        enemyPosition.setEnemyVertical(random.nextInt(5));
        enemyPosition.setEnemySide(random.nextInt(5));
//        enemyPosition.setEnemyVertical(0);
//        enemyPosition.setEnemySide(0);

        model.addAttribute("attackPosition", new AttackPosition());

        return "positionConfirmation";
    }

    @GetMapping("/fight")
    public String getFight(@ModelAttribute @Validated AttackPosition attackPosition) {
        return "positionConfirmation";
    }

    @PostMapping("/fight")
    public String fight(Model model, @ModelAttribute @Validated AttackPosition attackPosition, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("enemyBase", enemyBase);
            model.addAttribute("base", base);

            return "mistake";
        }

            //敵の攻撃位置を乱数で決定
            Random random = new Random();
            int enemyAttackVertical = random.nextInt(5);
            int enemyAttackSide = random.nextInt(5);

            //勝ち
            if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide()) {

                enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";
                model.addAttribute("enemyBase", enemyBase);
                model.addAttribute("base", base);

                return "win";

                //負け
            } else if (enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

                base[enemyAttackVertical][enemyAttackSide] = "x";
                model.addAttribute("enemyBase", enemyBase);
                model.addAttribute("base", base);

                return "lose";

                //引き分け
            } else if (attackPosition.getAttackVertical() == enemyPosition.getEnemyVertical() && attackPosition.getAttackSide() == enemyPosition.getEnemySide() &&
                    enemyAttackVertical == myPosition.getVertical() && enemyAttackSide == myPosition.getSide()) {

                enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "×";
                model.addAttribute("enemyBase", enemyBase);

                base[enemyAttackVertical][enemyAttackSide] = "x";
                model.addAttribute("base", base);

                return "draw";

            } else {

                enemyBase[attackPosition.getAttackVertical()][attackPosition.getAttackSide()] = "・";
                model.addAttribute("enemyBase", enemyBase);

                base[enemyAttackVertical][enemyAttackSide] = "・";
                model.addAttribute("base", base);

                return "miss";
            }

    }

}
