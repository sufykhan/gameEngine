package commands.implementation;

import game.User;

public class SendSMSCommand extends Send{
    String templateId;
    String template;

    public SendSMSCommand(User user,String message){
       super(user,message);
    }
}
