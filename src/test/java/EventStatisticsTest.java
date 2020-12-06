import clock.Clock;
import clock.NormalClock;
import clock.SetableClock;
import event.statistic.EventsStatistic;
import event.statistic.EventsStatisticImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EventStatisticsTest {

    @Test
    void testSimple() {
        Clock clock = new NormalClock();
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        HashMap<String, Integer> eventsCounts = new HashMap<>();
        eventsCounts.put("a", 1);
        eventsCounts.put("b", 7);
        eventsCounts.put("c", 2);

        for (Map.Entry<String, Integer> entry : eventsCounts.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                eventsStatistic.incEvent(entry.getKey());
            }
        }

        int totalSum = 0;
        for (Map.Entry<String, Integer> entry : eventsCounts.entrySet()) {
            totalSum += entry.getValue();
            double expected = (double) entry.getValue() / clock.MINUTES_IN_HOUR;
            double actual = eventsStatistic.getEventStatisticByName(entry.getKey());
            Assertions.assertEquals(expected, actual);
        }

        double expectedTotalRpm = (double) totalSum / clock.MINUTES_IN_HOUR;
        Assertions.assertEquals(expectedTotalRpm, eventsStatistic.getAllEventStatistic());
    }

    @Test
    void testSetableTime() {
        int time = 3600;
        SetableClock clock = new SetableClock(time);
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);

        // simulate 10 hours
        for (int i = 0; i <= 3600 * 11; i++) {
            clock.setTime(time);

            // twice every second
            eventsStatistic.incEvent("a");
            eventsStatistic.incEvent("a");

            // every 3 seconds
            if (i % 3 == 0) {
                eventsStatistic.incEvent("b");
            }

            // every minute
            if (i % 60 == 0) {
                eventsStatistic.incEvent("c");
            }

            time++;
        }

        double aCount = 2. * 60;
        double bCount = 60. / 3;
        double cCount = 1;

        Assertions.assertEquals(aCount, eventsStatistic.getEventStatisticByName("a"));
        Assertions.assertEquals(bCount, eventsStatistic.getEventStatisticByName("b"));
        Assertions.assertEquals(cCount, eventsStatistic.getEventStatisticByName("c"));
        Assertions.assertEquals(aCount + bCount + cCount, eventsStatistic.getAllEventStatistic());
    }
}
