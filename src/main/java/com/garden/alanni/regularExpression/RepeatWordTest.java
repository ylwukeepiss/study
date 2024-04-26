package com.garden.alanni.regularExpression;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 吴宇伦
 * @function 解决重复单词问题
 */
public class RepeatWordTest {
    public static void main(String[] args) {
        Pattern regex1 = Pattern.compile("\\b([a-z]+)((?:\\s|<[^>]+>)+)(\\1\\b)", Pattern.CASE_INSENSITIVE);
//        (?:\s|\<[^>]+\>)+
        String replace1 = "\033[7m$1\033[m$2\033[7m$3\033[m";

        Pattern regex2 = Pattern.compile("^(?:[^\\e]*\\n+)", Pattern.MULTILINE);
        Pattern regex3 = Pattern.compile("^([^\\n]+)", Pattern.MULTILINE);
//        String[] words = new String[]{"hello hello ai", "hallo hallo"};
        String[] words = new String[]{"hello hello hello hello hello ai"};
        for (String word : words) {
            Matcher matcher = regex1.matcher(word);
            if (matcher.find()) {
                String group = matcher.group();
                System.out.println(group);
            }
            word = regex1.matcher(word).replaceAll(replace1);
            System.out.println(word);
            word = regex2.matcher(word).replaceAll("");
            System.out.println(word);
            word = regex3.matcher(word).replaceAll(word + ": $1");
            System.out.println(word);
        }
    }
}
