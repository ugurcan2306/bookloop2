<<<<<<< HEAD
package com.example;
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
=======
package com.example;
import java.util.Objects;

import com.google.cloud.Timestamp;

public class Comment {
    String commentMade;
    String usernameOfTheOwner;
    Timestamp timeStamp;
    String bookname;

    public Comment(String commentMade,String owner,Timestamp timeStamp,String bookname){
        this.commentMade= commentMade;
        this.usernameOfTheOwner= owner;
        this.timeStamp= timeStamp;
        this.bookname= bookname;
    }
    Comment(){

    }
    @Override
    public int hashCode() {
        return Objects.hash(commentMade, usernameOfTheOwner);
    }
    @Override
    public boolean equals(Object obj) {
        Comment c= (Comment) obj;
        return c.commentMade.equals(commentMade)&&c.usernameOfTheOwner.equals(usernameOfTheOwner);
    }
    @Override
    public String toString() {
        return usernameOfTheOwner +": "+ commentMade;
    }
    public String getCommentMade() {
        return commentMade;
    }
    public void setCommentMade(String commentMade) {
        this.commentMade = commentMade;
    }
    public String getUsernameOfTheOwner() {
        return usernameOfTheOwner;
    }
    public void setOwnerofTheComment(String ownerofTheComment) {
        this.usernameOfTheOwner = ownerofTheComment;
    }
    
    public Timestamp getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public void setUsernameOfTheOwner(String usernameOfTheOwner) {
        this.usernameOfTheOwner = usernameOfTheOwner;
    }
     
}
>>>>>>> origin/main
