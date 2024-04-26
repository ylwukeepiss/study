package com.garden.alanni.regularExpression;

import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

/**
 * @author 吴宇伦
 * @function 解决重复单词问题
 */
public class RepeatWord {
    public static void main(String[] args) {
        Pattern regex1 = Pattern.compile("\\b([a-z]+)((?:\\s|\\<[^>]+\\>)+(\\1\\b))", Pattern.CASE_INSENSITIVE);
        String replace1 = "\033[7m$1\033[m$2\033[7m$3\033[m";

        Pattern regex2 = Pattern.compile("^(?:[^\\e]*\\n+)", Pattern.MULTILINE);
        Pattern regex3 = Pattern.compile("^([^\\n]+)", Pattern.MULTILINE);
        for (String arg : args) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(arg));
                String text;
                while (StringUtils.hasLength(text = getParagraph(bufferedReader))) {
                    text = regex1.matcher(text).replaceAll(replace1);
                    text = regex2.matcher(text).replaceAll("");
                    text = regex3.matcher(text).replaceAll(arg + ": $1");
                    System.out.println(text);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 读取一段文本
     * @param bufferedReader 输入流
     * @return 输入文本
     */
    static String getParagraph(BufferedReader bufferedReader) throws IOException {
        StringBuilder buffer = new StringBuilder();
        String line;
        while (StringUtils.hasLength(line = bufferedReader.readLine())) {
            buffer.append(line + "\n");
        }
        return buffer.toString();
    }
}
