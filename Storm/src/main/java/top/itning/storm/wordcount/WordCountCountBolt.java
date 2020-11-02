package top.itning.storm.wordcount;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * 累加计数
 *
 * @author itning
 */
public class WordCountCountBolt extends BaseRichBolt {

    private HashMap<String, Integer> countMap = new HashMap<>();
    private OutputCollector collector;

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        try {
            String s = input.getString(0);
            //存在
            if (countMap.containsKey(s)) {
                countMap.replace(s, countMap.get(s) + 1);
            } else {
                countMap.put(s, 1);
            }
            System.out.println("countMap-->" + countMap.toString());
            collector.ack(input);
        } catch (Exception e) {
            collector.fail(input);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}
