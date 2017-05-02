package xunshan.ds;

/**
 * Created by eldorado on 17-5-2.
 */
public class BFS {
    public static void main(String[] args) {
        Graph g = new Graph(4);

        // 生成图
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        g.bfs(2);
    }

}
