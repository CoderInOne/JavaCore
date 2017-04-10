package xunshan.ds;

import java.util.PriorityQueue;

/**
 * Created by eldorado on 17-4-10.
 *
 * 优先队列的要点
 * 1. 上滤&下滤过程
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 1到9入列
        for (int i = 1; i < 10; i ++) {
            queue.offer(i);
        }

        // 取两次最小
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);

        // 2入列，观察入列siftUp操作
        // 1. 取出k位置的parent节点
        // 2. k节点和parent节点对比
        // 2-0. k值比parent值小，k处存parent值，
        //      k设为parent索引，返回1，并保证k > 0，即sift up
        // 2-1. k值比parent大，直接返回
        queue.offer(2);
        System.out.println(queue);

        queue.remove(6);
        // 1. 移除把子树中较小的子树往上移动，即sift down
        System.out.println(queue);
    }
}
