package test.task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PlayerRWLockImpl implements Player {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private long points;

    private long kudos;

    public PlayerRWLockImpl(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public void update(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public long getPoints() {
        readLock.lock();
        try {
            return points;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public long getKudos() {
        readLock.lock();
        try {
            return kudos;
        } finally {
            readLock.unlock();
        }
    }

    private void updateImpl(long points, long kudos) {
        writeLock.lock();
        try {
            this.points = points;
            this.kudos = kudos;
        } finally {
            writeLock.unlock();
        }
    }
}
