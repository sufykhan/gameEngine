package Service;

import commands.implementation.SendEmailCommand;
import commands.implementation.SendSMSCommand;
import game.User;

public class SMSService {
    private void sendSMS(User user, String message){
        System.out.println("sendEmail has been envoked");
    }
    public void execute(SendSMSCommand command){
        sendSMS(command.getReciever(),command.getMessage());
    }
}