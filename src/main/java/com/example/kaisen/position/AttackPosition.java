package com.example.kaisen.position;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
public class AttackPosition {

    @Min(0)
    @Max(4)
    private int attackVertical;
    @Min(0)
    @Max(4)
    private int attackSide;

    public void setAttackVertical(int attackVertical) {
        this.attackVertical = attackVertical;
    }

    public void setAttackSide(int attackSide) {
        this.attackSide = attackSide;
    }

    public int getAttackVertical() {
        return attackVertical;
    }

    public int getAttackSide() {
        return attackSide;
    }
}
