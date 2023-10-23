import java.sql.Array;
import java.util.Arrays;

public class FunctionsAndArry {
    public static void main(String []args){
        //Q1
        //int a[]={3,1,0,7,5};
       // barChart(a);

        //Q2  Sum Of Two Arrays
       /* int a1[]={9,9,9};
        int a2[]={1};
        addTwoArray(a1,a2);*/

        //Q3  Difference Of Two Arrays
       /* int a1[]={2,6,7};
        int a2[]={1,0,0,0};
        differnceOfTwoArray(a1,a2);*/

        //Q4 reverse a array
        /*
        int a[]={1,2,3,4,5};
        reverse(a,101);*/


    /*    //Q5 Binary Search
        int a[]={10,20,30,40,50,60,70,80,90,100};
        binarySearch(a,70);*/

        //Q6 First and last index
  /*      int a[]={10,20,20,20,20,20,20,20,20,30,30,30,40,40,50,50,50,50,60};

        int fisrtIndex=binarySearchForFirstIndex(a,10);
        int lastIndex=binarySearchforLastIndex(a,10);
        System.out.println(20+" -> f="+fisrtIndex+" , s= "+lastIndex);*/


        // Q7
  /*      int a[]={10,20,20,20,20,20,20,20,20,30,30,30,40,40,50,50,50,50,60};
        //int a[]={10,20,30,40,50,60,70,80,90,100};
        ceilFloorOfficial(a,30);*/

        //Q8 inverted-bar-chart-official
//        int a[]={3,1,0,7,5};
//        invertedBarChartOfficial(a);


        // Q9 binary search in 2D array
        int [][]a={
                {0,1,2,3},       //0
                {4,5,6},      //1
                {7,8,9,10}        //2
        };

        binarySearch2d(a,0);


    }



    public static void  binarySearch2d(int a[][],int se){

        // let apply binary search by row wise

        int low=0,high=a.length;

        while((high>=low)){
            int mid=(low+high)/2;
            if(a[mid][a[low].length-1]<se){
                low=mid+1;
            }else if(a[mid][a[low].length-1]>se){
                high=mid-1;
            }else{
                System.out.print("found at : "+mid+" , "+mid);
                break;
            }
        }

        int row=low;

        // let apply binary search by col wise

        low=0;
        high=a[0].length-1;

        if(row-1>=0){
            binarySearchInCol(low,high,se,a,row-1);
        }
        binarySearchInCol(low,high,se,a,row);
        if(row+1<= a.length-1){
            binarySearchInCol(low,high,se,a,row+1);
        }
    }


    public static void binarySearchInCol(int low,int high,int se,int a[][],int row){
        while(low<=high ){
            int mid=(low+high)/2;
            if(a[row][mid]<se){
                low=mid+1;
            }else if(a[row][mid]>se){
                high=mid-1;
            }else{
                System.out.print("found at : "+row+" , "+mid);
                break;
            }
        }
    }









    public static void  invertedBarChartOfficial(int a[]){
        int max=maxInArray(a);
        for(int i=0;i<max;i++){
            for(int j=0;j<a.length;j++){
                if(a[j]>=i){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }

            System.out.println();
        }

    }










    public static void ceilFloorOfficial(int a[],int data){
        int left =0;
        int right=a.length;
        int mid=0;
        int floor=0;
        int ceil=0;

        while (left<=right){

            mid=(left+right)/2;
            if(a[mid]<data){
                left=mid+1;
                floor=mid;
            }else if(a[mid]>data){
                right=mid-1;
                ceil=mid;
            }else {
                left--;
                right--;
            }

        }
        System.out.println(a[floor]+" ,"+a[ceil]);

    }













    public  static int binarySearchForFirstIndex(int a[],int data){

        int left=0;
        int right=a.length;
        int fi=0;

        while (left<=right){
            int mid=(left+right)/2;
            if(data>a[mid]){
                left=mid+1;
            }else if(data<a[mid]){
                right=mid-1;
            }else{
                fi=mid;
                right=mid-1;

            }
        }

        return fi;

    }

    public  static int binarySearchforLastIndex(int a[],int data){

        int left=0;
        int right=a.length;
        int si=0;

        while (left<=right){
            int mid=(left+right)/2;
            if(data>a[mid]){
                left=mid+1;
            }else if(data<a[mid]){
                right=mid-1;
                si=right;
            }else{
               si=mid;
              left=mid+1;
            }
        }
        return si;

    }

    public  static void binarySearch(int a[],int data){

        int left=0;
        int right=a.length;

        while (left<right){
            int mid=(left+right)/2;
            if(data>a[mid]){
                left=mid+1;
            }else if(data<a[mid]){
                right=mid-1;
            }else{
                System.out.println(mid);
                break;
            }
        }

    }



    public static void reverse(int a[],int r){
        r=r%a.length;

        if(r<0){
            r=a.length-r;
        }
        int part1=a.length-r-1;
        int part2=r;

        System.out.println(Arrays.toString(a));
        swap(a,0,part1);
        swap(a,part1+1,a.length-1);
        swap(a,0,a.length-1);
        System.out.println(Arrays.toString(a));

    }

    public static void swap(int a[],int left,int right){

        while(left<=right){
            int temp=a[left];
            a[left]=a[right];
            a[right]=temp;
            left++;
            right--;
        }


    }

    public static void differnceOfTwoArray(int a1[],int a2[]){
        int i=a1.length-1;
        int j=a2.length-1;
        int max=(i>j?i:j);
        int ans[]=new int[max+1];

        int c=0;

        while(max>=0){
            int d1=0;
            int d2=0;

            if(i>=0){
                d1=a1[i];
            }

            if(j>=0){
                d2=a2[j];
            }

            int d=(d2-c)-d1;

            if(d<0){
                d=(d2+10)-d1-c;
                c=1;
            }else{
                c=0;
            }
            ans[max]=d;
            i--;
            j--;
            max--;

        }

        System.out.println(Arrays.toString(ans));


    }









    public static void addTwoArray(int a1[],int a2[]){
        int i=a1.length-1;
        int j=a2.length-1;

        int maxSize = (a1.length > a2.length) ? a1.length : a2.length;

        int resArray[]=new int[maxSize+1];
        int c=0;

        while(maxSize>=0 || c>0){

            // get a digit from both the array
            int d1=0;
            int d2=0;

            if(i>=0){
                d1=a1[i];
            }

            if(j>=0){
                d2=a2[j];
            }

            int d=d1+d2+c;
            if(d>9){
                c=d/10;
                d=(d%10);

            }else{
                c=0;
            }

            resArray[maxSize]=d;
            maxSize--;
            i--;
            j--;
        }
        System.out.println(Arrays.toString(resArray));
    }


    public static void barChart(int []a){
        // find max from the array
        int maxVal=maxInArray(a);

        for(int i=maxVal;i>0;i--){

            for(int j=0;j<a.length;j++){
                if(a[j]>=i){
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    public static int maxInArray(int []a){
        int maxVal=Integer.MIN_VALUE;
        for(int i=0;i<a.length;i++){
            if(a[i]>maxVal){
                maxVal=a[i];
            }
        }
        return  maxVal;
    }
}
