import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DynamicProgramming {
    public static void main(String []args){

       int a[]={15,10,9,7,5,2,1};
        maxSumAfterPartitioning(a,3);



    }


    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int m=0;

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr));
        return m;
    }


    public static int countSquares(int[][] matrix) {


        int dp[][]=new int[matrix.length][matrix[0].length];
        int count=0;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    continue;
                }
                if(i==0 || j==0){
                    dp[i][j]=matrix[i][j];
                    count+=matrix[i][j];
                }else{
                    int v1=matrix[i-1][j];
                    int v2=matrix[i-1][j-1];
                    int v3=matrix[i][j-1];
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])+1;
                    count+=dp[i][j];
                }
            }
        }

        System.out.println(Arrays.deepToString(dp));
        return count;
    }


    public  static int countVowelStrings(int n) {
        int ans=0;
        int dp[][]=new int[n+1][5];

        for(int i=0;i<5;i++){
            dp[0][i]=1;
        }

        for(int i=1;i<=n;i++){
            for(int j=0;j<5;j++){
                if(j==0){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=dp[i][j-1]+dp[i-1][j];
                }
            }
        }
        ans=dp[dp.length-1][dp[0].length-1];
        System.out.println(ans);

        return ans;
    }

    public static List<String> countVowelStrings(int i,List<String> que){
        if(i==que.size()){
            List<String> r=new ArrayList<>();
            return r;
        }

        String ch=que.get(i);
        List<String> ans=new ArrayList<>();

        for(int j=i;j<que.size();j++){
            ans.add(ch+que.get(j));
        }

        List<String> res=countVowelStrings(i+1,que);
        ans.addAll(res);
        return ans;
    }

    public static void climbStairsWithJumps(int n){
        int dp[]=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=i;j>=0;j--){
                dp[i]+=dp[j];
            }
        }
        System.out.println(Arrays.toString(dp));

    }
    public static void ClimbStairs(int n){
        int dp[]=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            dp[i]=0;
            if(i-3>=0){
                dp[i]+=dp[i-3];
            }
            if(i-2>=0){
                dp[i]+=dp[i-2];
            }
            if(i-1>=0){
                dp[i]+=dp[i-1];
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    public static void FibonacciDP(){
        int n=10;
        int dp[]=new int[11];
        dp[0]=0;
        dp[1]=1;

        for(int i=2;i<dp.length;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        System.out.println(Arrays.toString(dp));
    }















    public static  int minCostClimbingStairs(int[] cost) {

        int dp[]=new int[cost.length+1];
        dp[cost.length]=0;
        for(int i=cost.length-1;i>=0;i--){
            int oneStep=cost[i];
            int twoStep=cost[i];

            if(i+1<dp.length){
                oneStep=dp[i+1];
            }
            if(i+2<dp.length){
                twoStep=dp[i+2];
            }

            long minVal=Math.min(cost[i]+oneStep,cost[i]+twoStep);
            dp[i]=(int)minVal;
        }
        return Math.min(dp[0],dp[1]) ;
    }

    public  static int maxProfit(int[] prices) {
        int maxProfit=0;
        int buyTime=prices[0];
        for(int i=1;i<prices.length;i++){

            if((prices[i]-buyTime)>maxProfit){
                maxProfit=prices[i]-buyTime;
            }
            if(prices[i]<buyTime){
                buyTime=prices[i];
            }
        }
        return maxProfit;
    }


    public static List<Integer> getRow(int rowIndex) {
        List<Integer> preList=new ArrayList<>();
        List<Integer> currList;
        preList.add(1);
        for(int i=0;i<=rowIndex;i++){
            currList=new ArrayList<>();
            for(int j=0;j<=i;j++){
                int n1=0;
                int n2=0;
                if(j-1>=0){
                    n1=preList.get(j-1);
                }
                if(j<preList.size()){
                    n2=preList.get(j);
                }
                n2=n1+n2;
                currList.add(n2);
            }
            preList=currList;
        }
        return preList;
    }

    public static  List<List<Integer>> generate(int numRows) {
        List<Integer> preList=new ArrayList<>();
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> currList;
        preList.add(1);
        for(int i=0;i<numRows;i++){
            currList=new ArrayList<>();
            for(int j=0;j<=i;j++){
                int n1=0;
                int n2=0;
                if(j-1>=0){
                    n1=preList.get(j-1);
                }
                if(j<preList.size()){
                    n2=preList.get(j);
                }
                n2=n1+n2;
                currList.add(n2);
            }
            preList=currList;
            result.add(currList);
        }
        return result;
    }
}
