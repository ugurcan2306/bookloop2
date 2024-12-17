package com.example;

import java.util.Objects;

public class TradeRequest {
    private String sendername;
    private String receivername;

    // Constructors
    public TradeRequest(String sendername, String receivername) {
        this.sendername = sendername;
        this.receivername = receivername;
    }

    public TradeRequest() {
    }

  public String getReceivername() {
      return receivername;
  }
  public String getSendername() {
      return sendername;
  }
  public void setReceivername(String receivername) {
      this.receivername = receivername;
  }
  public void setSendername(String sendername) {
      this.sendername = sendername;
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
        return "Sender: " + sendername + " Receiver: " + receivername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendername, receivername);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TradeRequest tr = (TradeRequest) obj;
        return Objects.equals(sendername, tr.sendername) && Objects.equals(receivername, tr.receivername);
    }
}
