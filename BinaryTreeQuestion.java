import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreeQuestion {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v,TreeNode l,TreeNode r){
            this.val=v;
            this.left=l;
            this.right=r;
        }
    }
    public static void main(String args[]){
        int a[]={50,25,12,-1,-1,37,30,-1,-1,-1,75,62,-1,70,-1,-1,87,-1,-1};
        TreeNode root=constructTree(a);
        //BinaryTreeMetaData md=new BinaryTreeMetaData(root);
        //System.out.println(findRootToNodePath(root,70));
        //printKLevelsDown(root,0);
        display(root);


    }

    public static void printKLevelsDown(TreeNode node,int k){
        if(node ==  null || k<0){
            return;
        }
        if(k==0){
            System.out.print(node.val+" , ");
            return;
        }
        printKLevelsDown(node.left,k-1);
        printKLevelsDown(node.right,k-1);
    }

    public static ArrayList<Integer> findRootToNodePath(TreeNode node,int fv){
        if(node ==  null){
            return new ArrayList<Integer>();
        }

        if(node.val==fv){
            ArrayList<Integer> ar=new ArrayList<>();
            ar.add(node.val);
            return ar;
        }

        ArrayList<Integer> lp=findRootToNodePath(node.left,fv);
        if(lp.size()>0){
            lp.add(node.val);
            return lp;
        }
        ArrayList<Integer> rp=findRootToNodePath(node.right,fv);
        if(rp.size()>0){
            rp.add(node.val);
            return rp;
        }

        return new ArrayList<Integer>();
    }
    public static  class BinaryTreeMetaData{
        int size;
        int sum;
        int max;
        int min;
        int height;
        BinaryTreeMetaData(TreeNode node){
            this.size=size(node);
            this.sum=sum(node);
            this.max=maximum(node);
            this.min=minimum(node);
            this.height=height(node);
            System.out.println("size : "+size+"\nsum : "+sum+"\nmax : "+max+"\nmin : "+min+"\nheight : "+height);
        }

    }
    public static int size(TreeNode node){
        if(node == null){
            return 0;
        }
        int ls=size(node.left);
        int rs=size(node.right);
        return ls+rs+1;
    }
    public static int sum(TreeNode node){
        if(node == null){
            return 0;
        }
        int ls=sum(node.left);
        int rs=sum(node.right);
        return ls+rs+node.val;
    }
    public static int maximum(TreeNode node){
        if(node == null){
            return Integer.MIN_VALUE;
        }

        int lmax=maximum(node.left);
        int rmax=maximum(node.right);
        return Math.max(Math.max(lmax,rmax),node.val);

    }
    public static int minimum(TreeNode node){
        if(node == null){
            return Integer.MAX_VALUE;
        }
        int lmax=minimum(node.left);
        int rmax=minimum(node.right);
        return Math.min(Math.min(lmax,rmax),node.val);
    }
    public static int height(TreeNode node){
        if(node == null){
            return 0;
        }
        int lh=height(node.left);
        int rh=height(node.right);
        return Math.max(lh,rh)+1;
    }

    public static int  maxLevelSum(TreeNode root) {
        ArrayDeque<TreeNode> q=new ArrayDeque<>();
        ArrayDeque<TreeNode> cq=new ArrayDeque<>();
        q.add(root);
        int level=Integer.MAX_VALUE;
        int maxSum=Integer.MIN_VALUE;
        int max=0;
        int l=1;
        while(q.size()>0){
            TreeNode temp=q.remove();
            max+=temp.val;

            if(temp.left!=null){
                cq.add(temp.left);
            }

            if(temp.right!=null){
                cq.add(temp.right);
            }

            if(q.size()==0){
                if(max>maxSum){
                    maxSum=max;
                    level=l;
                }
                max=0;
                l++;
                q=cq;
                cq=new ArrayDeque<>();
            }
        }
        return level;
    }

    static class Pair{
        TreeNode node;
        int state;
        Pair(TreeNode n,int s){
            this.node=n;
            this.state=s;
        }

    }
    public static TreeNode constructTree(int a[]){
        Stack<Pair> st=new Stack();
        TreeNode root = new TreeNode(a[0],null,null);
        Pair p=new Pair(root,1);
        st.add(p);
        int ind=1;
        //{50-0,25-1,12-2,-1-3,-1-4,37-5,30-6,-1-7,-1-8,-1-9,75-10,62-11,-1-12,70-13,-1-14,-1-15,87-16,-1-17,-1-18};
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

}
