package xunshan.ds;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by eldorado on 17-5-2.
 * http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 * 邻接链表表示法
 */
public class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        this.V = v;
        this.adj = new LinkedList[this.V];
        for (int i = 0; i < V; i++)
            this.adj[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /**
     * 给定一个顶点值，访问所有的顶点
     * @param s 顶点值
     */
    public void bfs(int s) {
        boolean visited[] = new boolean[this.V];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while(queue.size() != 0) {
            // 访问当前顶点
            s = queue.poll();
            System.out.println(s + " ");

            // 访问当前顶点的所有邻接点
            ListIterator<Integer> iter = adj[s].listIterator();
            while (iter.hasNext()) {
                int n = iter.next();
                // 把未访问的放入队列
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
