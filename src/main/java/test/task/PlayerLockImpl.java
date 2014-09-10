package test.task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlayerLockImpl implements Player {

    private final Lock lock = new ReentrantLock();

    private long points;

    private long kudos;

    public PlayerLockImpl(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public void update(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public long getPoints() {
        lock.lock();
        try {
            return points;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long getKudos() {
        lock.lock();
        try {
            return kudos;
        } finally {
            lock.unlock();
        }
    }

    private void updateImpl(long points, long kudos) {
        lock.lock();
        try {
            this.points = points;
            this.kudos = kudos;
        } finally {
            lock.unlock();
        }
    }
}
