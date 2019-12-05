package com.example.kaisen.position;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
public class MyPosition {

    @Min(0)
    @Max(4)
    private int vertical;
    @Min(0)
    @Max(4)
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
