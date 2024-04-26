package com.garden.alanni.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 跳表实现
 * @author 吴宇伦
 */
public class SkipList<T> {
    public static void main(String[] args) {
        SkipList<String> zset = new SkipList<>();
//        zset.put(1, "1");
//        zset.put(2, "2");
//        zset.put(3, "3");
//        zset.put(5, "5");
//        zset.put(100, "100");
//        zset.put(66, "66");
        for (int i = 1; i < 10; ++i) {
            zset.put(i, String.valueOf(i));
        }
        System.out.println(zset);
        System.out.println("查找66：" + zset.get(66));
        System.out.println("删除64");
        zset.delete(64);
//        zset.delete(66);
    }

    /**
     * 限制最高层数为4
     */
    private static final int MAX_LEVEL = 4;

    private SkipNode<T> head;

    private int level = 0;

    /**
     * 用于产生随机数
     */
    private Random random = new Random();

    public SkipList() {
        this(MAX_LEVEL);
    }

    public SkipList(int level) {
        this.level = level;
        int i = level;
        SkipNode<T> temp;
        SkipNode<T> prev = null;
        while (i-- != 0) {
            temp = new SkipNode<T>(null, Integer.MAX_VALUE);
            temp.down = prev;
            prev = temp;
        }
        // 设置头节点
        head = prev;
    }

    private int getRandomLevel() {
        int lev = 1;
        while (random.nextInt() % 2 == 0) {
            lev++;
        }
        return Math.min(lev, MAX_LEVEL);
    }

    /**
     * 查找跳表中的某个值
     * @param score 参数
     * @return 返回改值的查找结果
     */
    public T get(Integer score) {
        SkipNode<T> t = head;
        while (t != null) {
            if (score.equals(t.score)) {
                return t.val;
            }
            if (t.next == null) {
                t = t.down;
            } else if (t.next.score > score) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
        return null;
    }

    public void put(Integer score, T val) {
        SkipNode<T> t = head, cur = null;
        List<SkipNode<T>> path = new ArrayList<>();
        while (t != null) {
            if (t.score.equals(score)) {
                // 当前score存在 更新val值
                cur = t;
                break;
            }
            if (t.next == null) {
                // 向下层查找 先记录该点
                path.add(t);
                t = t.down;
            } else if (t.next.score > score) {
                path.add(t);
                t = t.down;
            } else {
                t = t.next;
            }
        }
        if (cur != null) {
            while (cur != null) {
                cur.val = val;
                cur = cur.down;
            }
        } else {
            int lev = getRandomLevel();
            if (lev > level) {
                SkipNode<T> temp = null;
                SkipNode<T> prev = head;
                while (level++ != lev) {
                    temp = new SkipNode<>(null, Integer.MAX_VALUE);
                    path.add(0, temp);
                    temp.down = prev;
                    prev = temp;
                }
                head = temp;
                level = lev;
            }
            SkipNode<T> downTemp = null, temp, prev;
            for (int i = level - 1; i >= level - lev; --i) {
                temp = new SkipNode<T>(val, score);
                prev = path.get(i);
                temp.next = prev.next;
                prev.next = temp;
                temp.down = downTemp;
                downTemp = temp;
            }
        }
    }

    public void delete(Integer score) {
        SkipNode<T> t = head;
        while (t != null) {
            if (t.next == null) {
                t = t.down;
            } else if (t.next.score.equals(score)) {
                t.next = t.next.next;
                t = t.down;
            } else if (t.next.score > score) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SkipNode<T> t = head, next;
        while (t != null) {
            next = t;
            while (next != null) {
                sb.append(next.score).append(" ");
                next = next.next;
            }
            sb.append("\n");
            t = t.down;
        }
        return sb.toString().replace(Integer.MAX_VALUE+"","").replace("-2147483648","");
    }

    private static class SkipNode<E> {
        E val;
        Integer score;
        SkipNode<E> next, down;

        public SkipNode(E val, Integer score) {
            this.val = val;
            this.score = score;
        }
    }
}
