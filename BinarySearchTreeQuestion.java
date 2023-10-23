import java.util.*;

public class BinarySearchTreeQuestion {
    static class Pair{
        TreeNode node;
        int state;
        Pair(TreeNode n,int s){
            this.node=n;
            this.state=s;
        }

    }
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
    static class Pair2{
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        boolean isNodeBST=true;
    }


    public static void main(String []args){

        int a[]={5,4,3,-1,-1,-1,8,6,-1,-1,9,-1,-1};

        TreeNode root =constructTree(a);
        displayTree(root);
        tsp2(root,12);




    }

    static class IPair{
        int state;
        TreeNode node;
        IPair(TreeNode node){
            this.state=0;
            this.node=node;
        }
    }
    public static void tsp2(TreeNode node,int tg){
        Stack<IPair> in=new Stack();
        IPair inr=new IPair(node);
        in.push(inr);

        Stack<IPair> rv=new Stack();
        IPair rpr=new IPair(node);
        rv.push(rpr);

        TreeNode left=preInOrder(in);
        TreeNode right=postInOrder(rv);

        while(left.val<right.val){

            if(left.val +  right.val > tg){
                left=preInOrder(in);
            } else if(left.val +  right.val < tg){
                right=postInOrder(rv);
            }else{
                System.out.println(left.val+" + "+right.val+" = "+tg);
                left=preInOrder(in);
                right=postInOrder(rv);
            }
        }
    }
    public static TreeNode preInOrder(Stack<IPair> st){
        while(st.size()>0){
            IPair temp=st.peek();
            if(temp.state==0){
                if(temp.node.left!=null){
                    IPair nip=new IPair(temp.node.left);
                    st.push(nip);
                }
                temp.state++;
            }else if(temp.state==1){
                if(temp.node.right!=null){
                    IPair nip=new IPair(temp.node.right);
                    st.push(nip);
                }
                temp.state++;
                return temp.node;
            }else{
                st.pop();
            }

        }

        return null;
    }
    public static TreeNode postInOrder(Stack<IPair> st) {
        while (st.size() > 0) {
            IPair temp = st.peek();
            if (temp.state == 0) {
                if (temp.node.right != null) {
                    IPair nip = new IPair(temp.node.right);
                    st.push(nip);
                }
                temp.state++;
            } else if (temp.state == 1) {
                if (temp.node.left != null) {
                    IPair nip = new IPair(temp.node.left);
                    st.push(nip);
                }
                temp.state++;
                return temp.node;
            } else {
                st.pop();
            }
        }
        return null;
    }


    public static void  tsp(TreeNode node,TreeNode root,int sum){

        if(node==null) {
            return;
        }
        tsp(node.left,root,sum);
        int comp=(sum)-node.val;

        if(comp<node.val){
            boolean flag=find(root,comp);
            if(flag){
                System.out.println(node.val+ " + "+comp+" = "+sum);
            }
        }

        tsp(node.right,root,sum);
    }

    public static boolean find(TreeNode node,int data){
        if(node==null){
            return false;
        }
        if(node.val==data){
            return true;
        }

        if(data>node.val){
            return find(node.right,data);
        }else{
            return find(node.left,data);
        }
    }

    public static TreeNode maxInTree(TreeNode node){
        TreeNode maxNode=node;
        if(node.right!=null){
            maxNode=maxInTree(node.right);
        }
        return maxNode;
    }

    public static TreeNode remove(TreeNode node,int data){
        if(node.val==data){
            if(node.left==null && node.right==null){
                return null;
            }else if(node.left==null && node.right!=null){
                node=node.right;
                return node;
            }else if(node.right==null && node.left!=null){
                node=node.left;
                return node;
            }else{
                TreeNode maxNode=maxInTree(node.left);
                node.val=maxNode.val;
                node.left=remove(node.left,maxNode.val);
                return node;
            }

        }else{
            if(data>node.val){
                node.right=remove(node.right,data);
            }else{
                node.left=remove(node.left,data);
            }
        }
        return node;
    }

    public static TreeNode  add(TreeNode node,int data){
        if(node==null){
            TreeNode n=new TreeNode(data,null,null);
            return n;
        }else if(data>node.val){
            node.right=add(node.right,data);
            return node;
        }else if(data<node.val){
            node.left=add(node.left,data);
            return node;
        }else{
            return node;
        }


    }


















//Kth Largest Element in an Array
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue();
        for(int i=0;i<k;i++){
            pq.add(nums[i]);
        }

        for(int i=k;i<nums.length;i++){
            if(nums[i] > pq.peek()){
                pq.remove();
                pq.add(nums[i]);
            }
        }

        return pq.remove();

    }
    public static  boolean isValidBST(TreeNode root) {
        Pair2 p=helper(root);
        return p.isNodeBST;

    }



    public static  Pair2 helper(TreeNode node){
        if(node == null){
            Pair2 cp=new Pair2();
            return cp;
        }

        Pair2 lc=helper(node.left);
        Pair2 rc=helper(node.right);
        Pair2 mp=new Pair2();

        mp.min =Math.min(node.val,Math.min(lc.min,rc.min));
        mp.max =Math.max(node.val,Math.max(lc.max,rc.max));

        boolean isNodeBst= node.val > lc.max &&  node.val < rc.min;
        mp.isNodeBST=(lc.isNodeBST==true) &&  (rc.isNodeBST==true) && isNodeBst==true;
        return mp;
    }



    //Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        Map<Integer,Boolean> map1=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map1.put(nums[i],true);
        }

        for(Integer i:map1.keySet()){
            if(map1.containsKey(i-1)){
                map1.put(i,false);
            }
        }
        Map<Integer,Integer> map2=new HashMap<>();
        int maxKey=-1;
        int maxSize=1;
        for(Integer i:map1.keySet()){
            if(map1.get(i)==true){
                int val=i;
                int initVal=val;
                int count=1;
                while(true){

                    if(map1.containsKey(val+1)){
                        map2.put(initVal,val+1);
                        if(count>=maxSize){
                            maxSize=count+1;
                            maxKey=initVal;
                        }
                        count+=1;
                        val+=1;
                    }else{
                        break;
                    }

                }
            }
        }


        return maxSize;

    }

    //Intersection of Two Arrays
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map=new HashMap<>();
        ArrayList<Integer> ans=new ArrayList<>();

        for(int i=0;i<nums1.length;i++){
            if(map.containsKey(nums1[i])){
                int v=map.get(nums1[i])+1;
                map.put(nums1[i],v);
            }else{
                map.put(nums1[i],1);
            }
        }

        for(int i=0;i<nums2.length;i++){
            if(map.containsKey(nums2[i])==true && map.get(nums2[i])>0 ){
                int val=map.get(nums2[i]);
                ans.add(nums2[i]);
                map.put(nums2[i],val-1);
            }

        }

        int ans2[]=new int[ans.size()];
        int j=0;
        for(Integer i:ans){
            ans2[j]=i;
            j++;
        }

        return ans2;
    }






}
