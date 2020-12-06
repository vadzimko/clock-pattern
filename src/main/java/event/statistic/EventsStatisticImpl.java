package event.statistic;

import clock.Clock;

import java.util.HashMap;
import java.util.Map;

public class EventsStatisticImpl implements EventsStatistic {
    HashMap<String, EventCounter> events;
    Clock clock;

    public EventsStatisticImpl(Clock clock) {
        this.clock = clock;
        this.events = new HashMap<>();
    }

    public void incEvent(String name) {
        EventCounter eventCounter = events.computeIfAbsent(name, k -> new EventCounter(clock));
        eventCounter.addEvent();
    }

    public double getEventStatisticByName(String name) {
        EventCounter eventCounter = events.getOrDefault(name, null);
        if (eventCounter == null) {
            return 0;
        }

        return getEventsRpm(eventCounter.getLastHourCount());
    }

    public double getAllEventStatistic() {
        int eventsCount = 0;
        for (EventCounter eventCounter : events.values()) {
            eventsCount += eventCounter.getLastHourCount();
        }

        return getEventsRpm(eventsCount);
    }

    public void printStatistic() {
        for (Map.Entry<String, EventCounter> entry : events.entrySet()) {
            String name = entry.getKey();
            EventCounter eventCounter = entry.getValue();
            System.out.println(name + ": " + getEventsRpm(eventCounter.getLastHourCount()) + " rpm");
        }
    }

    private double getEventsRpm(int count) {
        return (double) count / clock.MINUTES_IN_HOUR;
    }
}
