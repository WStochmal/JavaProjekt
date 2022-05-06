package com.example.bank;

public class Card {
    private String Card_number;
    private int expire_date_year;
    private int expire_date_month;
    private int CVC;

    public Card(String Card_number, String expire_date_year, String expire_date_month, String CVC){
        this.Card_number = Card_number;
        this.expire_date_year = Integer.parseInt(expire_date_year);
        this.expire_date_month = Integer.parseInt(expire_date_month);
        this.CVC = Integer.parseInt(CVC);
    }

    public void getName() {
        System.out.println(Card_number);
        System.out.println(expire_date_year);
        System.out.println(expire_date_month);
        System.out.println(CVC);
    }
}
