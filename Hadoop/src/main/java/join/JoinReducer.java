package join;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class JoinReducer extends Reducer<Text, JoinBean, Text, JoinOutBean> {
    @Override
    protected void reduce(Text key, Iterable<JoinBean> values, Context context) throws IOException, InterruptedException {
        JoinOutBean joinOutBean = new JoinOutBean();
        boolean flag = true;
        for (JoinBean joinBean : values) {
            if (joinBean.getOid() == 0) {
                joinOutBean.setName(joinBean.getPname());
                joinOutBean.setCid(joinBean.getCategory_id());
                joinOutBean.setPrice(joinBean.getPrice());
                if (joinOutBean.getDate() == null) {
                    continue;
                } else {
                    flag = false;
                }
            }
            if (flag) {
                joinOutBean.setId(joinBean.getOid());
                joinOutBean.setDate(joinBean.getOdate());
            }
            if (joinOutBean.getDate() != null && joinOutBean.getCid() != null) {
                context.write(new Text(joinOutBean.getId() + ""), joinOutBean);
            }
        }
    }
}
