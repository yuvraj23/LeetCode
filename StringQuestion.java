import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class StringQuestion {
    static String  strMap[]={".;","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};

    public static void main(String args[]){
        //Print All Palindromic Substrings
        //printPalindromic("abcc");

        // Tower of hanio
        //towerOfHanio(3,"A","B","C");

       // System.out.print(getSubSequence("abc"));
        //System.out.print(getKpc("123"));

       // System.out.print(getStairPaths(4));

       // System.out.print(getMazePaths(0,0,3,3));

       // System.out.print(getMazePathWithJumps(0,0,3,3));

        printSubSequence("abc","");




    }

    public static void printSubSequence(String s,String ans){

        if(s.length()==0){
            System.out.println(ans);
            return;
        }

        String ch= s.charAt(0)+"";
        String res=s.substring(1);
        printSubSequence(res,ans+ch);
        printSubSequence(res,ans+"_");

    }

//[h1v1, v1h1, d1]
    public static ArrayList<String> getMazePathWithJumps(int r,int c,int dr,int dc){
        if(r>=dr || r<0 || c >= dc || c < 0){
            ArrayList<String> res=new ArrayList<>();
            return res;
        }
        if(r==dr-1 && c==dc-1){
            ArrayList<String> res=new ArrayList<>();
            res.add("");
            return res;
        }
        // column wise  call
        ArrayList<String> horizontalAns= new ArrayList<>();
        for(int i=c+1;i<dc;i++){
            horizontalAns=getMazePathWithJumps(r,c+1,dr,dc);
        }
        // row wise  call
        ArrayList<String> verticalAns= new ArrayList<>();
        for(int i=r+1;i<dr;i++){
            verticalAns=getMazePathWithJumps(r+1,c,dr,dc);
        }

        // diagonal calls
        ArrayList<String>diaAns= new ArrayList<>();
        for(int i=r+1;i<dr && i<dc;i++){
            diaAns=getMazePathWithJumps(r+1,c+1,dr,dc);
        }


        ArrayList<String> ans=new ArrayList<>();

        for(String hor:horizontalAns){
            ans.add("h"+(c+1)+hor);
        }
        for(String ver:verticalAns){
            ans.add("v"+(r+1)+ver);
        }

        for(String di:diaAns){
            ans.add("d"+(r+1)+di);
        }

        return ans;
    }


//[hhvv, hvhv, hvvh, vhhv, vhvh, vvhh]
    public  static ArrayList<String> getMazePaths(int r,int c,int sr,int sc){
        if(r>=sr || r<0 || c >= sc || c < 0){
            ArrayList<String> res=new ArrayList<>();
            return res;
        }
        if(r==sr-1 && c==sc-1){
            ArrayList<String> res=new ArrayList<>();
            res.add("");
            return res;
        }

       ArrayList<String> verticalAns= getMazePaths(r+1,c,sr,sc);
        ArrayList<String> horizontallAns= getMazePaths(r,c+1,sr,sc);

        ArrayList<String> ans=new ArrayList<>();

        for(String hor:horizontallAns){
            ans.add("h"+hor);
        }
        for(String ver:verticalAns){
            ans.add("v"+ver);
        }


        return  ans;
    }


    public static ArrayList<String> getStairPaths(int n){
        if(n<0){
            ArrayList<String> ans=new ArrayList<>();
            return ans;
        }
        if(n==0){
            ArrayList<String> ans=new ArrayList<>();
            ans.add("");
            return ans;
        }

        ArrayList<String> ones=getStairPaths(n-1);
        ArrayList<String> twos=getStairPaths(n-2);
        ArrayList<String> thress=getStairPaths(n-3);
        ArrayList<String> res=new ArrayList<>();

        for(String ss:ones){
            res.add("1"+ss);
        }

        for(String ss:twos){
            res.add("2"+ss);
        }

        for(String ss:thress){
            res.add("3"+ss);
        }

        return res;

    }
    public  static ArrayList<String> getKpc(String s){
        if(s.length()==0){
            ArrayList<String> r=new ArrayList<>();
            r.add("");
            return r;
        }

        String ch=s.charAt(0)+"";
        String res=s.substring(1);
        ArrayList<String> rs=getKpc(res);
        ArrayList<String> myres=new ArrayList<>();

        String ns=strMap[Integer.parseInt(ch)];

        for(int i=0;i<ns.length();i++){
            ch=ns.charAt(i)+"";
            for(String s1:rs){
                myres.add(ch+s1);
            }
        }

        return myres;
    }

    public static ArrayList<String> getSubSequence(String s){
        if(s.length()==0){
            ArrayList<String> r=new ArrayList<String>();
            r.add("");
            return r;
        }

        String ch=s.charAt(0)+"";
        String res=s.substring(1);
        ArrayList<String> recsList=getSubSequence(res);

        ArrayList<String> mylist=new ArrayList<>();

        for(String ss:recsList){
            mylist.add(ch+ss);
        }

        for(String ss:recsList){
            mylist.add(ss+"_");
        }
        return mylist;
    }

    public static void towerOfHanio(int n,String source,String destination,String helper){
        if(n==0){
            return;
        }
        towerOfHanio(n-1,source,helper,destination);
        System.out.println("Move "+ n + " from " + source +" to "+ destination );
        towerOfHanio(n-1,helper,destination,source);
    }

    public static void printPalindromic(String s){
        for(int i=0;i<s.length();i++){

            for(int j=i+1;j<=s.length();j++){
               String sbString=s.substring(i,j);
               int low=0;
               int high=sbString.length()-1;
               boolean isPal=true;
               while(low<high){
                   if(sbString.charAt(low)!=sbString.charAt(high)){
                       isPal=false;
                       break;
                   }
                   low++;
                   high--;
               }
               if(isPal){
                   System.out.println(sbString);
               }
            }
        }
    }
}
