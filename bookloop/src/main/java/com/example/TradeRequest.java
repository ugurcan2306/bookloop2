package com.example;

import java.util.Objects;

public class TradeRequest {
    private String Sendername;
    private String Receivername;

    // Constructors
    public TradeRequest(String sendername, String receivername) {
        this.Sendername = sendername;
        this.Receivername = receivername;
    }

    public TradeRequest() {
    }

    // Getters and Setters
   public String getReceivername() {
       return Receivername;
   }
   public String getSendername() {
       return Sendername;
   }
   public void setReceivername(String receivername) {
       Receivername = receivername;
   }
   public void setSendername(String sendername) {
       Sendername = sendername;
   }
    // Methods
    public void acceptTrade() {
        // Implement logic to accept the trade request
    }

    public void declineTrade() {
        // Implement logic to decline the trade request
    }

    public void cancelTrade() {
        // Implement logic to cancel the trade request
    }

    // Override Methods
    @Override
    public String toString() {
        return "Sender: " + Sendername + " Receiver: " + Receivername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Sendername, Receivername);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TradeRequest tr = (TradeRequest) obj;
        return Objects.equals(Sendername, tr.Sendername) && Objects.equals(Receivername, tr.Receivername);
    }
}
