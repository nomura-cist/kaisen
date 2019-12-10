package com.example.kaisen.controller;

import com.example.kaisen.position.AttackPosition;
import com.example.kaisen.position.MyPosition;
import com.example.kaisen.service.PositionSettingService;
import com.example.kaisen.service.VictoryOrDefeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PositionController {

    //自分の陣地の配列
    String[][] base = new String[5][5];

    //敵の陣地の配列
    String[][] enemyBase = new String[5][5];

    @Autowired
    private VictoryOrDefeatService victoryOrDefeatService;

    @Autowired
    private PositionSettingService positionSettingService;

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
        positionSettingService.myPositionSetting(myPosition,base);
        model.addAttribute("base",base);

        //敵の戦艦の位置を乱数で決定
        model.addAttribute("enemyBase",enemyBase);
        positionSettingService.enemyPositionSetting();

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

        String hantei = victoryOrDefeatService.shohai(attackPosition,base,enemyBase);
        model.addAttribute("enemyBase", enemyBase);
        model.addAttribute("base", base);

        return hantei;

    }

}
