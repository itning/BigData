package top.itning.kafka2storm;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @author wangn
 */
public class WordCountTopology {
    public static void main(String[] args)  {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("wordCountSpout",
                new KafkaSpout<>(KafkaSpoutConfig.builder("192.168.66.3:9092", "first").build()), 2);
        topologyBuilder.setBolt("wordCountSplitBolt", new WordCountSplitBolt(), 2).shuffleGrouping("wordCountSpout");
        topologyBuilder.setBolt("wordCountBolt", new WordCountBolt(), 4).fieldsGrouping("wordCountSplitBolt", new Fields("word"));

        Config config = new Config();
        config.setNumWorkers(4);
       // config.setNumAckers(0);
        try {
            StormSubmitter.submitTopology("wordCount", config, topologyBuilder.createTopology());
        } catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
            e.printStackTrace();
        }
    }
}
