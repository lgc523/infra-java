package dev.spider.jvm.clint;

/**
 * @author spider
 */
public class ClintDeadLock {
    public static void main(String[] args) {
        StaticDeadLockMain loadA = new StaticDeadLockMain('A');
        loadA.start();
        StaticDeadLockMain loadB = new StaticDeadLockMain('B');
        loadB.start();
    }
}
