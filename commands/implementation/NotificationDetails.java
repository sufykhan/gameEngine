package commands.implementation;

import game.User;

public class NotificationDetails {

    User reciever;
    String message;

    public User getReciever() {
        return reciever;
    }

    public String getMessage() {
        return message;
    }

    public NotificationDetails(User reciever, String message){
        this.reciever = reciever;
        this.message = message;
    }
}
