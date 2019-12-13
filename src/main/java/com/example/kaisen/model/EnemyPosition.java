package com.example.kaisen.model;

import org.springframework.stereotype.Component;

@Component
public class EnemyPosition {

    private int enemyVertical;
    private int enemySide;

    public void setEnemySide(int enemySide) {
        this.enemySide = enemySide;
    }

    public void setEnemyVertical(int enemyVertical) {
        this.enemyVertical = enemyVertical;
    }

    public int getEnemySide() {
        return this.enemySide;
    }

    public int getEnemyVertical() {
        return this.enemyVertical;
    }
}
