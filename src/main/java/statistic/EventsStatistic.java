package statistic;

public interface EventsStatistic {
    void incEvent(String name);
    int getEventStatisticByName(String name);
    int getAllEventStatistic();
    void printStatistic();
}