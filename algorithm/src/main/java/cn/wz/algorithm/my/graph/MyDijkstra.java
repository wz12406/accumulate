package cn.wz.algorithm.my.graph;

import cn.wz.algorithm.algs4.IndexMinPQ;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author Administrator
 * @date 2017/10/24 13:42
 * @desc  自己实现的dijkstra算法 实现查询有向图的最短路径算法
 */
public class MyDijkstra {

    private WeightDiGraph graph;
    private WeightDiEdge edgeto[]; //记录最低最短路径经过的边
    private double distto[]; //记录到达各个点的最短路径权重
    private IndexMinPQ<Double> pq;
    private  int s; //起点

    public MyDijkstra(WeightDiGraph graph,int s){
        this.graph = graph;
        this.edgeto = new WeightDiEdge[graph.V()];
        distto = new double[graph.V()];
        this.pq = new IndexMinPQ<Double>(graph.V());
        for(int i=0;i<distto.length;i++){
            distto[i] = Double.MAX_VALUE;
        }
        this.s = s;
        distto[s] = 0;
        pq.insert(s,0.0);
        while (!pq.isEmpty()){
            relax(graph,pq.delMin());
        }
    }

    private void relax(WeightDiGraph graph, int s) {
        List<WeightDiEdge> list = graph.adj(s);
        for(WeightDiEdge edge:list){
            if(distto[edge.to()]> new BigDecimal(edge.weight()).add(new BigDecimal(distto[edge.from()])).doubleValue()){
                distto[edge.to()] = new BigDecimal(edge.weight()).add(new BigDecimal(distto[edge.from()])).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                edgeto[edge.to()] = edge;
                if(pq.contains(edge.to())){
                    pq.change(edge.to(),edge.weight());
                }else{
                    pq.insert(edge.to(),edge.weight());
                }
            }
        }
    }

    /**
     * 是否有s->v的路径
     * @param v
     * @return
     */
    public boolean hasPathTo(int v){
        return this.distto[v]!=Double.MAX_VALUE;
    }

    /**
     * s->v的最短路径边的集合
     * @param v
     * @return
     */
    public Iterable<WeightDiEdge> pathTo(int v){
       if(!hasPathTo(v)) {
           System.out.println("路径不可达");
           return null;
       }
        int k = v;
        Stack<WeightDiEdge> stack = new Stack<>();
        for (WeightDiEdge e = edgeto[v]; e != null; e = edgeto[e.from()]) {
            stack.push(e);
        }
        return stack;
    }

    public static void main(String[] args) {
        WeightDiGraph graph = new WeightDiGraph(WeightDiGraph.class.getClassLoader().getResourceAsStream("tinyEWD.txt"));
        MyDijkstra dijkstra = new MyDijkstra(graph,0);
        Iterable iterable = dijkstra.pathTo(7);
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()){
            WeightDiEdge edge = (WeightDiEdge) iterator.next();
            System.out.println(edge.from()+"-->"+edge.to()+"  weight:"+edge.weight());
        }
    }


}
