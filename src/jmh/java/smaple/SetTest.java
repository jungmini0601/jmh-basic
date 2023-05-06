package smaple;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SetTest {
    int LOOP_COUNT = 10000;
    Set<String> hashSet;
    Set<String> treeSet;
    Set<String> linkedHashSet;
    String data = "abcdefghitjklmnopqrstuvwxyz";

    String[] keys;

    @Setup(Level.Trial)
    public void setUp() {
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        linkedHashSet = new LinkedHashSet<>();

        for(int i = 0; i < LOOP_COUNT; i++) {
            String tempData = data + i;
            hashSet.add(tempData);
            treeSet.add(tempData);
            linkedHashSet.add(tempData);
        }

        if (keys == null || keys.length != LOOP_COUNT) {
            keys = RandomKeyUtil.generateRandomSetKeysSwap(hashSet);
        }
    }

    @Benchmark
    public void iterateTreeSet() {
        for (String key: keys) hashSet.contains(key);
    }

    @Benchmark
    public void iterateHashSet() {
        for (String key: keys) treeSet.contains(key);
    }

    @Benchmark
    public void iterateLinkedHashSet() {
        for (String key: keys) linkedHashSet.contains(key);
    }

}
