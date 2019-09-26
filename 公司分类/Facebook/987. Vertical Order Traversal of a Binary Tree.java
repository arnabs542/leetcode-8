/**
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be 
at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical 
line touches some nodes, we report the values of the nodes in order from top to bottom 
(decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first 
is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have 
a list of values of nodes.

 

Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6. */

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, Queue<Pair>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0, 0));
        
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            map.putIfAbsent(p.x, new PriorityQueue<>(new Comparator<Pair>(){
                public int compare(Pair a, Pair b) {
                    if (a.x == b.x && a.y == b.y) {
                        return a.node.val - b.node.val;
                    }
                    return b.y - a.y;
                }
            }));
            map.get(p.x).add(p);
            if (p.node.left != null) {
                queue.offer(new Pair(p.node.left, p.x - 1, p.y - 1));
            }
            if (p.node.right != null) {
                queue.offer(new Pair(p.node.right, p.x + 1, p.y - 1));
            }
        }
        for (Queue<Pair> item : map.values()) {
            List<Integer> temp = new ArrayList<>();
            
            while (!item.isEmpty()) {
                temp.add(item.poll().node.val);
            }
            results.add(temp);
        }
        return results;
    }
}

class Pair {
    TreeNode node;
    int x, y;
    
    public Pair(TreeNode node, int x, int y) {
        this.node = node;
        this.x = x;
        this.y = y;
    }
}
//与314不同的是，y是decreasing, 同一行的时候，这题是从右往左，314是从左往右 
//time: O(n), space: O(height)