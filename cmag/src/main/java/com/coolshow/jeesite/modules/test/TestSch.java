package com.coolshow.jeesite.modules.test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 */
public class TestSch {

    public  static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");

    public static void main( String[] args ) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("ScheduledThreadPool:"+simpleDateFormat.format( new Date() ));
            }
        };
        executorService.scheduleAtFixedRate(run,0,30, TimeUnit.SECONDS);
        //executorService.shutdown();
    }
}
