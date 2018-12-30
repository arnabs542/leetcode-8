//给定n步，现在从原点出发，只能往上左右三个方向走，不能经过已经经过的节点。问有多少可能的路径？
public int numberOfPath(int n) {
    //f[i] = 第i步的时候的可能性（可以有多少path）
    int[] f = new int[n + 1];
    f[0] = 1; //当有0步的时候，有一种可能性，就是不走
    f[1] = 3;

    //考虑在i-1步的情况，第i-1步是往上，那么接下来就有三种可能性，而且前面i-2步可以以任意方式到达。因此是dp[i-2]*3
    //i-1步剩下的情况，就是往左往右，对于下一步i都只有两种选择，因此(dp[i-1]-dp[i-2])*2
    //第i-1步的所有可能性是dp[i-1]，第i-1步往上走的可能性是dp[i-2]， 那么第i-1步往左往右走的可能性就是dp[i-1]-dp[i-2]。 
    //这题其实是对i-1步进行了分类讨论
    for (int i = 2; i <= n; i++) {
        f[i] = f[i - 2] * 3 + (f[i - 1] - f[i - 2]) * 2;
    }
    return f[n];
}