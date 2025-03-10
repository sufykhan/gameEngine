package commands.implementation;

import game.User;

public class Send {

    User reciever;
    String message;

    public User getReciever() {
        return reciever;
    }

    public String getMessage() {
        return message;
    }

    public Send(User reciever, String message){
        this.reciever = reciever;
        this.message = message;
    }
}
