package com.HuangTaiQi.www.utils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextLog {
    private static String textLog = "";
    private static final Logger logger=Logger.getLogger(TextLog.class.getName());
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
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.log(Level.SEVERE,"Failed to create a file");
                throw new RuntimeException(e);
            }
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textLog);
                textLog = "";
            } catch (IOException e) {
                logger.log(Level.SEVERE,"Failed to save text log to file: " + e.getMessage());
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
        LocalDateTime now = LocalDateTime.now();
        textLog += "\n"+now.getYear()+":"+now.getMonthValue()+":"+now.getDayOfMonth()+
                "  "+now.getHour()+":"+now.getMinute()+"\n"+text;
    }
    public static void readTxt(int year,int month,int day) throws Exception {
        FileReader fileReader = null;
        if(TextLog.class.getResource("textLog_" + year + "-" + month+ "-" +day + ".txt")==null){
            System.out.println("文件找不到，可能没有生成,或你的日期输入错误");
            return;
        }
        File file=new File(TextLog.class.getResource("textLog_" + year + "-" + month+ "-" +day + ".txt").getFile());
        if(file.exists()){
            //创建一个读取文件的流对象
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //定义一个空字符串
            String str;
            while((str = bufferedReader.readLine())!= null) {
                System.out.println(str);
            }
            fileReader.close();
        }else {
            System.out.println("文件找不到，可能没有生成");
        }
    }


}

