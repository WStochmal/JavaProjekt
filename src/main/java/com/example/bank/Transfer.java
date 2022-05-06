package com.example.bank;

public class Transfer {
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

    private String Number,Sender,Receiver,Title,Amount;
    public Transfer(String Number,String Sender,String Receiver,String Title, String Amount){
        this.Amount = Amount;
        this.Number = Number;
        this.Receiver = Receiver;
        this.Sender = Sender;
        this.Title = Title;
    }
}


