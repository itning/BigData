package mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mappper
 * 泛型参数
 * 1->LongWritable 文本偏移量
 * 2->Text 所读到一行文本内容
 * 3->Text 用户输出的KEY
 * 4->LongWritable 用户输出的VALUE
 *
 * @author itning
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    private static final Text TEXT = new Text();
    private static final LongWritable LONG_WRITABLE = new LongWritable();

    /**
     * 每一行输入调用一次map
     *
     * @param key     文本偏移量
     * @param value   所读到一行文本内容
     * @param context 上下文
     * @throws IOException          IOException
     * @throws InterruptedException InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split(" ");
        for (String str : strings) {
            TEXT.set(str);
            LONG_WRITABLE.set(1);
            context.write(TEXT, LONG_WRITABLE);
        }
    }
}
