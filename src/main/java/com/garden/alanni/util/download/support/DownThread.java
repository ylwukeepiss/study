package com.garden.alanni.util.download.support;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 吴宇伦
 */
public class DownThread extends Thread {

    private String sourcePath;

    private long startPosition;

    private long perThreadSize;

    private RandomAccessFile currentFile;

    private long currentCountLength;

    public DownThread() {

    }

    public DownThread(String sourcePath, Long startPosition, Long perThreadSize, RandomAccessFile currentFile) {
        this.sourcePath = sourcePath;
        this.startPosition = startPosition;
        this.perThreadSize = perThreadSize;
        this.currentFile = currentFile;
    }

    @Override
    public void start() {
        try {
            URL url = new URL(sourcePath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            InputStream inputStream = connection.getInputStream();
            inputStream.skip(startPosition);
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            while (((hasRead = inputStream.read(buffer)) > -1) && currentCountLength < perThreadSize) {
                currentFile.write(buffer, 0, hasRead);
                currentCountLength += hasRead;
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(long startPosition) {
        this.startPosition = startPosition;
    }

    public long getPerThreadSize() {
        return perThreadSize;
    }

    public void setPerThreadSize(long perThreadSize) {
        this.perThreadSize = perThreadSize;
    }

    public RandomAccessFile getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(RandomAccessFile currentFile) {
        this.currentFile = currentFile;
    }

    public long getCurrentCountLength() {
        return currentCountLength;
    }

    public void setCurrentCountLength(long currentCountLength) {
        this.currentCountLength = currentCountLength;
    }
}
