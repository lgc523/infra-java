package dev.spider.io.listen;


import org.apache.log4j.helpers.FileWatchdog;

import java.util.logging.Logger;

/**
 * @author lgc
 * @apiNote log4j fileWatchDog only files in current dir or specify file
 */
public class Log4jWatchdog {
    static final Logger log = Logger.getLogger(Log4jWatchdog.class.getName());

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
            log.info(filename);
        }
    }
}