package cn.wz.algorithm.my.graph;

import java.io.InputStream;
import java.util.*;

/**
 * @author Administrator
 * @date 2017/10/24 13:46
 * @desc
 */
public class WeightDiGraph {
    private  Integer[] V; //记录图中的点；
    private Map<Integer,List<WeightDiEdge>> E; //记录图中的边

    public WeightDiGraph(Integer v){
        V = new Integer[v];
        E = new TreeMap<>();
        for(int i:V){
            E.put(i,new ArrayList<>());
        }
    }

    public WeightDiGraph(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        try {
            int v = scanner.nextInt();  //点的数目
            int e = scanner.nextInt();  //边的条数
            V = new Integer[v];
            E = new TreeMap<>();
            for(int j=0;j<v;j++){
                V[j] = j;
                E.put(j,new ArrayList<>());
            }
            for(int i=0;i<e;i++){
                WeightDiEdge wde= new WeightDiEdge(scanner.nextInt(),scanner.nextInt(),scanner.nextDouble());
                addEdge(wde);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            scanner.close();
        }

    }

    /**
     * 添加边
     */
    public void addEdge(WeightDiEdge edge){
        List<WeightDiEdge> list = E.get(edge.from());
        list.add(edge);
        E.put(edge.from(),list);
    }

    /**
     * 获取由图中某一顶点发出的相邻边
     */
    public List<WeightDiEdge> adj(int v){
       return  E.get(v);
    }

    public Integer V(){
        return V.length;
    }

    public static void main(String[] args) throws Exception{


    }
}
