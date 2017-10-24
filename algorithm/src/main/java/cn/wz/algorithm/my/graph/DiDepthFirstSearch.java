package cn.wz.algorithm.my.graph;

import cn.wz.algorithm.algs4.Digraph;

/**
 * @author Administrator
 * @date 2017/10/24 16:59
 * @desc 有向图深度优先遍历
 */
public class DiDepthFirstSearch {

    private  Digraph digraph;
    private  boolean[] marked;

    public DiDepthFirstSearch(Digraph digraph){
        this.digraph = digraph;
        marked = new boolean[digraph.V()];
        dfs(digraph,0);
    }

    /**
     * 有向图深度优先遍历
     * @param digraph
     * @param v
     */
    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for(int i:digraph.adj(v)){
            if(!marked[i]){
                dfs(digraph,i);
            }
        }
    }
}
