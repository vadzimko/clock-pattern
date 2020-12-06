package clock;

public interface Clock {
    int MINUTES_IN_HOUR = 60;
    int SECONDS_IN_HOUR = 3600;

    int current();
}
