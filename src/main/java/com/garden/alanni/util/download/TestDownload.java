package com.garden.alanni.util.download;

/**
 * @author 吴宇伦
 * <p>
 *     多线程下载文件
 *     <li>确定文件大小</li>
 *     <li>在磁盘创建一个和目标文件一样大小的文件夹</li>
 *     <li>计算每个线程需要负责下载的文件大小</li>
 *     <li>(扩展)如果需要支持断点下载 需要记录每个文件下载成功后的文件指针位置，并进行持久化，下次可从指针位置开始继续下载</li>
 * </p>
 */
public class TestDownload {
    public static void main(String[] args) throws Exception {
        String sourcePath = "https://www.baidu.com/";
        String targetFile = "/Users/wuyulun/IdeaProjects/alanni/src/main/resources/static/baidu.html";
        DownloadUtil downloadUtil = new DownloadUtil(sourcePath, targetFile, 2);
        downloadUtil.download();
        // 监控下载进度
        while (true) {
            double completeRate = downloadUtil.currentDownloadRate();
            double rate = completeRate * 100;
            if (completeRate < 1) {
                String rateStr = String.valueOf(rate);
                String currentRate = rateStr.split("\\.")[0];
                System.out.println("下载进度-------" + currentRate + "%");
            } else {
                System.out.println("下载进度-------100%");
                break;
            }
        }
    }
}
