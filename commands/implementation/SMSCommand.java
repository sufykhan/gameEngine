package commands.implementation;

import game.User;

public class SMSCommand {
    NotificationDetails notificationDetails;
    String templateId;
    String template;

    public SMSCommand(NotificationDetails notificationDetails){
        notificationDetails = new NotificationDetails(notificationDetails.getReciever(),notificationDetails.getMessage());
    }

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }
}
