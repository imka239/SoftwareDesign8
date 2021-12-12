package bucket;

import java.time.Instant;

public interface Bucket {
    void incEvent(Instant now);
    int getRpm(Instant now);
    int getAll();
}
