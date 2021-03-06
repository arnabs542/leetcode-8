public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }
        int start = 0, end = 0;
        for (int l : L) {
            end = Math.max(l, end);
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(L, mid) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(L, end) >= k) {
            return end;
        }
        return start;
    }
    
    private int count(int[] L, int len) {
        int sum = 0;
        for (int l : L) {
            sum += l / len;
        }
        return sum;
    }
}

/* 算法：按照值域的二分，想想木条可能多长，左边都是能满足的，右边都是不能满足的，找到满足条件的最大值，这就是二分性
** 时间复杂度：O(loglen * N) */