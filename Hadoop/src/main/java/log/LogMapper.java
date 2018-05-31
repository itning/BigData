package log;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    private static final Text TEXT = new Text();
    private static final LongWritable LONG_WRITABLE = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TEXT.clear();
        LONG_WRITABLE.set(0);
        String ip = value.toString().split(" ")[0];
        TEXT.set(ip);
        LONG_WRITABLE.set(1);
        context.write(TEXT, LONG_WRITABLE);
    }
}
