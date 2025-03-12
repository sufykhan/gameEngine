package Service;

import commands.implementation.SMSCommand;
import game.User;

public class SMSService {
    private void sendSMS(User user, String message){
        System.out.println("sendEmail has been envoked");
    }
    public void execute(SMSCommand command){
        sendSMS(command.getNotificationDetails().getReciever(),command.getNotificationDetails().getMessage());
    }
}