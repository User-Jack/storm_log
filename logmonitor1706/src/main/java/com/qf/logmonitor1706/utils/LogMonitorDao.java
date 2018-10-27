package com.qf.logmonitor1706.utils;

import com.qf.logmonitor1706.dao.DataSourceUtil;
import com.qf.logmonitor1706.domain.App;
import com.qf.logmonitor1706.domain.Record;
import com.qf.logmonitor1706.domain.Rule;
import com.qf.logmonitor1706.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class LogMonitorDao {
    private JdbcTemplate jdbcTemplate;

    public LogMonitorDao() {
        jdbcTemplate = new JdbcTemplate(DataSourceUtil.getDataSource());
    }

    /**
     * 查询所有用户信息
     */
    public List<User> getUserList() {
        String sql = "select id,name,mobile,email,isValid from log_monitor_user where isValid = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    /**
     * 查询所有规则信息
     */
    public List<Rule> getRuleList() {
        String sql = "select id,name,keyword,isValid,appId from log_monitor_rule where isValid = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Rule>(Rule.class));
    }

    /**
     * 查询所有应用程序信息
     */
    public List<App> getAppList() {
        String sql = "select id,name,isOnline,typeId,userId from log_monitor_app where isOnline = 1";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<App>(App.class));
    }

    /**
     * 更新触发规则的信息
     */
    public void saveRecord(Record record) {
        String sql = "insert into log_monitor_rule_record (appId,ruleId,isEmail,isPhone,isClose,noticeInfo,updateDate) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, record.getAppId(), record.getRuleId(), record.getIsEmail(), record.getIsPhone(), 0, record.getLine(), new Date());
    }











}
