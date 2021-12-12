package event;

import java.time.Instant;

public class Event {
    private final Instant time;

    public Event(Instant time) {
        this.time = time;
    }

    public Instant getTime() {
        return time;
    }
}
