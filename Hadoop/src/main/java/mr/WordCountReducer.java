package mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author itning
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    private static final LongWritable LONG_WRITABLE = new LongWritable();

    /**
     * 每个KEY调用一次reduce
     *
     * @param key     同一组的KEY
     * @param values  所有的可迭代的值
     * @param context 上下文
     * @throws IOException          IOException
     * @throws InterruptedException InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        for (LongWritable longWritable : values) {
            count += longWritable.get();
        }
        LONG_WRITABLE.set(count);
        context.write(key, LONG_WRITABLE);
    }
}
