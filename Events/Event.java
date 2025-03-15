package Events;


import game.User;

public abstract class Event {

    private User user;
    private String message;

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
