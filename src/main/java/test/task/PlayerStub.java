package test.task;

@Deprecated
public class PlayerStub implements Player {

    private long points;

    private long kudos;

    public PlayerStub(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public void update(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public long getPoints() {
        return points;
    }

    @Override
    public long getKudos() {
        return kudos;
    }

    private void updateImpl(long points, long kudos) {
        this.points = points;
        this.kudos = kudos;
    }
}
