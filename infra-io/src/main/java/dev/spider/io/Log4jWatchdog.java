package dev.spider.io;

import org.apache.log4j.helpers.FileWatchdog;

/**
 * @author lgc
 * @apiNote log4j fileWatchDog only files in current dir or specify file
 */
public class Log4jWatchdog {
    public static void main(String[] args) {
        String fileName = "";
        FileWatchdogThread watchdogThread = new FileWatchdogThread(fileName);
        //default 60000
        watchdogThread.setDelay(1000);
        watchdogThread.start();
        do {
        } while (true);
    }

    public static class FileWatchdogThread extends FileWatchdog {
        protected FileWatchdogThread(String filename) {
            super(filename);
        }

        @Override
        protected void doOnChange() {
            System.out.println(filename);
        }
    }
}