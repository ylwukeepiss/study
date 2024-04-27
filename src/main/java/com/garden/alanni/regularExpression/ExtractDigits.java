package com.garden.alanni.regularExpression;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 吴宇伦
 * <p>
 *     提取 并保留两位小数
 * </p>
 */
public class ExtractDigits {
    private static final Pattern EXTRACT_DIGITS = Pattern.compile("([^0](\\d+)?\\.?\\d\\d)", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        // 2024-04-27 正则表达式边界并不仅仅于此 可能还有一些问题没有发现
        String digit1 = "101";
        Matcher matcher = EXTRACT_DIGITS.matcher(digit1);
        if (matcher.find()) {
            System.out.println("1： " + matcher.group(1));
        }
        String digit2 = "101.230000";
        matcher = EXTRACT_DIGITS.matcher(digit2);
        if (matcher.find()) {
            System.out.println("2： " + matcher.group(1));
        }
        String digit3 = "01.230000";
        matcher = EXTRACT_DIGITS.matcher(digit3);
        if (matcher.find()) {
            System.out.println("3： " + matcher.group(1));
        }
        String digit4 = "0101.002300";
        matcher = EXTRACT_DIGITS.matcher(digit4);
        if (matcher.find()) {
            System.out.println("4： " + matcher.group(1));
        }

        // 启动并发测试
        List<String> digits = Arrays.asList(digit1, digit2, digit3, digit4);
        for (String digit : digits) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Matcher matcher = EXTRACT_DIGITS.matcher(digit);
                    if (matcher.find()) {
                        System.out.println("concurrency ： " + matcher.group(1));
                    }
                }
            };
            runnable.run();
        }
    }
}
