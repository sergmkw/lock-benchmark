package test.task;

public class PlayerInnerStateImpl implements Player {

    private volatile InnerState state;

    public PlayerInnerStateImpl(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public void update(long points, long kudos) {
        updateImpl(points, kudos);
    }

    @Override
    public long getPoints() {
        return state.points;
    }

    @Override
    public long getKudos() {
        return state.kudos;
    }

    private void updateImpl(long points, long kudos) {
        state = new InnerState(points, kudos);
    }

    private final static class InnerState {
        public final long points;
        public final long kudos;

        private InnerState(long points, long kudos) {
            this.points = points;
            this.kudos = kudos;
        }
    }
}
