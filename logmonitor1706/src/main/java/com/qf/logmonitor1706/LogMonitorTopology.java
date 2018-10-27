package com.qf.logmonitor1706;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.qf.logmonitor1706.bolt.FilterBolt;
import com.qf.logmonitor1706.bolt.PrepareRecordBolt;
import com.qf.logmonitor1706.bolt.SaveMessageBolt;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

/**
 * 驱动类
 */
public class LogMonitorTopology {
    public static void main(String[] args) throws Exception {

        final TopologyBuilder builder = new TopologyBuilder();

        // 指定获取kefka的topic
        String topic = "logmonitor";

        // 设置请求kafka的配置信息
        ZkHosts hosts = new ZkHosts("node01:2181,node02:2181,node03:2181");
        SpoutConfig spoutConfig = new SpoutConfig(hosts, topic, "/storm", "logmonitor");

        // 配置spout和bolt
        builder.setSpout("kafkaspout", new KafkaSpout(spoutConfig), 1);
        builder.setBolt("filterbolt", new FilterBolt(), 3).shuffleGrouping("kafkaspout");
        builder.setBolt("preparerecordbolt", new PrepareRecordBolt(), 1).fieldsGrouping("filterbolt", new Fields("appId", "message"));
        builder.setBolt("savemessagebolt", new SaveMessageBolt(), 1).shuffleGrouping("preparerecordbolt");

        Config conf = new Config();
        conf.setDebug(true);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);
            StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
        } else {
            conf.setMaxTaskParallelism(3);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("logmonitor", conf, builder.createTopology());
        }

    }
}
