package event.statistic;

import clock.Clock;

import java.util.ArrayDeque;
import java.util.Deque;

public class EventCounter {

    Clock clock;
    Deque<Integer> events;

    public EventCounter(Clock clock) {
        this.clock = clock;
        this.events = new ArrayDeque<>();
    }

    public void addEvent() {
        int time = clock.current();
        events.add(time);
    }

    public int getLastHourCount() {
        clearExpired();
        return events.size();
    }

    private void clearExpired() {
        int timeBorder = clock.current() - clock.SECONDS_IN_HOUR;
        while (!events.isEmpty() && events.getFirst() <= timeBorder) {
            events.removeFirst();
        }
    }

}
