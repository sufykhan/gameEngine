package commands.builder;

import commands.implementation.SMSCommand;
import game.User;


public class SMSCommandBuilder {
    NotificationBuilder notificationBuilder;
    String templateId;
    String template;

    public SMSCommandBuilder templateId(String templateId){
        this.templateId = templateId;
        return this;
    }
    public SMSCommandBuilder template(String template){
        this.template = template;
        return this;
    }
    public SMSCommandBuilder user(User user){
        this.notificationBuilder.user(user);
        return this;
    }
    public SMSCommandBuilder message(String message){
        this.notificationBuilder.message(message);
        return this;
    }
    public SMSCommand build(){
        return new SMSCommand(notificationBuilder.build());
    }

}
