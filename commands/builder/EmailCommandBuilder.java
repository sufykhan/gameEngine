package commands.builder;


import commands.implementation.EmailCommand;
import game.User;

public class EmailCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;
    String templateId;
    String tempalate;


    public EmailCommandBuilder link(String link){
        this.link = link;
        return this;
    }
    public EmailCommandBuilder templateId(String templateId){
        this.templateId = templateId;
        return this;
    }
    public EmailCommandBuilder user(User user){
        this.notificationBuilder.user(user);
        return this;
    }
    public EmailCommandBuilder message(String message){
        this.notificationBuilder.message(message);
        return this;
    }
    public EmailCommandBuilder template(String template){
        this.tempalate = template;
        return this;
    }
    public EmailCommand build(){
        return new EmailCommand(notificationBuilder.build());
    }
}
