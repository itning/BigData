package top.itning.storm.wordcount;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 在一个topology中获取源数据流的组件
 *
 * @author wangn
 */
public class WordCountSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;
    private Map<String, Values> buffer = new HashMap<>();

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String messageId = UUID.randomUUID().toString().replace("-", "");
        Values values = new Values("a b c d e f");
        buffer.put(messageId, values);
        collector.emit(values, messageId);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("消息处理成功:" + msgId);
        buffer.remove(msgId.toString());
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("消息处理失败:" + msgId);
        Values values = buffer.get(msgId.toString());
        collector.emit(values, msgId);
    }
}
