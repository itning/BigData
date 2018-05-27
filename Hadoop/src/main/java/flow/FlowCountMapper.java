package flow;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text, LongWritable, FlowCountBean> {
    private static final LongWritable LONG_WRITABLE = new LongWritable();
    private static final FlowCountBean FLOW_COUNT_BEAN = new FlowCountBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        LONG_WRITABLE.set(0);
        FLOW_COUNT_BEAN.clear();
        String[] strings = value.toString().split("\t");
        long tel = 0;
        long upload = 0;
        long download = 0;
        if (NumberUtils.isDigits(strings[1])) {
            tel = Long.parseLong(strings[1]);
        }
        if (NumberUtils.isDigits(strings[strings.length - 3])) {
            upload = Long.parseLong(strings[strings.length - 3]);
        }
        if (NumberUtils.isDigits(strings[strings.length - 2])) {
            download = Long.parseLong(strings[strings.length - 2]);
        }
        FLOW_COUNT_BEAN.setTel(tel);
        FLOW_COUNT_BEAN.setUpload(upload);
        FLOW_COUNT_BEAN.setDownload(download);
        FLOW_COUNT_BEAN.setSum(upload + download);
        LONG_WRITABLE.set(tel);
        context.write(LONG_WRITABLE, FLOW_COUNT_BEAN);

    }
}
