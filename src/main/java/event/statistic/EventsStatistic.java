package event.statistic;

public interface EventsStatistic {
    void incEvent(String name);

    double getEventStatisticByName(String name);

    double getAllEventStatistic();

    void printStatistic();
}
