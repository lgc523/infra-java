package dev.spider.io;


import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.*;

/**
 * @author lgc
 * @apiNote nio watch file change
 */
public class NioLocalResourceListener {
    static int corePoolSize = Runtime.getRuntime().availableProcessors() / 2;
    static int maxMumPoolSize = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService fixedThreadPool = new ThreadPoolExecutor(corePoolSize, maxMumPoolSize, 23,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            Executors.defaultThreadFactory());

    private WatchService ws;
    private String listFilePath;


    public NioLocalResourceListener(String listenerPath) {
        this.listFilePath = listenerPath;
    }

    public static void main(String[] args) throws IOException {
        NioLocalResourceListener resourceListener = new NioLocalResourceListener("");
        resourceListener.Listen("");
    }

    private void Listen(String path) {
        try {
            NioLocalResourceListener resourceListener = new NioLocalResourceListener(path);
            Path p = Paths.get(path);
            p.register(resourceListener.ws,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_CREATE);

            ws = FileSystems.getDefault().newWatchService();
            this.listFilePath = path;
            fixedThreadPool.submit(new NioLocalListen(ws, this.listFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

