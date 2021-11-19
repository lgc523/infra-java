package dev.spider.io;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @author spider
 * @apiNote Dynamic Refresh When file Change
 */
public class ApacheCommonIoDynamicRefresh {
    public static void main(String[] args) throws Exception {
        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver("");

        FileAlterationListener fileAlterationListener = new JavaEnvListener();
        //register listener
        fileAlterationObserver.addListener(fileAlterationListener);
        FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor();
        //register observer
        fileAlterationMonitor.addObserver(fileAlterationObserver);
        //start listen
        fileAlterationMonitor.start();
        // hang
        Thread.sleep(1000000);
    }

    public static class JavaEnvListener extends FileAlterationListenerAdaptor {

        @Override
        public void onFileChange(File file) {
            System.out.println("change");
            super.onFileChange(file);
        }

        @Override
        public void onStop(FileAlterationObserver observer) {
            System.out.println("observer stop");
            super.onStop(observer);
        }
    }
}
