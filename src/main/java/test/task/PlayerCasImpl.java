package test.task;

import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerCasImpl implements Player {

    private volatile long points;

    private volatile long kudos;

    private final AtomicBoolean busy = new AtomicBoolean(false);


    public PlayerCasImpl(long points, long kudos) {
        this.points = points;
        this.kudos = kudos;
    }

    @Override
    public void update(long points, long kudos) {
        while (true) {
            if (busy.compareAndSet(false, true)) {
                this.points = points;
                this.kudos = kudos;
                busy.set(false);
                break;
            }
        }
    }

    @Override
    public long getPoints() {
        return points;
    }

    @Override
    public long getKudos() {
        return kudos;
    }


}
