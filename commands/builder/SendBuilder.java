package commands.builder;

import commands.implementation.Send;
import commands.implementation.SendEmailCommand;
import game.User;

public class SendBuilder {

    User user;
    String message;

    public SendBuilder user(User user){
        this.user = user;
        return this;
    }
    public SendBuilder message(String message){
        this.message = message;
        return this;
    }

    public Send build(){
        return new Send(user,message);
    }
}
