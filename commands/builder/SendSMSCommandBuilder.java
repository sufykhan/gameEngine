package commands.builder;

import commands.implementation.SendSMSCommand;


public class SendSMSCommandBuilder extends SendBuilder{
    String templateId;
    String template;

    public SendSMSCommandBuilder templateId(String templateId){
        this.templateId = templateId;
        return this;
    }
    public SendSMSCommandBuilder template(String template){
        this.template = template;
        return this;
    }
    public SendSMSCommand build(){
        return new SendSMSCommand(user,message);
    }
}
