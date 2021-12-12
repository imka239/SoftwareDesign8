package clock;

import java.time.Instant;

public class TestClock implements Clock {
    private Instant now;

    public TestClock(Instant now) {
        this.now = now;
    }

    public void setNow(Instant now) {
        this.now = now;
    }

    @Override
    public Instant now() {
        return now;
    }
}
