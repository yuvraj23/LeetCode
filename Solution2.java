import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution2 {

   static  int minCost;
        public static class Pair implements Comparable<Pair>{
            int src;
            int wt;
            Pair(int src,int wt){
                this.src=src;
                this.wt=wt;
            }

            public int compareTo(Pair op){
                return this.wt-op.wt;
            }

        }


        public static class Edge{
            int src;
            int nbr;
            int wt;

            Edge(int s,int n,int w){
                this.src=s;
                this.nbr=n;
                this.wt=w;
            }
        }

        public static void main(String []args){
            int arr[][]={{3,12},{-2,5},{-4,1}};
            minCostConnectPoints(arr);

        }
        public static  int minCostConnectPoints(int[][] points) {

            ArrayList<Edge> graph[]=new ArrayList[points.length];

            for(int i=0;i<points.length;i++){
                graph[i]=new ArrayList<>();
            }

            for(int i=0;i<points.length;i++){
                for(int j=i+1;j<points.length;j++){
                    int distance=Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
                    Edge n1=new Edge(i,j,distance);
                    Edge n2=new Edge(j,i,distance);
                    graph[i].add(n1);
                    graph[j].add(n2);
                }
            }




            PriorityQueue<Pair> pq=new PriorityQueue<>();

            for(Edge e:graph[0]){
                Pair p=new Pair(e.nbr,e.wt);
                pq.add(p);
            }

            int count=0;
            int totalCount=points.length;
            boolean visited[]=new boolean[totalCount];
            visited[0]=true;

            while(count<totalCount-1){
                Pair rp=pq.poll();

                if(visited[rp.src]==true){
                    continue;
                }
                minCost+=rp.wt;
                visited[rp.src]=true;
                for(Edge ed:graph[rp.src]){
                    if(visited[ed.nbr]==false){
                        Pair np=new Pair(ed.nbr,ed.wt);
                        pq.add(np);
                    }
                }
                count++;
            }

            System.out.println(minCost);
            return minCost;

        }


    }

