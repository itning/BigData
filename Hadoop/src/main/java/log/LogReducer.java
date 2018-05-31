package log;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LogReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    private static final LongWritable LONG_WRITABLE = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        LONG_WRITABLE.set(0);
        long sum = 0;
        for (LongWritable longWritable : values) {
            sum += longWritable.get();
        }
        LONG_WRITABLE.set(sum);
        context.write(key, LONG_WRITABLE);
    }
}
