package com.example.bank;

public class TransferAdmin {
    public String getNumber() {
        return Number;
    }

    public String getSender() {
        return Sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public String getTitle() {
        return Title;
    }

    public String getAmount() {
        return Amount;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    private String Number;
    private String Sender;
    private String Receiver;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    private String Data;
    private String Amount;
    private String Title;
    public TransferAdmin(String Number, String Sender, String Receiver, String Title, String Amount, String Data){
        this.Amount = Amount;
        this.Number = Number;
        this.Receiver = Receiver;
        this.Sender = Sender;
        this.Title = Title;
        this.Data = Data;
    }
}


