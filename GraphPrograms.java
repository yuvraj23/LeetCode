import java.util.*;

public class GraphPrograms {
    static class Edge{
        int src;
        int nbr;
        int wt;
        Edge(int src,int nbr,int wt){
            this.src=src;
            this.nbr=nbr;
            this.wt=wt;
        }
        Edge(int src,int nbr){
            this.src=src;
            this.nbr=nbr;
        }

    }
    public static void main(String []args){
        //display(graph);
        //hasPath(graph,0,6,visited);
        //printAllPaths(graph,0,6,visited,"");
//        multisolver(graph,0,6,visited,"",0,40,5);
//        System.out.println("smallestPath - "+smallestPath+" @ "+smallestWeight);
//        System.out.println("largestPath - "+largestPath+" @ "+largestWeight);
//        System.out.println("justSmallestPath - "+jsp+" @ "+jsw);
//        System.out.println("jsutLargestPath - "+jlp+" @ "+jlw);
//        System.out.println("kth LargestPath - "+klp+" @ "+pq.remove());



        //boolean visited[]=new boolean[7];
        //code for hamilton cycle or paths

       // hamiltonianPathAndCycle(graph,graph[0].get(0).src,graph[0].get(0).src,visited,1,""+graph[0].get(0).src);

        // code for perfect friends and get connected components
//        int count=0;
//        List<List<Integer>>  list=new ArrayList<>();
//        for(int i=0;i<graph.length;i++){
//          if(graph[i].size()>0 &&  visited[graph[i].get(0).src]==false){
//              List<Integer> clubs=new ArrayList<>();
//              perfectFriends(graph,i,6,visited,clubs);
//              count++;
//              list.add(clubs);
//          }
//        }
//
//        System.out.println(list);
//        int ways=0;
//        for(int i=0;i<list.size();i++){
//            for(int j=i+1;j<list.size();j++){
//                ways+=(list.get(i).size()*list.get(j).size());
//            }
//        }
//        System.out.println(ways);


        //int [][]grid={{0,3,6},{5,8,1},{2,7,4}};
//        int [][]grid={{0,5,16,7,10,3},{34,26,1,4,17,8},{24,15,6,9,2,11},{27,35,25,20,31,18},{14,23,32,29,12,21},{33,28,13,22,19,30}};
//        //{{0,5,16,7,10,3},{34,26,1,4,17,8},{24,15,6,9,2,11},{27,35,25,20,31,18},{14,23,32,29,12,21},{33,28,13,22,19,30}}
//        int ii=0;
//        int ij=0;
//        for(int i=0;i<grid.length;i++){
//            for(int j=0;j<grid[0].length;j++){
//                if(grid[i][j]==0){
//                    ii=i;
//                    ij=j;
//                    break;
//                }
//            }
//        }
//
//        int row=grid.length;
//        int col=grid[0].length;
//        boolean visited[][]=new boolean[row][col];
//
//        int total=row*col;
//        System.out.println(iterate(grid,row,col,ii,ij,visited,1,total));

        //Q- bsf traversal in graph
        //https://leetcode.com/problems/shortest-path-visiting-all-nodes/description/
        //int a[][]={{1,2,3},{0},{0},{0}};
        //int [][]a={{1},{0,2,4},{1,3,4},{2},{1,2}};
        //bfs(a);


        List<Edge> graph[]=new ArrayList[8];
        for(int i=0;i<8;i++){
            graph[i]=new ArrayList<>();
        }

       //int edges[][] ={{0,1,10},{0,2,40},{1,3,40},{2,4,40},{3,5,40},{4,5,40}};
       int edges[][] ={{0,1,10},{0,3,40},{1,2,40},{1,4,40},{3,4,40},{2,5,40},{2,6,40},{5,6,40}};

        for(int i=0;i<edges.length;i++){
            Edge ed1=new Edge(edges[i][0],edges[i][1],edges[i][2]);
            Edge ed2=new Edge(edges[i][1],edges[i][0],edges[i][2]);
            graph[edges[i][0]].add(ed1);
            graph[edges[i][1]].add(ed2);
        }
        //detetCcycle(graph);
        //isBipartite(graph);
        spreadOfInfection(graph);

    }

    static class Pair{
        int src;
        int nbr;
        int level;
        Pair(int src,int nbr,int l){
            this.src=src;
            this.nbr=nbr;
            this.level=l;
        }
    }

    public static void spreadOfInfection(List<Edge> graph[]){
        int start=0;
        int time=3;
        ArrayDeque<Pair> q=new ArrayDeque<>();
        boolean visited[]=new boolean[graph.length];
        Pair p=new Pair(start,graph[start].get(0).nbr,0);
        q.add(p);
        int count=0;

        while(q.size()>0){
            Pair rp=q.remove();
            if(rp.level==time){
                System.out.println(count);
                return;
            }
            if(visited[rp.src]==true){
                continue;
            }
            count++;
            visited[rp.src]=true;
            for(Edge ed:graph[rp.src]){
                if(visited[ed.nbr]==false){
                    Pair np=new Pair(ed.nbr,ed.src,rp.level+1);
                    q.add(np);
                }
            }

        }
        System.out.println(count);
    }

    public static void isBipartite(List<Edge> graph[]){
        ArrayDeque<Pair> q=new ArrayDeque<>();
        Pair p=new Pair(graph[0].get(0).src,graph[0].get(0).nbr,0);
        boolean visited[]=new boolean[graph.length];

        List<Integer> odd=new ArrayList<>();
        List<Integer> even=new ArrayList<>();

        q.add(p);
        boolean isBiPartite=false;

        while(q.size()>0){
            Pair rp=q.remove();

            if(visited[rp.src]==true){
                int level=rp.level;
                if(level%2==0){
                    if(odd.contains(rp.src)){
                        isBiPartite=true;
                        System.out.println(rp.src+" , level = "+rp.level+"  Bipartite : \nodd = "+even+" \neven = "+odd);
                        break;
                    }
                }else{
                    if(even.contains(rp.src)){
                        isBiPartite=true;
                        System.out.println(rp.src+", level = "+rp.level+"  Bipartite : \nodd = "+even+" \neven = "+odd);
                        break;
                    }
                }
            }
            if(rp.level%2==0){
                even.add(rp.src);
            }else{
                odd.add(rp.src);
            }

            visited[rp.src]=true;

            for(Edge ed:graph[rp.src]){
                if(visited[ed.nbr]==false){
                    Pair np=new Pair(ed.nbr,ed.src,rp.level+1);
                    q.add(np);
                }
            }

        }

        if(isBiPartite==false){
            System.out.println("Not a Bipartite : \nodd = "+even+" \neven = "+odd);
        }

    }











    public static void detetCcycle(List<Edge> graph[]){
        ArrayDeque<Pair> q=new ArrayDeque<>();
        boolean visited[]=new boolean[graph.length];
        Pair p=new Pair(graph[0].get(0).src,graph[0].get(0).nbr,0);
        q.add(p);
        boolean isCycle=false;
        while(q.size()!=0){
            //remove
            Pair pair=q.remove();
            if(visited[pair.src]==true){
                isCycle=true;
                System.out.println("Cycle detected for node + "+pair.src);
                break;
            }
            // mark star
            visited[pair.src]=true;
            // add nbr
           for(Edge e:graph[pair.src]){
               if(visited[e.nbr]==false){
                   Pair np=new Pair(e.nbr,e.src,0);
                   q.add(np);
               }
           }
        }
        if(isCycle==false){
            System.out.println("Cycle not detected");
        }

    }

    public static int bfs(int[][] edg) {
        // bsf travelsel
        List<Edge> []graph=new ArrayList[edg.length];
        int len=edg.length;

        for(int i=0;i<len;i++){
            graph[i]=new ArrayList<>();
        }

        // connecting nodes
        for(int i=0;i<len;i++){
            for(int j=0;j<edg[i].length;j++){
                Edge ed1 =new Edge(i,edg[i][j]);
               // Edge ed2 =new Edge(edg[i][j],i);
                graph[i].add(ed1);
                //graph[edg[i][j]].add(ed2);
            }
        }

        // bfs traversal

        ArrayDeque<List<Edge>> pq=new ArrayDeque<>();
        ArrayDeque<List<Edge>> cq=new ArrayDeque<>();
        pq.add(graph[0]);
        boolean visited[]=new boolean[len];

        while(pq.size()>0){
            List<Edge> el=pq.remove();
            System.out.print(el.get(0).src +" , ");
            visited[el.get(0).src]=true;
            for(Edge ed:el){
                if(visited[ed.nbr]==false){
                    cq.add(graph[ed.nbr]);
                }
            }

            if(pq.size()==0){
                pq=cq;
                cq=new ArrayDeque<>();
                System.out.println();

            }
        }
        //display(graph);
        return 0;
    }

    public static void display(List<Edge> []graph){
        for(int i=0;i<graph.length;i++){
            boolean isSrc=true;
            for(Edge ed:graph[i]){
                if(isSrc){
                    System.out.print(ed.src+" = [ ");
                }
                System.out.print(ed.nbr+" , ");
                isSrc=false;
            }
            System.out.print("]\n");
        }
    }


    public static int  iterate(int [][]grid,int row,int col,int i,int j,boolean visited[][],int count,int total){

        if(visited[i][j]==true){
            return count-1;
        }
        if(count ==total){
            return count;
        }

        visited[i][j]=true;
        System.out.println(count);
        //upward left

        if(i-2 >= 0 && j-1 >=0 && i-2 < grid.length && j-1 < grid[0].length && grid[i-2][j-1]==count){
            count=iterate(grid,row,col,i-2,j-1,visited,count+1,total);
        }

        //upward right
        if(i-2 >= 0 && j+1 >=0 && i-2 < grid.length && j+1 < grid[0].length && grid[i-2][j+1]==count){
            count=iterate(grid,row,col,i-2,j+1,visited,count+1,total);
        }

        //upward mid left
        if(i-1 >= 0 && j-2 >=0 && i-1 < grid.length && j-2 < grid[0].length && grid[i-1][j-2]==count){
            count=iterate(grid,row,col,i-1,j-2,visited,count+1,total);
        }
        // upward mid right
        if(i-1 >= 0 && j+2 >=0 && i-1 < grid.length && j+2 < grid[0].length && grid[i-1][j+2]==count){
            count=iterate(grid,row,col,i-1,j+2,visited,count+1,total);
        }
        // down left
        if(i+2 >= 0 && j-1 >=0 && i+2 < grid.length && j-1 < grid[0].length && grid[i+2][j-1]==count){
            count=iterate(grid,row,col,i+2,j-1,visited,count+1,total);
        }
        // down right
        if(i+2 >= 0 && j+1 >=0 && i+2 < grid.length && j+1 < grid[0].length && grid[i+2][j+1]==count){
            count=iterate(grid,row,col,i+2,j+1,visited,count+1,total);
        }
        // // down mid left
        if(i+1 >=0 && j-2 >=0 && i+1 < grid.length && j-2 < grid[0].length && grid[i+1][j-2]==count){
            count=iterate(grid,row,col,i+1,j-2,visited,count+1,total);
        }

        // // down mid right
        if(i+1 >= 0 && j+2 >=0 && i+1 < grid.length && j+2 < grid[0].length && grid[i+1][j+2]==count){
            count=iterate(grid,row,col,i+1,j+2,visited,count+1,total);
        }
        return count;
    }

    public static void hamiltonianPathAndCycle(List<Edge> []graph,int src,int dest,boolean visited[],int count,String psf){
        if(count==7){
            boolean iscycle=false;
            for(Edge ed:graph[src]){
                if(ed.nbr==dest){
                    System.out.println(psf+" .Hcycle");
                    iscycle=true;
                    break;
                }
            }
            if(iscycle==false){
                System.out.println(psf+" *Hpath");
            }
            return;
        }
//        0123456.
//        0123465.
//        0125643*
//        0346521*
        visited[src]=true;
        for(Edge e:graph[src]){
            if(visited[e.nbr]==false) {
                hamiltonianPathAndCycle(graph, e.nbr, dest, visited, count + 1, psf+" - " + e.nbr);
            }
        }
        visited[src]=false;
    }


    public static void perfectFriends(List<Edge> []graph,int src,int dest,boolean visited[],List<Integer> clubs){
        visited[src]=true;
        clubs.add(src);
        for(Edge ed: graph[src]){
            if(visited[ed.nbr]==false){
                perfectFriends(graph,ed.nbr,dest,visited,clubs);
            }
        }
    }




    public static void getConnectedComponent(List<Edge> []graph,int src,int dest,boolean visited[]){
        visited[src]=true;
        for(Edge ed: graph[src]){
            if(visited[ed.nbr]==false){
                getConnectedComponent(graph,ed.nbr,dest,visited);
            }
        }
    }





    /*
3.1 Smallest path and it's weight separated by an "@"
3.2 Largest path and it's weight separated by an "@"
3.3 Just Larger path (than criteria in terms of weight) and it's weight separated by an "@"
3.4 Just smaller path (than criteria in terms of weight) and it's weight separated by an "@"
3.5 Kth largest path and it's weight separated by an "@"
*/

    static String smallestPath;
    static int smallestWeight=Integer.MAX_VALUE;
    static String largestPath;
    static int largestWeight=Integer.MIN_VALUE;

    static int jsw=Integer.MIN_VALUE;
    static String jsp;

    static int jlw=Integer.MAX_VALUE;
    static String jlp;
    static String klp;

    static PriorityQueue<Integer> pq=new PriorityQueue<>();
    public static void multisolver(List<Edge> []graph,int src,int dest,boolean visited[],String psf,int wsf,int ct,int k){
        if(src==dest){
           if(wsf > largestWeight){
               largestWeight=wsf;
               largestPath=psf;
           }
            if(wsf < smallestWeight){
                smallestWeight=wsf;
                smallestPath=psf;
            }
            if(wsf< ct && wsf >=jsw){
                jsp=psf;
                jsw=wsf;
            }
            if(wsf>ct && wsf<jlw){
                jlp=psf;
                jlw=wsf;
            }
            if(pq.size()<k){
                pq.add(wsf);
                klp=psf;
            }else{
                pq.remove();
                pq.add(wsf);
                klp=psf;
            }
            return;
        }
        visited[src]=true;
        psf=psf+" , "+src;
        for(Edge ed: graph[src]){
            if(visited[ed.nbr]==false){
                multisolver(graph,ed.nbr,dest,visited,psf,wsf+ed.wt,ct,k);
            }
        }
        visited[src]=false;
        return;
    }

    public static void printAllPaths(List<Edge> []graph,int src,int dest,boolean visited[],String psf){
        if(src==dest){
            System.out.println("path - "+psf+" , "+src);
            return;
        }
        visited[src]=true;
        psf=psf+" , "+src;
        for(Edge ed: graph[src]){
            if(visited[ed.nbr]==false){
                printAllPaths(graph,ed.nbr,dest,visited,psf);
            }
        }
        visited[src]=false;
        return;
    }
    public static void hasPath(List<Edge> []graph,int src,int dest,boolean visited[]){
        if(src==dest){
            System.out.println("yes");
            return;
        }
        visited[src]=true;
        for(Edge ed: graph[src]){
            if(visited[ed.nbr]==false){
                hasPath(graph,ed.nbr,dest,visited);
            }
        }
        return;
    }



}
