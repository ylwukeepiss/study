package com.garden.alanni.regularExpression;

import java.util.regex.*;

/**
 * @author 吴宇伦
 */
public class GetFileLastPath {

    private static final String WINDOWS_FILE_PATH = "C:\\Program\\Files\\wuyulun\\Message";

    private static final Pattern WINDOWS_FILE_LAST_PATH_MATCH = Pattern.compile("^.*\\\\", Pattern.CASE_INSENSITIVE);

    private static final String LINUX_FILE_PATH = "/usr/local/Program/Files/wuyulun/Message";

    private static final Pattern LINUX_FILE_LAST_PATH_MATCH = Pattern.compile("^.*/", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        String windows = WINDOWS_FILE_PATH.replaceFirst("^.*\\\\", "");
        System.out.println(windows);

        String linux = LINUX_FILE_PATH.replaceFirst("^.*/", "");
        System.out.println(linux);
    }
}
