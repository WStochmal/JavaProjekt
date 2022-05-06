package com.example.bank;

import javax.crypto.AEADBadTagException;

public class Account {
    private String Nr_account;
    private Double Available_money;

    public Account(String Nr_account, String Available_money){
        this.Available_money = Double.valueOf(Available_money);
        this.Nr_account = Nr_account;
    }

    public void getName() {
        System.out.println(Nr_account);
        System.out.println(Available_money);
    }

    public String getAccountNumber(){
        return Nr_account;
    }
}
