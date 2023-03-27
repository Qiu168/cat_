package com.HuangTaiQi.www.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TextLog {
    private static String textLog = "";

    /**
     *  每天的毫秒数
     */
    private static final long DAY_IN_MILLIS = 86400000;

    /**
     *  定时任务，每天0点执行一次
     */
    private static final TimerTask SAVE_TO_FILE = new TimerTask() {
        @Override
        public void run() {
            LocalDateTime now = LocalDateTime.now();
            String fileName = "textLog_" + now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth() + ".txt";
            File file = new File(fileName);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textLog);
                textLog = "";
            } catch (IOException e) {
                System.err.println("Failed to save text log to file: " + e.getMessage());
            }
        }
    };

    static {
        // 创建定时器
        Timer timer = new Timer();
        // 计算到明天0点的时间差
        long delay = DAY_IN_MILLIS - (System.currentTimeMillis() % DAY_IN_MILLIS);
        // 启动定时任务
        timer.scheduleAtFixedRate(SAVE_TO_FILE, delay, DAY_IN_MILLIS);
    }

    public static void add(String text) {
        textLog += text;
    }
}

