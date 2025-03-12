package Service;

import commands.implementation.EmailCommand;
import game.User;

public class EmailService {
    private void sendEmail(User user, String message){
        System.out.println("sendEmail has been envoked");
    }
    public void execute(EmailCommand command){
        sendEmail(command.getNotificationDetails().getReciever(),command.getNotificationDetails().getMessage());
    }
}
