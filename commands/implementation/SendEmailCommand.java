package commands.implementation;

import game.User;

public class SendEmailCommand extends Send{
    String link;
    String templateId;
    String template;

    public SendEmailCommand(User user,String message){
        super(user,message);
    }
}
