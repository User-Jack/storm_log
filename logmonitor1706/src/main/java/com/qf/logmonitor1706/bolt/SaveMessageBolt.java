package com.qf.logmonitor1706.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import com.qf.logmonitor1706.domain.Record;
import com.qf.logmonitor1706.utils.MonitorHandler;

public class SaveMessageBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {

        Record record = (Record) input.getValueByField("record");

        MonitorHandler.save(record);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
