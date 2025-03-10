package commands.builder;

import commands.implementation.Send;
import commands.implementation.SendEmailCommand;
import game.User;

public class SendEmailCommandBuilder extends SendBuilder{
    String link;
    String templateId;
    String tempalate;


    public SendEmailCommandBuilder link(String link){
        this.link = link;
        return this;
    }
    public SendEmailCommandBuilder templateId(String templateId){
        this.templateId = templateId;
        return this;
    }
    public SendEmailCommandBuilder template(String template){
        this.tempalate = template;
        return this;
    }
}
