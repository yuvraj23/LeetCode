import java.util.Arrays;

public class TwoDArrayQuestion {
    public static void main(String []args){
       /* Q1
        int a[][]={{12,8,4},{3,17,14},{9,8,10}};
        int b[][]={{5,19,3},{6,15,9},{7,8,16}};*/
        //matrixMultplication(a,b);

        // Q2
        // Rotate by 90 degree
/*        int a[][]={{12,8,4},{3,17,14},{9,8,10}};
        rotateBy90(a);*/

        // Q3 spiral display
/*        int a[][]={{11,12,13,14,100},{21,22,23,24,101},{31,32,33,34,102},{41,42,43,44,103}};
        spiralDisplay(a);*/

        // q4 wave traversal in 2d matrix
/*        int a[][]={{11,12,13,14},{21,22,23,24},{31,32,33,34},{41,42,43,44}};
        printWaveTraversal(a);*/

        // Q5 exit point of maze
//        int a[][]={{0,0,1,0},{0,0,0,0},{0,0,0,0},{1,0,1,0}};
//        findExitPoint(a);

        // Q6 Ring Rotate
/*        int a[][]={
             //  0  1  2   3  4  5  6
                {11,12,13,14,15,16,17}, //0
                {21,22,23,24,25,26,27},// 1
                {31,32,33,34,35,36,37},//2
                {41,42,43,44,45,46,47}//3
        };
        ringRotate(a);*/

       // Q7 Saddle Price
        int [][]a= {
                {11, 12 ,13 ,14},
                {21, 22 ,23 ,24},
                {31 ,32 ,33 ,34},
                {41, 42 ,43 ,44},
        };
        saddlePoint(a);




    }
//
//    {11, 12 ,13 ,14},
//    {21, 22 ,23 ,24},
//    {31 ,32 ,33 ,34},
//    {41, 42 ,43 ,44},

    public static void saddlePoint(int a[][]){
        for(int i=0;i<a.length;i++){
            int ps=a[i][0];
            // find min in col
            int psind=0;
            for(int j=1;j<a.length;j++){
                if(a[i][j]<ps){
                    ps=a[i][j];
                    psind=j;
                }
            }
            // find max in row
            int j=psind;
            for(;j<a.length;j++){
                if(ps<a[j][psind]){
                    break;
                }
            }
            if(j==a.length){
                System.out.print(ps);
                break;
            }

        }

    }




    public static void ringRotate(int a[][]){
        getShell(a,1,2);

    }

    public static void getShell(int a[][],int s,int r){
        System.out.println(Arrays.deepToString(a));
        int count=0;
        int minCol=s; //1
        int minRow=s; ///1
        int maxCol=a[0].length-s-1; // 5
        int maxRow=a.length-s-1; // 2
        int total= 2*(maxCol-minCol) + 2*(maxRow-minRow);   //
        int res[]=new int[total];

        while (count<total){
            //left vertical wall
            for(int i=minRow;i<=maxRow &&  count<total;i++){
                res[count]=a[i][minRow];
                count++;
            }
            minCol++;

            //bottom horizontal wall
            for(int i=minCol;i<=maxCol &&  count<total;i++){
                res[count]=a[maxRow][i];
                count++;
            }
           maxRow--;

            //right vertical wall
            for(int i=maxRow;i>=minRow &&  count<total;i--){
                res[count]=a[i][maxCol];
                count++;
            }
            maxCol--;

            //top  horizontal wall
            for(int i=maxCol;i>=minCol &&  count<total;i--){
                res[count]=a[minRow][i];
                count++;
            }
            minRow++;

        }


        rotate(res,r);
        setShell(a,s,res);
        System.out.println(Arrays.deepToString(a));

    }



    public static void setShell(int a[][],int s,int res[]){
        int count=0;
        int minCol=s; //1
        int minRow=s; ///1
        int maxCol=a[0].length-s-1; // 5
        int maxRow=a.length-s-1; // 2
        int total= 2*(maxCol-minCol) + 2*(maxRow-minRow);   //

        while (count<total){
            //left vertical wall
            for(int i=minRow;i<=maxRow &&  count<total;i++){
                a[i][minRow]=res[count];
                count++;
            }
            minCol++;

            //bottom horizontal wall
            for(int i=minCol;i<=maxCol &&  count<total;i++){
                a[maxRow][i]=res[count];
                count++;
            }
            maxRow--;

            //right vertical wall
            for(int i=maxRow;i>=minRow &&  count<total;i--){
                a[i][maxCol]=res[count];
                count++;
            }
            maxCol--;

            //top  horizontal wall
            for(int i=maxCol;i>=minCol &&  count<total;i--){
                a[minRow][i]=res[count];
                count++;
            }
            minRow++;

        }

    }

    public static void rotate(int a[],int r){
        r=r%a.length;
        if(r<0){
            r=a.length-r;
        }

        swap(a,0,a.length-1-r);
        swap(a,a.length-r,a.length-1);
        swap(a,0,a.length-1);
    }

    public static void swap(int a[],int l,int r){
        while(l<r){
            int temp=a[l];
            a[l]=a[r];
            a[r]=temp;
            l++;
            r--;
        }
    }




    public static void findExitPoint(int a[][]){

        int i=0,j=0;
        int d=0;

        while((i<a.length && j< a[0].length) && (i>=0 && j>=0)){
            if(a[i][j]!=0){
                d++;
            }

            if((d%4)==0){
                j++;
            }else if((d%4)==1){
                i++;
            }else if((d%4)==2){
                j--;
            }else{
                i--;
            }

        }
        System.out.print(i+" , "+j);
    }

    public static void printWaveTraversal(int a[][]){
        for(int i=0;i<a[0].length;i++){
            for(int j=0;j<a.length;j++){
                if(i%2==0){
                    System.out.print(a[j][i]+" , ");
                }else{
                    int col=a[i].length-j-1;
                    System.out.print(a[a.length-j-1][i]+" , ");
                }
            }
            System.out.println();
        }
    }


    public  static  void spiralDisplay(int a[][]){
        int count=0;
        int minRow=0;
        int minCol=0;
        int maxRow=a.length-1;
        int maxCol=a[0].length-1;
        int totalEle=a[0].length*a.length;
        int ansMat[][]=new int[maxRow][maxCol];


        while(count < totalEle){
            // printing left vertical wall
            for(int i=minRow;i<=maxRow && count<totalEle;i++){
              System.out.print(a[i][minRow]+" , ");
                count++;
            }
            minCol++;
            System.out.println();

            // printing bottom horizontal wall
            for(int i=minCol;i<=maxCol && count<totalEle;i++){
                System.out.print( a[maxRow][i]+" , ");
                count++;
            }
            maxRow--;
            System.out.println();

            // printing right vertical wall
            for(int i=maxRow;i>=minRow && count<totalEle;i--){
                System.out.print(a[i][maxCol]+" , ");
                count++;
            }
            maxCol--;
            System.out.println();

            // printing top horizontal wall
            for(int i=maxCol;i>=minCol && count<totalEle;i--) {
                System.out.print(a[minRow][i] + " , ");
                count++;
            }
            minRow++;
            System.out.println();

        }

    }


    public static void rotateBy90(int a[][]){
        // do transpose of matrix
        System.out.println(Arrays.deepToString(a));
        for(int i=0;i<a.length;i++){
            for(int j=i;j<a.length;j++){
                int temp=a[i][j];
                a[i][j]=a[j][i];
                a[j][i]=temp;
            }
        }
        System.out.println(Arrays.deepToString(a));

        // swapping logic
        for(int i=0;i<a.length;i++){
            int left=0;
            int right=a.length-1;
            while(left<=right){
                int temp=a[i][left];
                a[i][left]=a[i][right];
                a[i][right]=temp;
                left++;
                right--;
            }
        }
        System.out.println(Arrays.deepToString(a));
    }

    public static void matrixMultplication(int a[][],int b[][]){
        if(a[0].length==b.length){
            System.out.println("Invalid inputs");
        }
        int ans[][]=new int[a.length][a[0].length];

        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                int val=0;
                for(int k=0;k<a.length;k++){
                   val+=(a[i][k]*b[k][j]);
                }
                ans[i][j]=val;
            }
        }

        System.out.println(Arrays.deepToString(ans));
    }


}
