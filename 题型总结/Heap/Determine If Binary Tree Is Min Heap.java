/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public boolean isMinHeap(TreeNode root) {
      if (root == null) {
          return true;
      }
      int count = getCount(root);
      if (isComplete(root, 0, count) && isHeap(root)) {
          return true;
      }
      return false;
  }
  
  private boolean isHeap(TreeNode root) {
      if (root.left == null && root.right == null) {
          return true;
      }
      if (root.right == null) { //if left is null, right must be null(completeness)
          if (root.key > root.left.key) {
              return false;
          } else {
              return root.key <= root.left.key && isHeap(root.left);
          }
      }
      return root.key <= root.left.key && root.key <= root.right.key &&
             isHeap(root.left) && isHeap(root.right);
  }
  
  private boolean isComplete(TreeNode node, int index, int count) {
      if (node == null) {
          return true;
      }
      if (index >= count) {
          return false;
      }
      return isComplete(node.left, index * 2 + 1, count) &&
             isComplete(node.right, index * 2 + 2, count);
  }
  
  private int getCount(TreeNode root) {
      if (root == null) {
          return 0;
      }
      return getCount(root.left) + getCount(root.right) + 1;
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(logn) */
