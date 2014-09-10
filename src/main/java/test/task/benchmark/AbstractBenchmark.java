package test.task.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import test.task.Player;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5)
@Threads(10)
@Fork(1)
public abstract class AbstractBenchmark {

    protected Player player;

    public abstract void setup();


    @Benchmark
    public long randomlyReadWrite() {
        long time = System.nanoTime();
        if (time % 2 == 0) {
            return player.getPoints();
        } else {
            if (time % 9 == 0) {
                player.update(time, time + 1);
                return 0l;
            } else {
                return player.getKudos();
            }
        }
    }

    @Benchmark
    public long systematicallyReadWrite() {
        long time = System.nanoTime();
        long kudos = player.getKudos();
        long points = player.getPoints();
        player.update(points + time, kudos + points);
        return player.getKudos();
    }

    @Benchmark
    public long readOnly() {
        long time = System.nanoTime();
        if (time % 2 == 0) {
            return player.getKudos();
        } else {
            return player.getPoints();
        }
    }

    @Benchmark
    public void writeOnly() {
        long time = System.nanoTime();
        player.update(time + 1, time - 1);
    }
}
