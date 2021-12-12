package bucket;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import event.Event;

public class BucketImpl implements Bucket {
    private final Deque<Event> bucket = new ArrayDeque<>();
    private int happenedBefore = 0;

    private void removeOld(Instant now) {
        while (!bucket.isEmpty() && bucket.getFirst().getTime().isBefore(now.minusSeconds(3600))) {
            bucket.pollFirst();
            happenedBefore += 1;
        }
    }

    @Override
    public void incEvent(Instant now) {
        removeOld(now);
        bucket.addLast(new Event(now));
    }

    @Override
    public int getRpm(Instant now) {
        removeOld(now);
        return bucket.size();
    }

    @Override
    public int getAll() {
        return happenedBefore + bucket.size();
    }
}
