package com.garden.alanni.util.download;

import com.garden.alanni.util.download.support.DownThread;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 吴宇伦
 */
public class DownloadUtil {
    private String sourcePath;

    private String targetPath;

    private int threadNum;

    private DownThread[] downThreads;

    private double completeRate;

    private long fileSize;


    public DownloadUtil() {
    }

    public DownloadUtil(String sourcePath, String targetPath, Integer threadNum) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.threadNum = threadNum;
        downThreads = new DownThread[threadNum];
    }

    public void download() throws Exception {
        // 1. 获取文件大小
        URL url = new URL(sourcePath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        fileSize = connection.getContentLengthLong();
        System.out.println("文件总大小: " + fileSize);
        // 2. 计算每个线程所需负责下载的文件大小
        long perThreadSize = fileSize / threadNum + 1;
        System.out.println("线程数量：" + threadNum + " 线程负责大小：" +  perThreadSize);
        // 3. 创建空白文件
        RandomAccessFile file = new RandomAccessFile(targetPath, "rw");
        file.setLength(fileSize);
        file.close();

        // 4. 启动并行下载
        for (int i = 0; i < threadNum; i++) {
            long startPosition = i * perThreadSize;
            RandomAccessFile currentThreadFile = new RandomAccessFile(targetPath, "rw");
            // 改变文件指针的位置 从指针位置开始读写
            currentThreadFile.seek(startPosition);
            downThreads[i] = new DownThread(sourcePath, startPosition, perThreadSize, currentThreadFile);
            downThreads[i].start();
        }
    }

    public double currentDownloadRate() {
        long currentTotalDown = 0l;
        for (DownThread downThread : downThreads) {
            currentTotalDown += downThread.getCurrentCountLength();
        }
        return (double) currentTotalDown / fileSize;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public double getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(double completeRate) {
        this.completeRate = completeRate;
    }

    public DownThread[] getDownThreads() {
        return downThreads;
    }

    public void setDownThreads(DownThread[] downThreads) {
        this.downThreads = downThreads;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
