/* Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

Note:
You may assume that all inputs are consist of lowercase letters a-z. */

public class Solution {
  public List<String> findWords(char[][] board, String[] words) {
    Set<String> results = new HashSet<>();
    if (board == null || board.length == 0 || words == null || words.length == 0) {
        return new ArrayList<>(results);
    }
    Map<String, Boolean> prefixIsWord = getPrefix(words);
    int n = board.length;
    int m = board[0].length;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            boolean[][] visited = new boolean[n][m];
            visited[i][j] = true; 
            dfs(board, visited, i, j, board[i][j] + "", prefixIsWord, results);
        }
    }
    return new ArrayList<>(results);
  }

  private void dfs(char[][] board, boolean[][] visited, int x, int y, String temp,
                   Map<String, Boolean> prefixIsWord, Set<String> results) {
    if (!prefixIsWord.containsKey(temp)) {
        return;
    }
    if (prefixIsWord.get(temp) == true) {
        results.add(new String(temp));
    }
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && !visited[nx][ny]) {
            visited[nx][ny] = true;
            dfs(board, visited, nx, ny, temp + board[nx][ny], prefixIsWord, results);
            visited[nx][ny] = false;
        }
    }
  }

  private Map<String, Boolean> getPrefix(String[] words) {
    Map<String, Boolean> map = new HashMap<>();
    for (String word : words) {
        for (int i = 1; i < word.length(); i++) {
            String sub = word.substring(0, i);
            if (!map.containsKey(sub)) {
                map.put(sub, false);
            }
        }
        map.put(word, true);
    }
    return map;
  }
}
//time: O(n^2 * 4^word.length()), space: O(n^2)