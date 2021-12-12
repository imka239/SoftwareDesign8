import clock.TestClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import statistic.EventsStatistic;
import statistic.EventsStatisticImpl;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClockTest {
    private TestClock clock;
    private Instant now;
    private EventsStatistic eventsStatistic;

    private void addSeconds(int sec) {
        now = now.plusSeconds(sec);
        clock.setNow(now);
    }

    private void subtractSeconds(int sec) {
        now = now.minusSeconds(sec);
        clock.setNow(now);
    }

    @BeforeEach
    public void setUp() {
        now = Instant.EPOCH;
        clock = new TestClock(now);
        eventsStatistic = new EventsStatisticImpl(clock);
    }

    @Test
    public void oneEvent() {
        eventsStatistic.incEvent("event1");
        assertEquals(1, eventsStatistic.getEventStatisticByName("event1"));
    }

    @Test
    public void manyEventsInHour() {
        for (int i = 0; i < 3600; i++) {
            eventsStatistic.incEvent("event");
            addSeconds(1);
        }
        assertEquals(3600, eventsStatistic.getEventStatisticByName("event"));
        assertEquals(0, eventsStatistic.getEventStatisticByName("event1"));
        subtractSeconds(3600);
        assertEquals(3600, eventsStatistic.getEventStatisticByName("event"));
    }

    @Test
    public void anotherManyEventsInHour() {
        for (int i = 0; i < 3600; i++) {
            eventsStatistic.incEvent("event");
            addSeconds(1);
        }
        assertEquals(3600, eventsStatistic.getEventStatisticByName("event"));
        addSeconds(3600);
        assertEquals(0, eventsStatistic.getEventStatisticByName("event"));
    }

    @Test
    public void manyDifferentEventsInHour() {
        for (int i = 0; i < 3600; i++) {
            eventsStatistic.incEvent("event" + i);
            addSeconds(1);
        }
        assertEquals(1, eventsStatistic.getEventStatisticByName("event1"));
        assertEquals(3600, eventsStatistic.getAllEventStatistic());
    }

    @Test
    public void strangeTime() {
        for (int i = 0; i < 3; i++) {
            eventsStatistic.incEvent("event");
            addSeconds(60 * 50);
        }
        assertEquals(1, eventsStatistic.getEventStatisticByName("event"));
        subtractSeconds(60 * 50);
        assertEquals(1, eventsStatistic.getEventStatisticByName("event"));
    }
}
