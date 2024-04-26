package com.garden.alanni.io;

import org.apache.tomcat.jni.Time;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴宇伦
 */
public class ErrorOpenFile {
    private static final String FILE_PATH = "/Users/wuyulun/IdeaProjects/alanni/pom.xml";
    public static void main(String[] args) throws InterruptedException, IOException {
        final Path path = Paths.get(FILE_PATH);
        int fdCnt = 0;
        boolean createFd = true;
        while (createFd) {
            // 为了查看FD的增长 设置阻塞50ms
            TimeUnit.MILLISECONDS.sleep(1);
            fdCnt++;
            Files.newBufferedReader(path);
            System.out.println("打开一个文件描述符" + fdCnt);
        }
    }
}
