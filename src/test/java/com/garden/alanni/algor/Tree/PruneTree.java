package com.garden.alanni.algor.Tree;

import com.garden.alanni.algor.TreeNode;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.cglib.SpringCglibInfo;

import java.util.*;

/**
 * @author 吴宇伦
 */
public class PruneTree {
    class BuildTree {
        private Map<Integer, Integer> rootIndex;

        public TreeNode build(int[] preorder, int[] inorder) {
            int len = preorder.length;
            rootIndex = new HashMap<>(len * 2);
            for (int i = 0; i < len; ++i) {
                rootIndex.put(inorder[i], i);
            }
            return bst(preorder, 0, len - 1, inorder, 0, len - 1);
        }

        private TreeNode bst(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd) {
                return null;
            }
            int val = preorder[preStart];
            int index = rootIndex.get(val);
            int leftNodeNum = index - inStart;
            TreeNode root = new TreeNode(val);
            root.left = bst(preorder, preStart + 1, preStart + leftNodeNum, inorder, inStart, index - 1);
            root.right = bst(preorder, preStart + leftNodeNum + 1, preEnd, inorder, index + 1, inEnd);
            return root;
        }
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            root = null;
        }
        return root;
    }

    public static int maxDepth(TreeNode root) {
        int ans = 0;
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.pollLast();
                if (node.left != null) {
                    queue.push(node.left);
                }
                if (node.right != null) {
                    queue.push(node.right);
                }
                size--;
            }

        }
        return ans;
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        } else if (root.left != null) {
            return minDepth(root.left) + 1;
        } else if (root.right != null) {
            return minDepth(root.right) + 1;
        }
        return 1;
    }

    static class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public static int minDepthOn(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<QueueNode> deque = new LinkedList<>();
        deque.push(new QueueNode(root, 1));
        while (!deque.isEmpty()) {
            QueueNode queueNode = deque.pollLast();
            TreeNode node = queueNode.node;
            if (node.left == null && node.right == null) {
                return queueNode.depth;
            }
            if (node.left != null) {
                deque.push(new QueueNode(node.left, queueNode.depth + 1));
            }
            if (node.right != null) {
                deque.push(new QueueNode(node.right, queueNode.depth + 1));
            }
        }
        return 0;
    }

    class LeafSimilar {
        public boolean work(TreeNode root1, TreeNode root2) {
            if (root1 == null || root2 == null) {
                return false;
            }
            List<Integer> leaf1 = new ArrayList<>();
            List<Integer> leaf2 = new ArrayList<>();
            recur(root1, leaf1);
            recur(root2, leaf2);
            if (leaf1.size() == leaf2.size()) {
                int len = leaf1.size();
                for (int i = 0; i < len; ++i) {
                    if (!leaf1.get(i).equals(leaf2.get(i))) {
                        return false;
                    }
                }
            }

            return true;
        }

        public void recur(TreeNode root, List<Integer> leaf) {
            if (root == null) {
                return;
            }
            recur(root.left, leaf);
            recur(root.right, leaf);
            if (root.left == null && root.right == null) {
                leaf.add(root.val);
            }
        }
    }

    public static boolean isValidSerialization(String preorder) {
        String[] ss = preorder.split(",");
        int len = ss.length;
        int out = 0, in = 2;
        String root = ss[0];
        for (int i = 1; i < len; ++i) {
            String ch = ss[i];
            if (ch.equals("#")) {
                out += 1;
            } else {
                out += 1;
                in += 2;
            }
        }
        return out == in;
    }

    public static void main(String[] args) {
//        TreeNode left = null;
//        TreeNode right = new TreeNode(0, new TreeNode(0, null, null), new TreeNode(0, null, null));
//        TreeNode root = new TreeNode(0, left, right);
//        TreeNode treeNode = pruneTree(root);
//        System.out.println(root);

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2, new TreeNode(4, null, null),  null);
//        root.right = new TreeNode(3, null, new TreeNode(5, null, null));
//        int i = maxDepth(root);

//        TreeNode root = new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6, null, null)))));
//        TreeNode root = new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15, null, null), new TreeNode(7, null, null)));
//        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4, null, null), new TreeNode(5, null, null)), new TreeNode(3, null, null));
//        TreeNode root = new TreeNode(0, new TreeNode(2, new TreeNode(1, new TreeNode(5), new TreeNode(1)), null), new TreeNode(4, new TreeNode(3, new TreeNode(6), null), new TreeNode(-1, new TreeNode(8), null)));
//        int i = minDepthOn(root);

//        String s = "9,3,4,#,#,1,#,#,2,#,6,#,#";
//        isValidSerialization(s);
    }
}
