import clock.NormalClock;
import statistic.EventsStatistic;
import statistic.EventsStatisticImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        EventsStatistic statistics = new EventsStatisticImpl(new NormalClock());
        while (true) {
            String name = in.next();
            if ("getAll".equals(name)) {
                System.out.println(statistics.getAllEventStatistic());
            } else if ("printAll".equals(name)) {
                statistics.printStatistic();
            } else if (name.startsWith("statisticByName")) {
                String realName = in.next();
                System.out.println(statistics.getEventStatisticByName(realName));
            } else if ("exit".equals(name)) {
                break;
            } else {
                statistics.incEvent(name);
            }
        }
    }
}
