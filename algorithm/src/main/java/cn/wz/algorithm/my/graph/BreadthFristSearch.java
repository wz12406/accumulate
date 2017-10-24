package cn.wz.algorithm.my.graph;

import cn.wz.algorithm.algs4.Graph;
import cn.wz.algorithm.algs4.Queue;

/**
 * @author Administrator
 * @date 2017/10/24 17:09
 * @desc 图的广度优先搜索
 */
public class BreadthFristSearch {

    private boolean[] marked;

    private Queue<Integer> queue;

    public  BreadthFristSearch(Graph g, int s){
        marked = new boolean[g.V()];
        queue = new Queue();
        queue.enqueue(s);
        marked[s] = true;
        bfs(g);
    }

    private void bfs(Graph g) {
        while (queue.size()>0){
            int v = queue.dequeue();
            for(int i:g.adj(v)){
                if(!marked[i]){
                    marked[i] = true;
                    queue.enqueue(i);
                }
            }
        }
    }
}
