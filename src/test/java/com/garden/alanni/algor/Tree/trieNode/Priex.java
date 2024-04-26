package com.garden.alanni.algor.Tree.trieNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 吴宇伦
 */
public class Priex {
    static class Solution {
        static class TrieNode {
            boolean end;
            TrieNode[] tns = new TrieNode[26];
        }
        private TrieNode init() {
            TrieNode root = new TrieNode();
            root.end = true;
            return root;
        }
        private void insertTrieNode(TrieNode root, String s) {
            int len = s.length();
            for (int pos = 0; pos < len; ++pos) {
                char ch = s.charAt(pos);
                int index = ch - 'a';
                if (root.tns[index] == null) {
                    root.tns[index] = new TrieNode();
                }
                root = root.tns[index];
            }
            root.end = true;
        }

        private boolean startWith(TrieNode root, String s) {
            int len = s.length();
            for (int i = 0; i < len; ++i) {
                int pos = s.charAt(i) - 'a';
                if (root.tns[pos] == null || !root.end) {
                    return false;
                }
                root = root.tns[pos];
            }
            return root != null && root.end;
        }

        public String longestWord(String[] words) {
            TrieNode root = init();
            for (String s : words) {
                insertTrieNode(root, s);
            }
            String ans = "";
            for (String s : words) {
                if (s.length() < ans.length()) {
                    continue;
                }
                if (s.length() == ans.length() && s.compareTo(ans) > 0) {
                    continue;
                }
                if (startWith(root, s)) {
                    ans = s;
                }
            }
            return ans;
        }
    }

    public static boolean isValidSerialization(String preorder) {
        String[] ss = preorder.split(",");
        Stack<String> stack = new Stack<>();
        for (String s : ss) {
            stack.add(s);
            while (stack.size() >= 3 && stack.get(stack.size() - 1).equals("#") && stack.get(stack.size() - 1).equals(stack.get(stack.size() - 2)) && !stack.get(stack.size() - 3).equals("#")) {
                int len = stack.size();
                stack.remove(len - 2);
                stack.remove(len - 3);
            }
            String[] strs = {"a", "b"};
            List<String> sortStrs = Arrays.stream(strs).sorted(new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    if (a.length() < b.length()) {
                        return 1;
                    } else if (a.length() > b.length()) {
                        return -1;
                    } else {
                        return a.compareTo(b);
                    }
                }
            }).collect(Collectors.toList());
            String ans = sortStrs.get(0);
            for (int i = 1; i < sortStrs.size(); ++i) {
                String str = sortStrs.get(i);
                while (!str.startsWith(ans)) {
                    ans = ans.substring(0, ans.length() - 2);
                }
            }
        }
        return stack.size() == 1 && stack.get(0).equals("#");
    }

    public static void main(String[] args) {
//        String[] words = {"w","wo","wor","worl","world"};
//        String[] words = {"a","banana","app","appl","ap","apply","apple"};
//        String[] words = {"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"};
//        String[] words = {"ogz","eyj","e","ey","hmn","v","hm","ogznkb","ogzn","hmnm","eyjuo","vuq","ogznk","og","eyjuoi","d"};
//        Solution solution = new Solution();
//        String word = solution.longestWord(words);
//        System.out.println(word);
        String preOrder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        isValidSerialization(preOrder);
    }
}
