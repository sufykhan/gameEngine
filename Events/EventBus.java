package Events;

import java.util.List;

public class EventBus {
    List<Event> events;
    List<Subscriber> subscriber;

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(Event event) {
        this.events.add(event);
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber.add(subscriber);
    }

}
