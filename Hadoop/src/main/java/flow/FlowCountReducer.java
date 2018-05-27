package flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<LongWritable, FlowCountBean, LongWritable, FlowCountBean> {
    private static final FlowCountBean FLOW_COUNT_BEAN = new FlowCountBean();

    @Override
    protected void reduce(LongWritable key, Iterable<FlowCountBean> values, Context context) throws IOException, InterruptedException {
        FLOW_COUNT_BEAN.clear();
        long upload = 0;
        long download = 0;
        long sum = 0;
        for (FlowCountBean flowCountBean : values) {
            upload += flowCountBean.getUpload();
            download += flowCountBean.getDownload();
            sum += flowCountBean.getSum();
        }
        FLOW_COUNT_BEAN.setTel(key.get());
        FLOW_COUNT_BEAN.setUpload(upload);
        FLOW_COUNT_BEAN.setDownload(download);
        FLOW_COUNT_BEAN.setSum(sum);
        context.write(key, FLOW_COUNT_BEAN);
    }
}
