import clock.NormalClock;
import event.statistic.EventsStatistic;
import event.statistic.EventsStatisticImpl;

public class Main {
    public static void main(String[] args) {
        EventsStatistic eventsStatistic = new EventsStatisticImpl(new NormalClock());

        eventsStatistic.incEvent("hello");
        eventsStatistic.incEvent("hell2");
        eventsStatistic.incEvent("hell2");
        eventsStatistic.incEvent("hell2");
        eventsStatistic.incEvent("hello1");
        eventsStatistic.incEvent("hello1");
        eventsStatistic.incEvent("hello1");
        eventsStatistic.incEvent("hello1");
        eventsStatistic.incEvent("hello1");
        System.out.println(eventsStatistic.getAllEventStatistic());
        System.out.println(eventsStatistic.getEventStatisticByName("hello"));
        eventsStatistic.printStatistic();

    }
}
