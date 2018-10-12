package top.itning.kafka2storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangn
 */
public class WordCountBolt extends BaseRichBolt {

    private OutputCollector collector;
    private HashMap<String, Integer> countMap = new HashMap<>();

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        try {
            String word = input.getStringByField("word");
            System.out.println("WordCountBolt get->" + word + " !" + word.length());
            if (countMap.containsKey(word)) {
                countMap.replace(word, countMap.get(word) + 1);
            } else {
                countMap.put(word, 1);
            }
            System.out.println(countMap);
            collector.ack(input);
        } catch (Exception e) {
            collector.reportError(e);
            collector.fail(input);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
