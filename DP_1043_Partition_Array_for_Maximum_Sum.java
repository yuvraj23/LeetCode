public class DP_1043_Partition_Array_for_Maximum_Sum {
    public static void main( String []args){
        int arr[]={1,15,7,9,2,5,10};
        System.out.println(maxSumAfterPartitioning(arr,3));
    }

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int dp[]=new int[arr.length];

        for(int i=0;i<arr.length;i++){
            int pairs=0;
            int best=Integer.MIN_VALUE;
            int max=Integer.MIN_VALUE;
            int not=0;
            for(int j=i;j>(i-k);j--){
                if(j<0){
                    break;
                }
                not++;
                max=Math.max(max,arr[j]);
                pairs=max*not;
                if(j==0){
                    int val=pairs+0;
                    best=Math.max(val,best);
                }else if(j>0){
                    int val=pairs+dp[j-1];
                    best=Math.max(val,best);
                }
            }
            dp[i]=best;
        }
        return dp[dp.length-1];
    }
}
