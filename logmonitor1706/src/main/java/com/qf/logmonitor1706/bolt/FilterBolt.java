package com.qf.logmonitor1706.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.qf.logmonitor1706.domain.Message;
import com.qf.logmonitor1706.utils.MonitorHandler;

/**
 * 过滤触发信息
 */
public class FilterBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        // 获取spout传过来的数据
        Object value = input.getValue(0);
        // 将数据转换为字符串
        String line = new String((byte[])value);

        // 解析验证数据，校验获取的日志是否合法
        Message message = MonitorHandler.parse(line);

        if (message == null) {
            return;
        }

        // 对获取的数据进行判断，是否触发规则
        if (MonitorHandler.trigger(message)) {
            collector.emit(new Values(message.getAppId(), message));
        }

        // 定时更新规则信息
        MonitorHandler.scheduleLoad();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("appId", "message"));
    }
}
