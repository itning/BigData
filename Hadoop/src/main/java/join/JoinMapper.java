package join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class JoinMapper extends Mapper<LongWritable, Text, Text, JoinBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strings = value.toString().split("\t");
        JoinBean joinBean = new JoinBean();
        if ("t_order.txt".equals(((FileSplit) context.getInputSplit()).getPath().getName())) {
            joinBean.setOid(Long.parseLong(strings[0]));
            joinBean.setOdate(strings[1]);
            joinBean.setOpid(strings[2]);
            joinBean.setOamount(Long.parseLong(strings[3]));

            joinBean.setPid("");
            joinBean.setPname("");
            joinBean.setCategory_id("");
            joinBean.setPrice(0);

            context.write(new Text(strings[2]), joinBean);
        } else {
            joinBean.setPid(strings[0]);
            joinBean.setPname(strings[1]);
            joinBean.setCategory_id(strings[2]);
            joinBean.setPrice(Long.parseLong(strings[3]));

            joinBean.setOid(0);
            joinBean.setOdate("");
            joinBean.setOpid("");
            joinBean.setOamount(0);

            context.write(new Text(strings[0]), joinBean);
        }
    }
}
