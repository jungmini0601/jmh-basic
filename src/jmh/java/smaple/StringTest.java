package smaple;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringTest {

    @Benchmark
    public void makeSQLString() {
        String strSQL = "";
        strSQL += "select * ";
        strSQL += "from member ";
        strSQL += "where member id = 1";
        strSQL += "order by created_at";
    }


    @Benchmark
    public void makeSQLStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("select * ")
                .append("from member ")
                .append("where member id = 1")
                .append("order by created_at")
                .toString();
    }
}
