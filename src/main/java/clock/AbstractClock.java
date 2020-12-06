package clock;

import java.sql.Timestamp;

public class AbstractClock implements Clock {
    protected int currentValue;

    public AbstractClock() {
        this.currentValue = 0;
    }

    /**
     * @param forceRefresh - чтобы без необходимости не обновлять текущее время.
     *   Может пригодиться, когда бизнес-логика требует, чтобы для длительных
     *   операций каждый вызов возвращал одно и то же значение (например
     *    время запуска программы/первого вызова)
     * @return int
     */
    public int current(boolean forceRefresh) {
        if (currentValue == 0 || forceRefresh) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return (int) (timestamp.getTime() / 1000);
        }

        return currentValue;
    }

    public int current() {
        return current(false);
    }
}
