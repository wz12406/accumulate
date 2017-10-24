package cn.wz.algorithm.my.graph;

import cn.wz.algorithm.algs4.Graph;

/**
 * @author Administrator
 * @date 2017/10/23 11:24
 * @desc
 */
public class DepthFirstSearch {

    private boolean[] marked;

    private Integer count;

    public DepthFirstSearch(Graph g,int s){
        marked = new boolean[g.V()];
        dfs(g,s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        count++;
        for(int i:g.adj(s)){
            if(!marked[i])dfs(g,i);
        }
    }
}
