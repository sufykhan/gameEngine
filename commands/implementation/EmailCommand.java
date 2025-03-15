package commands.implementation;

import game.User;

public class EmailCommand {
    NotificationDetails notificationDetails;
    String link;
    String templateId;
    String template;

    public EmailCommand(NotificationDetails notificationDetails){
       notificationDetails = new NotificationDetails(notificationDetails.getReciever(),notificationDetails.getMessage());
    }

    public NotificationDetails getNotificationDetails() {
        return notificationDetails;
    }
}
