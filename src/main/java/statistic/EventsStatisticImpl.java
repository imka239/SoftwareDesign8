package statistic;


import bucket.Bucket;
import bucket.BucketImpl;
import clock.Clock;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class EventsStatisticImpl implements EventsStatistic {
    private final Clock clock;
    private final Map<String, Bucket> eventsBucket = new HashMap<>();

    public EventsStatisticImpl(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void incEvent(String name) {
        Instant now = clock.now();
        if (!eventsBucket.containsKey(name)) {
            eventsBucket.put(name, new BucketImpl());
        }
        eventsBucket.get(name).incEvent(now);
    }

    @Override
    public int getEventStatisticByName(String name) {
        Instant now = clock.now();
        AtomicInteger answer = new AtomicInteger();
        Optional.ofNullable(eventsBucket.get(name)).ifPresent(bucket -> answer.addAndGet(bucket.getRpm(now)));
        return answer.get();
    }

    @Override
    public int getAllEventStatistic() {
        Instant now = clock.now();
        AtomicInteger answer = new AtomicInteger();
        eventsBucket.forEach((s, bucket) -> answer.addAndGet(bucket.getRpm(now)));
        return answer.get();
    }

    @Override
    public void printStatistic() {
        eventsBucket.forEach((s, bucket) -> System.out.println("Event " + s + "was happened " + bucket.getAll() + " times"));
    }
}
