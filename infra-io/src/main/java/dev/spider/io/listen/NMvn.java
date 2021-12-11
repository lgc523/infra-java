package dev.spider.io.listen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NMvn {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.getRuntime();
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    runtime.exec("mvn help:effective-settings").getInputStream()));
            String line;
            String prefix = "<localRepository>";
            String eof = "</localRepository>";
            while ((line = is.readLine()) != null) {
                String target = new String(line.getBytes(StandardCharsets.UTF_8));
                if (target.contains(eof)) {
                    String goal = target.substring(target.indexOf(prefix) + 17, target.indexOf(eof));
                    Files.walkFileTree(Paths.get(goal), new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            Files.delete(file);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            if (exc == null) {
                                Files.delete(dir);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                }
            }
            is.close();
        } catch (IOException e) {

        }
    }
}