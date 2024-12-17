package com.bookloop.firestore;

import java.time.LocalDate;

import com.example.User;
import com.google.cloud.Timestamp;

public class Comment {
    String commentMade;
    User ownerofTheComment;
    Timestamp timeStamp;

    public Comment(String commentMade,User owner,Timestamp timeStamp){
        this.commentMade= commentMade;
        this.ownerofTheComment= owner;
        this.timeStamp= timeStamp;
    }
    Comment(){

    }
    @Override
    public String toString() {
        return ownerofTheComment.toString() +": "+ commentMade;
    }
    public String getCommentMade() {
        return commentMade;
    }
    public void setCommentMade(String commentMade) {
        this.commentMade = commentMade;
    }
    public User getOwnerofTheComment() {
        return ownerofTheComment;
    }
    public void setOwnerofTheComment(User ownerofTheComment) {
        this.ownerofTheComment = ownerofTheComment;
    }
    
    public Timestamp getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
    
   
}
