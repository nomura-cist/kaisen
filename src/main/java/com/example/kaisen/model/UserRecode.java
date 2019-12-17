package com.example.kaisen.model;

import org.springframework.stereotype.Component;

@Component
public class UserRecode {

    private int handling;
    private int hantei;
    private int playTime;
    private String hanteiCh;

    public UserRecode() {


    }

    public int getHandling() {
        return handling;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }

    public int getHantei() {
        return hantei;
    }

    public void setHantei(int hantei) {

        this.hantei = hantei;

        if (hantei==0) {
            this.hanteiCh="勝ち";
        }else if (hantei==1) {
            this.hanteiCh="負け";
        }else {
            this.hanteiCh="引き分け";
        }
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
}
