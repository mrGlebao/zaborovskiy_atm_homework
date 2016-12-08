package managers;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by zabor on 08.12.2016.
 */
public class LockManager {
    private int delay;
    private TimeUnit unit;
    private ScheduledExecutorService executor;
    private ScheduledFuture timer;
    private final int MAX_BAD_ATTEMPTS = 3;
    private int badAttempts;

    public LockManager(int time, TimeUnit unit) {
        this.delay = time;
        this.unit = unit;
        this.executor = Executors.newScheduledThreadPool(1);
    }
    public LockManager(int time) {
        this.delay = time;
        this.unit = TimeUnit.SECONDS;
        this.executor = Executors.newScheduledThreadPool(1);
    }

    private void scheduleTask() {
        if (timer == null) {
            timer = executor.schedule(new SetAcessibleTask(), delay, unit);
        }
    }

    public void incrementBadAttempts() {
        this.badAttempts += 1;
    }

    public int getBadAttempts() {
        return badAttempts;
    }

    private class SetAcessibleTask extends TimerTask {
        @Override
        public void run() {
            clear();
        }
    }

    public boolean isLocked() {
        if (badAttempts == MAX_BAD_ATTEMPTS-1) {
            scheduleTask();
            return true;
        }
        return false;
    }

    public void clear(){
        badAttempts = 0;
        timer = null;
    }

    public long getTimeLeft() {
        return timer.getDelay(TimeUnit.MILLISECONDS);
    }
}
