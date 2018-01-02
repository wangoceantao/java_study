package com.hit.wangoceantao.test;

/**
 * Created by wanghaitao on 2017/1/28.
 */
public enum Season {
    SPRING, SUMMER, AUTUMN, WINTER;

    public static void main(String[] args) {
        Season season = Season.SPRING;
        switch (season) {
            case SPRING:
                System.out.println("It's Spring");
                break;

            case WINTER:
                System.out.println("It's Winter");
                break;

            case SUMMER:
                System.out.println("It's Summer");
                break;
            case AUTUMN:
                System.out.println("It's Autumn");
                break;
        }
    }
}
