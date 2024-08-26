package com.example.myproject2022_05_06;

public class food {
    private static String store;
    private static String saram;
    private static String hour;
    private static String minute;
    private static String money;
    private static Integer delivery;

    public food() { } // 생성자 메서드

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        food.delivery = delivery;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getSaram() {
        return saram;
    }

    public void setSaram(String saram) {
        this.saram = saram;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    //값을 추가할때 쓰는 함수
    public food(String store, String saram, String hour, String minute, String money, Integer delivery){
        this.store = store;
        this.saram = saram;
        this.hour = hour;
        this.minute = minute;
        this.money = money;
        this.delivery = delivery;
    }
}
