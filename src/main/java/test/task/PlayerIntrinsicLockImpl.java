package test.task;

public class PlayerIntrinsicLockImpl implements Player {

    private long points;

    private long kudos;

    public PlayerIntrinsicLockImpl(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public void update(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public synchronized long getPoints() {
        return points;
    }

    @Override
    public synchronized long getKudos() {
        return kudos;
    }

    private synchronized void updateImpl(long points, long kudos) {
        this.points = points;
        this.kudos = kudos;
    }
}
