import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {
    public static void main(String []args){
       // int arr[][]={{1,2},{2,3},{5},{0},{5},{},{}};
        //int arr[][]={{1,2,3,4},{1,2},{3,4},{0,4},{}};

        int arr[][]={{},{0,2,3,4},{3},{4},{}};

        eventualSafeNodes(arr);

    }

   public static  class Edge{
        int src;
        int nbr;
        Edge(int src,int nbr){
            this.src=src;
            this.nbr=nbr;
        }
    }

    public static List<Integer> eventualSafeNodes(int[][] arr) {
        List<Integer> safeNodes=new ArrayList<>();
        List<Integer> terminalNodes=new ArrayList<>();
        List<Integer> notSafeNodes=new ArrayList<>();
        int length=arr.length;

        ArrayList<Edge> graph[]=new ArrayList[length];

        for(int i=0;i<length;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=0;i<length;i++){
            if(arr[i].length==0){
                terminalNodes.add(i);
                continue;
            }
            for(int j=0;j<arr[i].length;j++){
                Edge e=new Edge(i,arr[i][j]);
                graph[i].add(e);
            }

        }


        for(int i=0;i<length;i++){
            boolean visited[]=new boolean[length];
            if(notSafeNodes.contains(i)==false){
                boolean isValid=help(graph,i,safeNodes,terminalNodes,visited,notSafeNodes);
                if(isValid){
                    safeNodes.add(i);
                }else{
                    notSafeNodes.add(i);
                }

            }
        }
        return safeNodes;
    }


    public static boolean help(ArrayList<Edge> graph[],int src,List<Integer> safeNodes,List<Integer> terminalNodes,boolean visited[],List<Integer> notSafeNodes){

        if(visited[src]==true || notSafeNodes.contains(src)){
            return false;
        }

        if(safeNodes.contains(src) || terminalNodes.contains(src)){
            return true;
        }

        //{{1,2},{2,3},{5},{0},{5},{},{}};
        visited[src]=true;

        for(Edge e:graph[src]){
            if(visited[e.nbr]==false && notSafeNodes.contains(e.nbr)==false){
                boolean isValid=help(graph,e.nbr,safeNodes,terminalNodes,visited,notSafeNodes);
                if(isValid==false){
                    notSafeNodes.add(e.nbr);
                    return isValid;
                }
            }else{
                return false;
            }
        }
        visited[src]=false;
        return true;
    }
}
