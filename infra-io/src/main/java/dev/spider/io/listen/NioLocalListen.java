package dev.spider.io.listen;

import java.io.IOException;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

/**
 * @author lgc
 * @apiNote nio listen local
 */
public class NioLocalListen implements Runnable {
    private WatchService ws;
    private String localPath;

    public NioLocalListen(WatchService service, String localPath) {
        this.ws = service;
        this.localPath = localPath;
    }

    @Override
    public void run() {
        try {
            do {
                WatchKey watchKey = ws.take();
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for (WatchEvent<?> event : watchEvents) {
                    System.out.println(localPath + event.context() + event.kind() + event.count());
                }
                watchKey.reset();
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("gg smd");
            try {
                ws.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}