import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        static class Pair{
            TreeNode node;
            int state;
            Pair(TreeNode n,int s){
                this.node=n;
                this.state=s;
            }

        }

        public static void display(TreeNode node){
            ArrayDeque<TreeNode> q=new ArrayDeque();
            ArrayDeque<TreeNode> cq=new ArrayDeque();
            q.add(node);

            while(q.size()>0){
                TreeNode temp=q.remove();
                System.out.print(temp.val+" ,  ");

                if(temp.left!=null){
                    cq.add(temp.left);
                }

                if(temp.right!=null){
                    cq.add(temp.right);
                }

                if(q.size()==0){
                    q=cq;
                    cq=new ArrayDeque();
                    System.out.println();
                }
            }
        }

        public static TreeNode constructTree(int a[]){
            Stack<Pair> st=new Stack();
            TreeNode root = new TreeNode(a[0],null,null);
            Pair p=new Pair(root,1);
            st.add(p);
            int ind=1;

            while(st.size()>0){
                Pair temp=st.peek();
                if(temp.state==3){
                    st.pop();
                }else if(a[ind]==-1){
                    temp.state++;
                    ind++;
                } else if(temp.state==1){
                    TreeNode n=new TreeNode(a[ind],null,null);
                    temp.node.left=n;
                    temp.state++;
                    Pair np=new Pair(n,1);
                    st.push(np);
                    ind++;
                }else if(temp.state==2){
                    TreeNode n=new TreeNode(a[ind],null,null);
                    temp.node.right=n;
                    temp.state++;
                    Pair np=new Pair(n,1);
                    st.push(np);
                    ind++;
                }

            }

            return root;
        }

        public static void displayTree(TreeNode root){
            Queue<TreeNode> q=new ArrayDeque<>();
            q.add(root);
            while(q.size()>0){

                // remove
                TreeNode node=q.remove();
                //print
                String s = node.val+"";
                if(node.left!=null){
                    s = node.left.val + " <- " +s;
                    q.add(node.left);
                }else{
                    s="- <- "+s;
                }
                if(node.right!=null){
                    s= s + " -> "+node.right.val;
                    q.add(node.right);
                }else{
                    s=s+" -> -";
                }

                System.out.println(s);
            }
        }

    }

    public  static void main(String []args) {
        //int a[]={1,3,-1,2,-1,-1,-1};
        int a[]={3,1,-1,-1,4,2,-1,-1,-1};

        TreeNode root =TreeNode.constructTree(a);
        TreeNode.displayTree(root);
        recover(root);
        TreeNode.displayTree(root);
    }


    static class Pair{
        TreeNode min=new TreeNode();
        TreeNode max=new TreeNode();
        boolean swap;

        TreeNode pmin=new TreeNode();
        TreeNode pmax=new TreeNode();
        Pair(){
            this.min.val=Integer.MAX_VALUE;
            this.max.val=Integer.MIN_VALUE;
            this.swap=false;
        }
    }

    public static Pair pair=new Pair();

    public static void recover(TreeNode node){

        if(node==null){
            return;
        }

        int p=node.val;
        int cl=pair.min==null?-1:pair.min.val;
        int cr=pair.max==null?-1:pair.max.val;
        System.out.println(node.val+" min : "+cl+" max: "+cr);
        TreeNode pmx=pair.max;
        pair.max= node.left!=null  && node.left.val > pair.max.val
                ? node.left : pair.max;
        if(pmx!=pair.max){
            pair.pmax=node;
        }
        TreeNode pmi=pair.max;
        pair.min= node.right!=null && node.right.val < pair.min.val
                ? node.right : pair.min;
        if(pmi!=pair.min){
            pair.pmin=node;
        }

        System.out.println(node.val+" min : "+pair.min.val+" max: "+pair.max.val);


        recover(node.left);
        recover(node.right);

        if(pair.swap==false && pair.max.val !=Integer.MIN_VALUE &&  (node.val < pair.max.val && node.val <= pair.pmax.val)){
            int val=node.val;
            node.val=pair.max.val;
            pair.max.val=val;

        }


        if(pair.swap==false && pair.min.val !=Integer.MAX_VALUE  && (node.val > pair.min.val && node.val >= pair.pmin.val)){
            int val=node.val;
            node.val=pair.min.val;
            pair.min.val=val;

        }

    }
}