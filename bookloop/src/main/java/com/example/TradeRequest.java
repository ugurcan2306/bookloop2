package com.example;

import java.util.Objects;

public class TradeRequest {
<<<<<<< HEAD
    private String Sendername;
    private String Receivername;

    // Constructors
    public TradeRequest(String sendername, String receivername) {
        this.Sendername = sendername;
        this.Receivername = receivername;
=======
    private String sendername;
    private String receivername;

    // Constructors
    public TradeRequest(String sendername, String receivername) {
        this.sendername = sendername;
        this.receivername = receivername;
>>>>>>> origin/main
    }

    public TradeRequest() {
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> origin/main
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
<<<<<<< HEAD
        return "Sender: " + Sendername + " Receiver: " + Receivername;
=======
        return "Sender: " + sendername + " Receiver: " + receivername;
>>>>>>> origin/main
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(Sendername, Receivername);
=======
        return Objects.hash(sendername, receivername);
>>>>>>> origin/main
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TradeRequest tr = (TradeRequest) obj;
<<<<<<< HEAD
        return Objects.equals(Sendername, tr.Sendername) && Objects.equals(Receivername, tr.Receivername);
=======
        return Objects.equals(sendername, tr.sendername) && Objects.equals(receivername, tr.receivername);
>>>>>>> origin/main
    }
}
