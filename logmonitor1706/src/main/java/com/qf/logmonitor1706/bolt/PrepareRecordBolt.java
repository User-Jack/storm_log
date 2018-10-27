package com.qf.logmonitor1706.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.qf.logmonitor1706.domain.Message;
import com.qf.logmonitor1706.domain.Record;
import com.qf.logmonitor1706.utils.MonitorHandler;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class PrepareRecordBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String appId = input.getValueByField("appId") + "";
        Message message = (Message) input.getValueByField("message");

        // 将触发规则信息进行邮件通知
        MonitorHandler.notify(appId, message);

        Record record = new Record();

        try {
            // 将message的字段信息映射到record
            BeanUtils.copyProperties(record, message);
            collector.emit(new Values(record));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("record"));
    }
}
