package clock;

public class SetableClock extends AbstractClock {

    public SetableClock() {
        super();
    }

    public SetableClock(int now) {
        this.currentValue = now;
    }

    public void setTime(int ts) {
        this.currentValue = ts;
    }
}
