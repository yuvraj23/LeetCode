public class RotateFunction {

    public  static void main(String []args){
        int nums[]={4,3,2,6};
        System.out.println(maxRotateFunction(nums));
    }

    public  static int maxRotateFunction(int[] nums) {
        int sum=0;
        int maxSum=0;
        for(int i=0;i<nums.length;i++){
            sum=rotateAndCalculation(i,nums);
            if(sum>maxSum){
                maxSum=sum;
            }
        }
        return maxSum;
    }

    public static int rotateAndCalculation(int r,int []nums){
        int reverseRotate=nums.length-r;
        int sum=0;
        // backward calculation
        int i=0;
        for(;reverseRotate<nums.length;reverseRotate++){
            sum+=(i*nums[reverseRotate]);
            i++;
        }

        // forward calculation
        for(int j=0;j<nums.length-r;j++){
            sum+=(i*nums[j]);
            i++;
        }

        return sum;
    }
}
