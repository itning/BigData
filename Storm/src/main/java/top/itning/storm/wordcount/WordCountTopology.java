package top.itning.storm.wordcount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Topology:Storm中运行的一个实时应用程序的名称(拓扑)
 *
 * @author wangn
 */
public class WordCountTopology {
    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("wcSpout", new WordCountSpout(), 2);
        topologyBuilder.setBolt("wcSplit", new WordCountSplitBolt(), 3).shuffleGrouping("wcSpout");
        topologyBuilder.setBolt("wcCount", new WordCountCountBolt(), 4).fieldsGrouping("wcSplit", new Fields("splitWord"));
        Config config = new Config();
        config.setNumWorkers(4);

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("mywordcount", config, topologyBuilder.createTopology());
        //StormSubmitter.submitTopology("mywordcount",config,topologyBuilder.createTopology());
        //Utils.sleep(10000);
        //localCluster.shutdown();
    }
}
