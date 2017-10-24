package cn.wz.algorithm.my.graph;

/**
 * @author Administrator
 * @date 2017/10/24 13:49
 * @desc  有向加权边
 */
public class WeightDiEdge {
    private int from;  //起点
    private int to;  //终点
    private double weight;//权重

    public WeightDiEdge(int from,int to,double weight){
        this.from = from;
        this.to = to;
        this.weight =weight;

    }

    public int from(){
        return from;
    }

    public  int to(){
        return to;
    }

    public double weight(){
       return weight;
    }
}
