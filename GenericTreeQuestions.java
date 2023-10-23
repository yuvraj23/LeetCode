import java.util.*;

public class GenericTreeQuestions {

    public static class Node{
            int val;
            ArrayList<Node> children=new ArrayList<>();
            Node(int data){
                this.val=data;
            }

    }
    public static  Node  constructTree(int a[]){
        Node root=null;
        Stack<Node> st=new Stack<>();
        for(int i=0;i<a.length;i++){
            if(a[i]==-1 &&  st.size()>0){
                st.pop();
            }else{
                Node node = new Node(a[i]);
                if (st.size() > 0) {
                    st.peek().children.add(node);
                } else {
                    root = node;
                }
                st.push(node);
            }
        }
        return root;
    }

    public static void main(String []args){
       // int[] arr = {10, 20,2,-1 ,-1 ,30, 50 ,-1 ,60 ,-1, -1 ,40 ,-1, -1};
        int[] arr = {10,20,30,40,-1,-1,-1,50,60,-1,-1,-1};
        Node root=constructTree(arr);
        //displayTree(root);
        displayTree(root);
        System.out.println("----------------");
        System.out.println(diameter(root));

    }


    static int height=0;
    public static int maxDia=0;
    public static int diameter(Node node){
        int d1=-1;
        int d2=-1;
        for(int i=0;i<node.children.size();i++){
            int hh=diameter(node.children.get(i));
            if(hh > d1){
                d2=d1;
                d1=hh;
            }else if(hh>d2){
                d2=hh;
            }
        }
        int cd=d1+d2+2;
       if(cd>maxDia){
           maxDia=cd;
       }
       return maxDia;
    }

    public static int heighRecursive(Node node){
        int h=0;
        for(int i=0;i<node.children.size();i++){
            int hh=heighRecursive(node.children.get(i));
            if(hh+1>h){
                h=hh+1;
            }
        }
        return h;
    }

    public static int maxValFinder(int a[],int n){
        int m=Integer.MIN_VALUE;
        for(int i=0;i<a.length;i++){
            if(a[i]>m && a[i]<n){
                m=a[i];
                a[i]=Integer.MIN_VALUE;
            }
        }
        return m;
    }

    public static void KthLargest(Node node,int k){
        int kLargest=Integer.MAX_VALUE;
        for(int i=0;i<k;i++){
            Pair3 p=new Pair3();
            ceilFloor2(node,kLargest,p);
            kLargest=p.floor;
        }

        System.out.println(kLargest);
    }

    public static class Pair3{
        int floor=Integer.MIN_VALUE;
        int ceil=Integer.MIN_VALUE;
    }

    public static void ceilFloor2(Node node,int n,Pair3 p){

        if(node.val < n && node.val>p.floor){
            p.floor=node.val;
        }

        for(int i=0;i<node.children.size();i++){
            ceilFloor2(node.children.get(i),n,p);
        }
    }


    public static int ceil=Integer.MAX_VALUE;
    public static int floor=Integer.MIN_VALUE;
    public static void ceilFloor(Node node,int n){
        // celi
        if(node.val > n && node.val<ceil){
            ceil=node.val;
        }

        // floor
        if(node.val < n && node.val>floor){
            floor=node.val;
        }

        for(int i=0;i<node.children.size();i++){
            ceilFloor(node.children.get(i),n);
        }
    }





    public static class Pair2{
        Node pre;
        Node suc;
        int state;
        Pair2(int state){
            this.state=state;
        }
    }
    public static void predecessorAndSuccessor(Node node,int n,Pair2 p){
        if(node.val==n){
            p.state++;
            p.pre=p.suc;
            return;
        }
        p.suc=node;
        for(int i=0;i<node.children.size();i++){
            if(p.state==1){
                p.suc=node.children.get(i);
                break;
            }
            predecessorAndSuccessor(node.children.get(i),n,p);
        }
    }

    public static boolean areTreeSimilar(Node n1,Node n2){
        if(n1.children.size()!=n2.children.size()){
            return false;
        }
        boolean isSimilar=true;
        for(int i=0;i<n1.children.size();i++){
            isSimilar=areTreeSimilar(n1.children.get(i),n2.children.get(i));
            if(!isSimilar){
                return isSimilar;
            }
        }
        return isSimilar;
    }

    public static void distanceBetweenNodes(Node node,int n1,int n2){
        if(node == null){
            return;
        }
        List<Integer> p1=nodeToRootPath(node,n1);
        List<Integer> p2=nodeToRootPath(node,n2);

       int ind1=p1.size()-1;
       int ind2=p2.size()-1;

       while(ind1>=0 && ind2>=0){
           if(p1.get(ind1)!=p2.get(ind2)){
               break;
           }
           ind1--;
           ind2--;
       }
       ind1++;
       ind2++;
       System.out.println(Math.abs(ind1+ind2));

    }

    public static List<Integer> nodeToRootPath(Node node,int nodeData){
        List<Integer> path=new ArrayList<>();
        if(node.val==nodeData){
            path.add(node.val);
            return path;
        }

        for(int i=0;i<node.children.size();i++){
           path=nodeToRootPath(node.children.get(i),nodeData);
            if(path.size()>0){
                path.add(node.val);
                return path;
            }
        }
        return path;
    }

    public static  Node  linearize(Node node){
        if(node.children.size()==0){
            return node;
        }

        Node lastNode=node.children.remove(node.children.size()-1);
        Node lkt=linearize(lastNode);

        while(node.children.size()>0){
            Node secondLastNode=node.children.remove(node.children.size()-1);
            Node skt=linearize(secondLastNode);
            skt.children.add(lastNode);
            lastNode=secondLastNode;
        }
        node.children.add(lastNode);
        return lkt;
    }

    public static void removeLeaves(Node node){
        System.out.println(node.val);
        for(int i=node.children.size()-1;i>=0;i--){
            if(node.children.get(i).children.size()==0){
                node.children.remove(i);
                continue;
            }
            removeLeaves(node.children.get(i));
        }

    }

    public static void mirror(Node node){
      for(int i=0;i<node.children.size();i++){
          mirror(node.children.get(i));
      }
      if(node.children.size()>0){
          int h=node.children.size()-1;
          int l=0;
          while(l<h){
              Node temp=node.children.get(l);
              node.children.set(l,node.children.get(h));
              node.children.set(h,temp);
              l++;
              h--;
          }
      }
    }



  public static void levelorderLineWiseZZ(Node root){
        if(root == null){
            return;
        }
        Stack<Node> st1=new Stack<>();
        Stack<Node> st2=new Stack<>();
        st1.push(root);
        int level=0;

        while(st1.size()>0){
            Node temp = st1.pop();
            System.out.print(temp.val+" , ");
            if(level%2!=0){
                for(int i=temp.children.size()-1;i>=0;i--){
                    st2.push(temp.children.get(i));
                }
            }else{
                for(int i=0;i<temp.children.size();i++){
                    st2.push(temp.children.get(i));
                }
            }

            if(st1.size()==0){
                System.out.println();
                st1=st2;
                st2=new Stack();
                level++;
            }


        }


  }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans=new ArrayList<>();

        Queue<Node> pq=new ArrayDeque<>();
        Queue<Node> cq=new ArrayDeque<>();

        pq.add(root);
        List<Integer> lans=new ArrayList<>();
        lans.add(root.val);
        ans.add(lans);
        lans=new ArrayList<>();


        while(pq.size()>0){
            Node temp=pq.remove();
            for(int i=0;i<temp.children.size();i++){
                cq.add(temp.children.get(i));
                lans.add(temp.children.get(i).val);
            }

            if(pq.size()==0){
                ans.add(lans);
                lans=new ArrayList<>();
                pq=cq;
                cq=new ArrayDeque<>();
            }
        }
        System.out.println(ans);
        return ans;

    }

public static class Pair{
        int state;
        Node node;
        Pair(int state,Node node){
            this.state=state;
            this.node=node;
        }
}
    public static void preOrder(Node root){
        Stack<Pair> st=new Stack();
        Pair p=new Pair(0,root);
        st.add(p);
        List<Integer> pre=new ArrayList<>();

        while(st.size()>0){
            Pair temp=st.peek();
            if(temp.state==0){
                pre.add(temp.node.val);
            }
            if(temp.node.children.size()==temp.state){
                st.pop();
                continue;
            }
            Pair np=new Pair(0,temp.node.children.get(temp.state));
            st.push(np);
            temp.state++;

        }
        System.out.println("Pre : "+pre);
    }

    public static void heightOfTree(Node root){
        Queue<Node> pq=new ArrayDeque<>();
        Queue<Node> cq=new ArrayDeque<>();

        pq.add(root);
        int height=0;

        while(pq.size()>0){
            Node temp=pq.remove();

            for(int i=0;i<temp.children.size();i++){
                cq.add(temp.children.get(i));
            }

            if(pq.size()==0 &&  cq.size()>0 ){
                height++;
                pq=cq;
                cq=new ArrayDeque();
            }
        }

        System.out.println("\nheight of tree : "+height);
    }

    public static void maxInTree(Node root){
        Queue<Node> q=new ArrayDeque<>();
        q.add(root);

        int max=Integer.MIN_VALUE;

        while(q.size()>0){
            Node temp=q.remove();

            if(temp.val>max){
                max=temp.val;
            }

            for(int i=0;i<temp.children.size();i++){
                q.add(temp.children.get(i));
            }
        }
        System.out.println("Max is : "+max);

    }



    public static void displayTree(Node root){
        // remove print add-childrenren
        Queue<Node> q=new ArrayDeque<>();
        q.add(root);
        while(q.size()>0){

            // remove
            Node node=q.remove();
            //print
            System.out.print(node.val+" : ");
            for(int i=0;i<node.children.size();i++){
                System.out.print(node.children.get(i).val+" , ");
                //add-childrenren
                q.add(node.children.get(i));
            }
            System.out.println();
        }
    }

    public static void sizeOfTree(Node root){
        if(root==null){
            return;
        }

        Queue<Node> q=new ArrayDeque<>();
        q.add(root);
        int size=1;
        while(q.size()>0){
            // remove
            Node node=q.remove();
            //print
            for(int i=0;i<node.children.size();i++) {
                size++;
                q.add(node.children.get(i));
            }
        }
        System.out.println("\nsize of tree : "+size);

    }


}
