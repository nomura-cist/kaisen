package com.example.kaisen.position;

import org.springframework.stereotype.Component;

@Component
public class MyPosition {

    private int vertical;
    private int side;

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public int getVertical() {
        return vertical;
    }

    public int getSide() {
        return side;
    }
}
