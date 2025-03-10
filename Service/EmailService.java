package Service;

import commands.implementation.SendEmailCommand;
import game.User;

public class EmailService {
    private void sendEmail(User user, String message){
        System.out.println("sendEmail has been envoked");
    }
    public void execute(SendEmailCommand command){
        sendEmail(command.getReciever(),command.getMessage());
    }
}
