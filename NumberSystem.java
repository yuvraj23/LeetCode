public class NumberSystem {
    public static void main(String []args){
        //anyBaseAddition(136,636,8);
        //anyBaseSubtraction(25430,4077,8);
        anyBaseMultiplication(325,14,8);
    }
    public  static int decimalToanyBase(int num,int base){
        //conversion logic
        int pow=1;
        int destiVal=0;
        while(num>0) {
            int rem = num % base;
            destiVal += (rem * pow);
            pow *= 10;
            num=num/8;
        }

        return destiVal;
        //anyBaseToDecimal(1172,8,10);
    }

    public static void  anyBaseToDecimal(int num,int sourceBase,int destiBase){
        int pow=1;
        int destiVal=0;
        while(num>0){
            int rem=num%destiBase;
            destiVal+=(rem*pow);
            pow*=sourceBase;
            num=num/destiBase;
        }
        System.out.println(destiVal);
    }

    public static int anyBaseAddition(int number1,int number2,int base){
        int total=0;
        int carry=0;
        int d1=0;
        int d2=0;
        int pow=1;

        while(number1>0 || number2>0 || carry>0){
            if(number1>0){
                d1=number1%10;
                carry+=d1;
            }

            if(number2>0){
                d2=number2%10;
                carry+=d2;
            }

            int digit=0;
            digit=(carry%base);
            carry=carry/base;


            total=total+(digit*pow);
            pow*=10;
            number1=number1/10;
            number2=number2/10;
        }

        return total;
    }

    public static void anyBaseSubtraction(int number1, int number2, int base) {
        int res = 0;
        int carry = 0;
        int pow = 1;

        while (number1 > 0 || number2 > 0) {
            //Part 1
            int d1 = number1 % 10;
            int d2 = number2 % 10;

            //Part 2
            int digit = (d1 - carry) - d2;

            //Part 3
            if (digit < 0) {
                digit = d1 + base - carry - d2;
                carry = 1;
            } else {
                carry = 0;
            }

            //Part 4
            res += digit * pow;
            pow *= 10;
            number1 /= 10;
            number2 /= 10;
        }

        System.out.println(res);
    }


    public static void anyBaseMultiplication(int number1, int number2, int base) {
        int res=0;
        int pow=1;
        while(number2>0){
           int r = mulHelper(number1,number2%10,base);
           r*=pow;
           res=(anyBaseAddition(r,res,base));
           pow*=10;
           number2=number2/10;
        }
        System.out.println(res);
    }

    public static  int mulHelper(int number,int digit,int base){
        int res = 0;
        int carry = 0;
        int pow = 1;
        int total = 0;

        while (number>0 || carry>0){
            int d=number%10;
            int mulResult=d*digit+carry;
            if(mulResult>=base){
                int octVal=decimalToanyBase(mulResult,base);
                if(octVal>=base){
                    int mulRes=(octVal%10)*pow;
                    total+=mulRes;
                    carry=(octVal/10);
                }else{
                    total+=(octVal);
                    carry=0;
                }
            }else{
                int mulRes=(mulResult%10)*pow;
                total+=mulRes;
                carry=0;
            }
            pow*=10;
            number=number/10;
        }

        return total;

    }



}
