package com.example;

import java.util.Objects;

public class TradeRequest {
    User Sender;
    User Receiver;// sent trade request dediğinde algılıyacağız kimmiş
    public TradeRequest(User sender,User receiver){
        this.Sender=sender;
        this.Receiver=receiver;
    }
    public TradeRequest(){

    }
    public User getReceiver() {
        return Receiver;
    }
    public User getSender() {
        return Sender;
    }
    public void setReceiver(User receiver) {
        Receiver = receiver;
    }
    public void setSender(User sender) {
        Sender = sender;
    }
    public void acceptTrade(){
        
    }
    public void declineTrade(){

    }
    public void cancelTrade(){

    }
    @Override
    public String toString() {
        return "Sender: "+Sender+ " Receiver: "+ Receiver;
    }
    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true; // Same object check
    if (obj == null || getClass() != obj.getClass()) return false; // Null or different class check
    
    TradeRequest user = (TradeRequest) obj; // Cast the object to User
    return Sender.equals(user.Sender) && // Username comparison
           Receiver.equals(user.Receiver);
    }
    @Override
    public int hashCode() {
        return Objects.hash(Sender,Receiver); 
    }
}
