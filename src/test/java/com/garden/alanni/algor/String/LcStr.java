package com.garden.alanni.algor.String;


import javax.management.StringValueExp;
import java.awt.image.ImageProducer;
import java.util.*;

/**
 * @author 吴宇伦
 */
public class LcStr {
    public static Map<Character, Character> brackets = new HashMap<Character, Character>(){
        {
            put(')', '(');
            put('}', '{');
            put(']', '[');
            put('?', '?');
        }
    };

    public static int strStr(String ss, String pp) {
        if (pp.isEmpty()) return 0;

        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) {
                j = next[j];
            }
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) {
                j++;
            }
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) {
                j = next[j];
            }
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) {
                j++;
            }
            // 整一段匹配成功，直接返回下标
            if (j == m) {
                return i - m;
            }
        }
        return -1;
    }

    public static String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        char[] ans = new char[len * 3];
        int index = 0;
        for (int i = 0; i < len; ++i) {
            char ch = chars[i];
            if (ch == ' ') {
                ans[index++] = '%';
                ans[index++] = '2';
                ans[index++] = '0';
            } else {
                ans[index++] = ch;
            }
        }
        return new String(ans, 0, index);
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordsNums = new HashMap<>();
        for (String word : words) {
            Integer num = wordsNums.getOrDefault(word, 0) + 1;
            wordsNums.put(word, num);
        }
        List<Integer> ans = new ArrayList<>();
        int wordNums = words.length;
        int wordSize = words[0].length();
        int windowRight = wordNums * wordSize;
        int len = s.length();
        int i = 0;
        String slidWin = "";
        Map<String, Integer> targetMap = new HashMap<>();
        while (i <= len - windowRight) {
            targetMap.clear();
            slidWin = s.substring(i, i + windowRight);
            boolean contains = true;
            for (int j = 0; j + wordSize <= slidWin.length(); j += wordSize) {
                String word = slidWin.substring(j, j + wordSize);
                Integer num = targetMap.getOrDefault(word, 0) + 1;
                targetMap.put(word, num);
            }
            for (Map.Entry<String, Integer> entry : targetMap.entrySet()) {
                String word = entry.getKey();
                Integer nums = entry.getValue();
                if (wordsNums.containsKey(word)) {
                    if (!wordsNums.get(word).equals(nums)) {
                        contains = false;
                    }
                } else {
                    contains = false;
                }
            }
            if (contains) {
                ans.add(i);
            }
            i++;
        }
        return ans;
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> slidWin = new HashMap<>();
        int len = s.length();
        int left = 0;
        int max = 0;
        for (int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (slidWin.containsKey(c)) {
                left = Math.max(left, slidWin.get(c) + 1);
                left = Math.max(left, slidWin.get(c) + 1);
            }
            slidWin.put(c, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static String minWindow(String s, String t) {
        char[] chars = s.toCharArray(), chart = t.toCharArray();
        int sLen = chars.length;
        int tLen = chart.length;
        // 最小子串起始下标
        int begin = 0;
        // 最小子串长度
        int minWin = sLen + 1;
        // sliding-window 左右指针 [left, right)
        int left = 0, right = 0;
        // 滑动窗口中出现的t字符串个数；
        int distance = 0;
        // 窗口内字符频数
        int[] winFreq = new int[128];
        // t字符串内 字符频数
        int[] tFreq = new int[128];
        for (char ch : chart) {
            tFreq[ch]++;
        }
        while (right < sLen) {
            char chr = chars[right];
            if (winFreq[chr] < tFreq[chr]) {
                distance++;
            }
            winFreq[chr]++;
            right++;
            while (distance == tLen) {
                if (minWin > right - left) {
                    minWin = right - left;
                    begin = left;
                }
                char chl = chars[left];
                if (winFreq[chl] == tFreq[chl]) {
                    distance--;
                }
                winFreq[chl]--;
                left++;
            }
        }
        return minWin == sLen + 1 ? "" : s.substring(begin, begin + minWin);
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int minWin = len + 1;
        int left = 0, right = 0;
        int winSum = 0;
        while (right < len) {
            int valr = nums[right];
            winSum += valr;
            right++;
            while (winSum >= target) {
                minWin = Math.min(minWin, right - left);
                int vall = nums[left];
                winSum -= vall;
                left++;
            }
        }
        return minWin == len + 1 ? 0 : minWin;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int size = len - k + 1;
        int left = 0, right = k - 1;
        int[] ans = new int[size];
        if (k > len) {
            return ans;
        }
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < k; ++i) {
            int val = nums[i];
            maxQueue.add(new int[]{i, val});
        }
        int[] peek = maxQueue.peek();
        if (peek != null) {
            ans[left] = peek[1];
            left++;
            right++;
        }
        while (right < len) {
            int valr = nums[right];
            maxQueue.add(new int[]{right, valr});
            Iterator<int[]> iterator = maxQueue.iterator();
            while (iterator.hasNext()) {
                int[] next = iterator.next();
                if (next[0] < left) {
                    iterator.remove();;
                }
            }
            ans[left] = maxQueue.peek()[1];
            left++;
            right++;
        }
        return ans;
    }

    public static String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int left = 0, right = len - 1;
        while (left < right) {
            char chl = chars[left];
            char chr = chars[right];
            if (!Character.isLetter(chl)) {
                left++;
                continue;
            }
            if (!Character.isLetter(chl)) {
                right--;
                continue;
            }
            chars[left++] = chr;
            chars[right--] = chl;
        }
        return String.valueOf(chars);
    }

    public static boolean isValid(String s) {
        Stack<Character> characters = new Stack<>();
        int len = s.length();
        if (len == 0 || len % 2 != 0) {
            return false;
        }
        // 哨兵
        characters.add('?');
        s = s + "?";
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            Character peek = characters.peek();
            if (peek != null) {
                Character left = brackets.get(ch);
                if (left != null) {
                    if (left.equals(peek)) {
                        characters.pop();
                        continue;
                    }
                }
            }
            characters.add(ch);
        }
        return characters.isEmpty();
    }

    static int N = 1010;
    static char[][] g = new char[N][N];
    static int[] idxs = new int[N];
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int n = s.length();
        Arrays.fill(idxs, 0);
        char[] chars = s.toCharArray();
        for (int i = 0, j = 0, k = 1; i < n; i++) {
            char ch = chars[i];
            g[j][idxs[j]++] = ch;
            j += k;
            if (j < 0) {
                j += 2; k = 1;
            } else if (j == numRows) {
                j -= 2; k = -1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < idxs[i]; j++) {
                sb.append(g[i][j]);
            }
        }
        return sb.toString();
    }

    public static String convert2(String s, int numRows) {
        int len = s.length();
        if (len <= numRows || numRows == 1) {
            return s;
        }
        // 一个周期有多少个字符
        int perTimeNums = numRows + numRows - 2;
        // 一个周期占用多少列
        int perTimeColumn = 1 + numRows - 2;
        // 一共有多少个周期
        int t = len / perTimeNums + 1;
        // 一共有多少列
        int c = t * perTimeColumn;
        char[][] matrix = new char[numRows][c];
        char[] chars = s.toCharArray();
        int x = 0, y = 0;
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < len; ++i) {
            char ch = chars[i];
            matrix[x][y] = ch;
            if (i % perTimeNums < perTimeColumn) {
                x++;
            } else {
                x--;
                y++;
            }
        }
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < c; ++j) {
                if (matrix[i][j] != Character.MIN_VALUE) {
                    ans.append(matrix[i][j]);
                }
            }
        }

        return ans.toString();
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return div(strs, 0, strs.length - 1);
    }

    public static String div(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String left = div(strs, start, mid);
            String right = div(strs, mid + 1, end);
            int len = Math.min(left.length(), right.length());
            int i = 0;
            while (i < len) {
                if (left.charAt(i) == right.charAt(i)) {
                    i++;
                } else {
                    break;
                }
            }
            return left.substring(0, i);
        }
    }

    public static void main(String[] args) {
//        String t = "abbac";
//        lengthOfLongestSubstring(str);
//        String t = "wordgoodgoodgoodbestword";

//        String[] words = (String[]) Arrays.asList("word", "good", "best", "word").toArray();
//        findSubstring(s, words);

//        String s = "ADOBECODEBANC";
//        String t = "ABC";
//        String s1 = minWindow(s, t);
//        System.out.println(s1);
//        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
//        minSubArrayLen(7, nums);
//        maxSlidingWindow(nums, 3);
//        String s = "a-bC-dEf-ghIj";
//        String ans = reverseOnlyLetters(s);
//        System.out.println();
//        String s = "we are champion ";
//        replaceSpace(s);
//        String ss = "abccdeafkgckk";
//        String pp = "deafk";
//        strStr(ss, pp);

//        String s = "()";
//        isValid(s);
//        String paypalishiring = convert2("AB", 1);
//        System.out.println(paypalishiring);

//        String strs[] = {"flower","flow","flight"};
//        String strs[] = {"flower","flower","flower","flower"};
//        String s = longestCommonPrefix(strs);
//        System.out.println(s);

        String s = "hello";
        s = s.substring(1, s.length() - 1);
        System.out.println(s);
        StringBuffer buffer = new StringBuffer();
    }
}
