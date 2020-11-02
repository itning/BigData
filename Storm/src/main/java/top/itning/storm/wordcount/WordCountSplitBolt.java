package top.itning.storm.wordcount;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 接受数据然后执行处理的组件,用户可以在其中执行自己想要的操作。<br/>
 * 字符串进行分割操作
 *
 * @author itning
 */
public class WordCountSplitBolt extends BaseRichBolt {

    private OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        try {
            String word = input.getString(0);
            String[] s = word.split(" ");
            for (String str : s) {
                collector.emit(new Values(str));
            }
            collector.ack(input);
        } catch (Exception e) {
            collector.fail(input);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("splitWord"));
    }
}
