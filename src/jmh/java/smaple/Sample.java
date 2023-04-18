package smaple;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Sample {

    private List<Integer> list;

    @Setup
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public void ArrayListBenchmark(Blackhole blackhole) {
        blackhole.consume(ArrayListMethod(list));
    }

    @Benchmark
    public void StreamBenchmark(Blackhole blackhole) {
        blackhole.consume(StreamMethod(list));
    }

    private List<Integer> ArrayListMethod(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer val: list) {
            if (val % 5 == 0) {
                result.add(val);
            }
        }

        return result;
    }

    private List<Integer> StreamMethod(List<Integer> list) {
        return list.stream().filter(val -> val % 5 == 0).toList();
    }
}
