package com.rjp.eaction.bean;

/**
 * Created by small on 2018/7/24.
 */

public class MatchDetailPlayerModel {

    /**
     * positionY : CL
     * number : 2
     * name : 佩特森
     * position : 后卫
     * substitute : false
     * positionX : D1
     */

    private String positionY;
    private String number;
    private String name;
    private String position;
    private boolean substitute;
    private String positionX;

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isSubstitute() {
        return substitute;
    }

    public void setSubstitute(boolean substitute) {
        this.substitute = substitute;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }
}
