package top.itning.kafka2storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
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
        //"topic", "partition", "offset", "key", "value"
        try {
            String value = input.getStringByField("value");
            System.out.println("WordCountSplitBolt get->" + value);
            char[] chars = value.toCharArray();
            for (char c : chars) {
                collector.emit(new Values(String.valueOf(c)));
            }
            collector.ack(input);
        } catch (Exception e) {
            collector.reportError(e);
            collector.fail(input);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
