package com.qf.logmonitor1706.utils;

import com.qf.logmonitor1706.domain.*;
import com.qf.logmonitor1706.mail.MailInfo;
import com.qf.logmonitor1706.mail.MessageSender;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志监控核心类
 */
public class MonitorHandler {
    // 封装所有用户信息
    private static List<User> userList;
    // 封装所有应用程序信息
    private static List<App> appList;
    // 封装appId对应的所有规则信息
    private static Map<String, List<Rule>> ruleMap;
    // 封装appId对应的所有负责人
    private static Map<String, List<User>> userMap;
    // 定时加载规则信息的标识
    private static boolean reloaded = false;

    static {
        load();
    }

    /**
     * 加载数据
     */
    public static void load() {
        if (userList == null) {
            userList = loadUserList();
        }
        if (appList == null) {
            appList = loadAppList();
        }
        if (ruleMap == null) {
            ruleMap = loadRuleMap();
        }
        if (userMap == null) {
            userMap = loadUserMap();
        }
    }

    /**
     * 获取所有有效的用户信息
     */
    private static List<User> loadUserList() {
        return new LogMonitorDao().getUserList();
    }

    /**
     * 获取所有应用程序信息
     */
    private static List<App> loadAppList() {
        return new LogMonitorDao().getAppList();
    }

    /**
     * 获取appId对应的所有规则信息
     */
    private static Map<String, List<Rule>> loadRuleMap() {
        HashMap<String, List<Rule>> map = new HashMap<>();
        List<Rule> ruleList = new LogMonitorDao().getRuleList();

        for (Rule rule : ruleList) {
            // 获取该规则id对应的规则信息
            List<Rule> ruleListByAppId = map.get(rule.getAppId() + "");

            if (ruleListByAppId == null) {
                ruleListByAppId = new ArrayList<>();
            }
            ruleListByAppId.add(rule);
            map.put(rule.getAppId() + "", ruleListByAppId);
        }

        return map;
    }

    /**
     * 获取appId对应的所有负责人信息
     */
    private static Map<String, List<User>> loadUserMap() {
        HashMap<String, List<User>> map = new HashMap<>();

        for (App app : appList) {
            String userIds = app.getUserId();
            List<User> userListInApp = map.get(app.getId() + "");
            if (userListInApp == null) {
                userListInApp = new ArrayList<>();
            }

            String[] userIdArray = userIds.split(",");

            for (String userId : userIdArray) {
                userListInApp.add(queryUserByUserId(userId));
            }

            map.put(app.getId() + "", userListInApp);
        }

        return map;
    }

    /**
     * 根据负责人id获取负责人信息
     */
    private static User queryUserByUserId(String userId) {
        for (User user : userList) {
            if (user.getId() == Integer.parseInt(userId))
                return user;
        }

        return null;
    }

    /**
     * 校验数据的方法
     * @param line
     * @return
     */
    public static Message parse(String line) {
        // 切分日志数据
        String[] messageArr = line.split("\\$\\$\\$\\$\\$");

        Message message = null;

        if (messageArr.length != 2) {
            return message;
        }

        if (StringUtils.isBlank(messageArr[0]) || StringUtils.isBlank(messageArr[1])) {
            return message;
        }

        // 校验当前的日志所属appId是否经过授权
        if (appIdIsValid(messageArr[0].trim())) {
            message = new Message();
            message.setAppId(Integer.parseInt(messageArr[0].trim()));
            message.setLine(messageArr[1]);
        }

        return message;
    }

    /**
     * 验证appId是否授权
     */
    private static boolean appIdIsValid(String appId) {
        for (App app : appList) {
            if (app.getId() == Integer.parseInt(appId))
                return true;
        }

        return false;
    }

    /**
     * 对获取的数据进行判断，判断是否触发规则
     * @param message
     * @return
     */
    public static boolean trigger(Message message) {
        if (ruleMap == null) {
            load();
        }

        // 根据appId获取该id的所有规则
        List<Rule> keywordByAppIdList = ruleMap.get(message.getAppId() + "");

        for (Rule rule : keywordByAppIdList) {
            if (message.getLine().contains(rule.getKeyword())) {
                message.setRuleId(rule.getId());
                message.setKeyword(rule.getKeyword());
                return true;
            }
        }

        return false;
    }

    /**
     * 定时更新规则信息，10分钟加载一次
     */
    public static void scheduleLoad() {
        String date = DateUtils.getDateTime();

        // 获取当前时间的分钟数
        int nowMinute = Integer.parseInt(date.split(":")[1]);
        if (nowMinute % 10 == 0) {
            reloadData();
        } else {
            reloaded = true;
        }
    }


    /**
     * 重新加载数据
     */
    private static void reloadData() {
        if (reloaded) {
            userList = loadUserList();
            appList = loadAppList();
            ruleMap = loadRuleMap();
            userMap = loadUserMap();

            reloaded = false;
        }
    }

    /**
     * 发送邮件进行告警
     * @param appId
     * @param message
     */
    public static void notify(String appId, Message message) {
        // 根据appId获取负责人的信息
        List<User> users = getUserIdsByAppId(appId);

        if (senMail(appId, users, message)) {
            message.setIsEmail(1);
        }
    }

    /**
     * 根据appId获取当前app的所有负责人
     */
    private static List<User> getUserIdsByAppId(String appId) {
        return userMap.get(appId);
    }


    private static boolean senMail(String appId, List<User> users, Message message) {
        // 用于保存负责人的Email
        List<String> receiver = new ArrayList<>();

        for (User user : users) {
            receiver.add(user.getEmail());
        }
        for (App app : appList) {
            if (app.getId() == Integer.parseInt(appId)) {
                message.setAppName(app.getName());
            }
        }

        if (receiver.size() >= 1) {
            String date = DateUtils.getDateTime();

            // 拼接邮件内容
            String content = "应用程序[" + message.getAppName() +
                    "]在" + date + "触发了规则，触发信息为：["+ message.getLine() +"]";

            MailInfo mailInfo = new MailInfo("日志监控告警系统", content, receiver, null);

            return MessageSender.sendMail(mailInfo);
        }

        return false;
    }

    /**
     * 把触发信息保存到数据库
     * @param record
     */
    public static void save(Record record) {
        new LogMonitorDao().saveRecord(record);
    }
}
