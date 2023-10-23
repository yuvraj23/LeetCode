public class Rotate {
    public static  void main(String []args){
        int k=1;
        int number=647743;

        // get number of digits
        int temp=number;
        int digit=0;
        while(temp>0){
            temp=temp/10;
            digit++;
        }

        //check for negative case
        int kRem=k%digit;
        if(kRem < 0){
            k = digit+kRem;
        }

        // get remainder & quiotent
        int div=(int)Math.pow(10,k);
        int mul=(int)Math.pow(10,(digit-k));

        // do reverse
        int d=number%div;
        int q=number/div;
        int ans=d*mul+q;
        System.out.println(ans);
    }
}
