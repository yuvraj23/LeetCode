import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Sorting {
    public  static void main(String []args){

       // int a[]={5,7,-2,4,1,3,0};
        //bubbleSort((a));
        //insertionSort(a);
        //selectionSort(a);

       //System.out.println(Arrays.toString( mergeSort(a,0,a.length-1) ));
       // int a[]={12, 3, 4, 1, 6, 9};
       // targetSumTriplet(a,24);
       // int a[]={0, 1, 1, 0, 1, 1, 0, 0, 1};
       // sortArrayO1(a);
       // int a[]={1,2,3,4,5,6};
       // sortArrayEvenOdd(a);
       // int a[]={0, 1, 2, 0, 2, 1, 0, 2, 1};
       // sort012(a);
       // printMazePathWithJump(0,0,3,3,"");


       //System.out.print(Arrays.toString( mergeSort(a,0,a.length-1)));
        //int a[]={7,-2,4,1,3,1};
        //int pivot=3;
       // partitionAnArray(a,a[a.length-1]);
//        quickSort(a,0,a.length-1);
//       System.out.print(Arrays.toString(a));

       // int a[]={5,7,2,4,1,3,2,5,7,9,1,2,3,5,3,2,1,9,8};
       // countSort(a);
        //int a[]={121, 432, 564, 23, 1, 45, 788,131,342,99,102};
        //radixSort(a);

       // int a[]={10, 20,30, 40, 50};
       // targetSumSubsets(a,0,"",60,0);
      //  int board[][]=new int[4][4];
       // nQueens(board,0);











    }

    public static void  nQueens(int b[][],int row){

        if(row==b.length){
            System.out.println(Arrays.deepToString(b));
            return;
        }

        for(int i=0;i<b.length;i++){
            if(isSafe(b,row,i)){
                b[row][i]=1;
                nQueens(b,row+1);
                b[row][i]=0;
            }
        }
    }


    public static boolean  isSafe(int b[][],int row,int col){

        // checking for upward direction
        for(int i=row-1;i>=0;i--){
            if(b[i][col]==1){
                return false;
            }
        }

        // checking for right diagonal
        int c=col+1;
        for(int i=row-1;i>=0 &&  c<b.length;i--){
            if(b[i][c]==1){
                return false;
            }
            c++;
        }

        // checking for left diagonal
        c=col-1;
        for(int i=row-1; i>=0 &&  c>=0;i--){
            if(b[i][c]==1){
                return false;
            }
            c--;
        }

        return true;
    }

    public static void targetSumSubsets(int a[],int i,String ans,int sum,int rs){
        if(rs==sum){
            System.out.println(ans);
            return;
        }
        if(i==a.length){
            return;
        }
        targetSumSubsets(a,i+1,ans+a[i]+" , ",sum,rs+a[i]);
        targetSumSubsets(a,i+1,ans,sum,rs);
    }


    public static void floodFill(int a[][],int sr,int sc,int dr,int dc,int visited[][],String path){

        if(sr>dr || sc > dc || sc<0 || sr < 0 || a[sr][sc]==1 || visited[sr][sc]==1){
            return;
        }
        if(sr==dr && sc == dc){
            System.out.println(path);
            return;
        }
        visited[sr][sc]=1;
        floodFill(a,sr,sc+1,dr,dc,visited,path+"r");
        floodFill(a,sr+1,sc,dr,dc,visited,path+"d");
        floodFill(a,sr,sc-1,dr,dc,visited,path+"l");
        floodFill(a,sr-1,sc,dr,dc,visited,path+"t");

        visited[sr][sc]=0;
    }
    public  static void radixSort(int a[]){
        System.out.println("Before sorting : "+Arrays.toString(a));
        int place=1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<a.length;i++){
            if(a[i]>max){
                max=a[i];
            }
        }

        while(max/place >0){
            countSort2(a,place);
            place*=10;
        }

        System.out.println("After sorting : "+Arrays.toString(a));

    }

    public static void countSort2(int a[],int place){

        int fa[]=new int[10];

        //fill the frequency
        for(int i=0;i<a.length;i++){
            int val=(a[i]/place)%10;
            fa[val]++;
        }

        //fill the prefix sum
        for(int i=1;i<fa.length;i++){
           fa[i]+=fa[i-1];
        }

        int ans[]=new int[a.length];
        //fill the ans array
        for(int i=a.length-1;i>=0;i--){
            int val=(a[i]/place)%10;
            int pos=fa[val];
            ans[pos-1]=a[i];
            fa[val]--;
        }

        for(int i=0;i<ans.length;i++){
            a[i]=ans[i];
        }

    }







    public static void countSort(int a[]){
        // find min and max
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

        for(int i=0;i<a.length;i++){
            if(a[i]>max){
                max=a[i];
            }
            if(a[i]<min){
                min=a[i];
            }
        }

        // frequency array
        int freq[]=new int[max-min+1];
        for(int i=0;i<a.length;i++){
          freq[a[i]-min]+=1;
        }

        System.out.println(Arrays.toString(freq));
        // frequency sum array
        for(int i=1;i<freq.length;i++){
            freq[i]+=freq[i-1];
        }

        // now fill ans array
        /*
        by iterating over a, take a elemnt find that element in frequency array
        and put a[i] in freq[a[i]-1 in  ans array and decrease freq[a[i]-1]
         */
        int ans[]=new int[a.length];
        for(int i=a.length-1;i>=0;i--){
            ans[freq[a[i]-1]-1]=a[i];
            freq[a[i]-1]--;

        }

        System.out.println(Arrays.toString(ans));

    }

    public static void quickSort(int a[],int low,int high){
        if(low>=high){
            return;
        }
       int pi= partitionAnArray(a,low,high,a[high]);
        quickSort(a,low,pi-1);
        quickSort(a,pi+1,high);
    }

    public static int  partitionAnArray(int a[],int low,int high,int pivot){

        int i=low,j=low;
        while(i<=high){
            if(a[i]<=pivot){
                swap(a,i,j);
                j++;
            }
            i++;
        }
       // System.out.print(Arrays.toString(a));
        return j-1;
    }

    public static  int []mergeSort(int a[],int low,int high){
        if(low==high){
            int oneEle[]={a[low]};
            return oneEle;
        }
        int mid=((low+high)/2);
        int fsr[]=mergeSort(a,low,mid);
        int ssr[]=mergeSort(a,mid+1,high);
        int ans[]=merge2SortedArray(fsr,ssr);
        return ans;
    }



    public static int[]  merge2SortedArray(int a[],int b[]){
        int i=0,j=0,k=0;

        int ans[]=new int[a.length+b.length];

        while(i<a.length  && j < b.length){

            if(a[i]>b[j]){
                ans[k]=b[j];
                j++;
            }else{
                ans[k]=a[i];
                i++;
            }
            k++;

        }

        if(i<a.length){
            while(i<a.length){
                ans[k]=a[i];
                i++;
                k++;
            }
        }

        if(j<b.length){
            while(j<b.length){
                ans[k]=b[j];
                j++;
                k++;
            }
        }

        //System.out.print(Arrays.toString(ans));
        return ans;
    }












    public static void printMazePathWithJump(int r,int c,int dr,int dc,String ans){
        if(r>dr-1 || c>dc-1){
            return;
        }
        if(r==dr-1 && c==dc-1){
            System.out.println(ans);
            return;
        }

        for(int i=r+1;i<dr;i++){
            printMazePathWithJump(i,c,dr,dc,ans+"v"+(i));
        }

        for(int i=c+1;i<dc;i++){
            printMazePathWithJump(r,i,dr,dc,ans+"h"+(i));
        }

        return;

    }





    public static void sort012(int a[]){
        System.out.println("un-sorted array : => "+Arrays.toString(a));
        int i=0,j=0,k=a.length-1;
        while (i<k){
            //if we get 1 then swap i and j
            if(a[i]==0){
                swap(a,i,j);
                i++;
                j++;
            }else if(a[i]==2){
                swap(a,i,k);
                k--;
            }else{
                i++;
            }
        }

        System.out.println("sorted array : => "+Arrays.toString(a));
    }

    public static void sortArrayEvenOdd(int a[]){
        System.out.println("un-sorted array : => "+Arrays.toString(a));
        int i=0,j=0;
        while(i<a.length){
            if(a[i]%2==0){
                swap(a,i,j);
                i++;
                j++;
            }else{
                i++;
            }
        }
        System.out.println("sorted array : => "+Arrays.toString(a));

    }
    public static void sortArrayO1(int a[]){
        System.out.println("un-sorted array : => "+Arrays.toString(a));
        int i=0,j=0;
        while(i<a.length){
            if(a[i]==0){
               swap(a,i,j);
               i++;
               j++;
            }else{
                i++;
            }
        }
        System.out.println("sorted array : => "+Arrays.toString(a));

    }
    public static void targetSumTriplet(int a[],int target){
        Arrays.sort(a);
        for(int i=0;i<a.length;i++){
            int secTag=target-a[i];
            int low=i+1;
            int high=a.length-1;
            targetSumPairHelper(a,target,secTag,low,high);
        }

    }
    public static void targetSumPairHelper(int a[],int target,int secTag,int low,int high){

        while(low<high){
            if(a[low]+a[high]==secTag){
                System.out.println(target-secTag+" , "+a[low]+" , "+a[high]);
            }

            if(a[low]+a[high]>target){
                high--;
            }else {
                low++;
            }
        }
    }

    public static void targetSumPair(int a[],int target){
        Arrays.sort(a);
        int low=0;
        int high=a.length-1;

        while(low<high){
            if(a[low]+a[high]==target){
                System.out.println(a[low]+" , "+a[high]);
            }

            if(a[low]+a[high]>target){
                high--;
            }else {
                low++;
            }
        }
    }
//    public static int[] mergeSort(int a[],int low,int high){
//        if(low==high){
//           int ans[]=new int[1];
//           ans[0]=a[low];
//           return  ans;
//        }
//
//        int mid=(low+high)/2;
//        int fsa[]=mergeSort(a,low,mid);
//        int ssa[]=mergeSort(a,mid+1,high);
//        int mergeArray[]=mergeTwoSortedArrays(fsa,ssa);
//        return mergeArray;
//    }



    public static int[] mergeTwoSortedArrays(int a[],int b[]){

        int ai=0,bi=0;
        int ans[]=new int[a.length+b.length];
        int i=0;
        while(ai <a.length && bi < b.length){

            if(a[ai]>b[bi]){
                ans[i]=b[bi];
                bi++;
            }else{
                ans[i]=a[ai];
                ai++;
            }
            i++;
        }

        if(ai <a.length){
            for(int j=ai;j<a.length;j++){
                ans[i]=a[j];
                i++;
            }
        }
        if(bi <b.length){
            for(int j=bi;j<b.length;j++){
                ans[i]=b[j];
                i++;
            }
        }


        return ans;

    }


    public static void selectionSort(int a[]){
        System.out.println("un-sorted array"+Arrays.toString(a));
        for(int i=0;i<a.length;i++){
            int minInd=i;
            for(int j=i+1;j<a.length;j++){
                if(a[minInd]>a[j]){
                    minInd=j;
                }
            }
            int temp=a[minInd];
            a[minInd]=a[i];
            a[i]=temp;

        }
        System.out.println("sorted array"+Arrays.toString(a));
    }
    public static void insertionSort(int a[]){
        System.out.println("un-sorted array"+Arrays.toString(a));
        for(int i=1;i<a.length;i++){
            int temp=a[i];
           int j=i-1;
           while(j>=0 && a[j]>temp){
               a[j+1]=a[j];
               j--;
           }

            a[j+1]=temp;
        }
        System.out.println("sorted array"+Arrays.toString(a));
    }
    public static void bubbleSort(int a[]){
        System.out.println("un-sorted array"+Arrays.toString(a));
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]){
                    swap(a,j,j+1);
                }
            }
            System.out.println(Arrays.toString(a));
        }
        System.out.println("sorted array"+Arrays.toString(a));
    }

    public static void swap(int a[],int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
