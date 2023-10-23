import java.util.ArrayList;
import java.util.Stack;

public class NykaaInterview {

    public static void main(String a[]){

        boolean visited[][]=new boolean[4][4];
        String paths =new String();
        paths="";
        //minPath(4,4,0,0,2,2,paths,visited);

        /*Write an algorithm for a code editor which checks whether the parentheses are balanced or not ((,{,[).

        Ex: (({[ ] })) - correct
                (({[}])) - incorrect
                (({[]}) - incorrect
                [(({[]})) - incorrect
                [()(){}] - correct*/



        checkParentheses("(({[}]))");

    }



    public static void checkParentheses(String s){
        Stack<String> st=new Stack<>();
        int ch=0;


        while((!st.empty() && ch < s.length() )|| ch==0){

            if((s.charAt(ch)+"").equalsIgnoreCase("{") || (s.charAt(ch)+"").equalsIgnoreCase("(") ||(s.charAt(ch)+"").equalsIgnoreCase("[")  ){
                st.push(s.charAt(ch)+"");
            }else{
                if ((s.charAt(ch)+"").equalsIgnoreCase("}")) {
                    if(st.peek()+""=="{"){
                        st.pop();
                    }else{
                        System.out.println("Incorrect");
                        break;
                    }
                }
                if ((s.charAt(ch)+"").equalsIgnoreCase("]") ) {
                    if(st.peek()+""=="]"){
                        st.pop();
                    }else{
                        System.out.println("Incorrect");
                        break;
                    }
                }
                if ((s.charAt(ch)+"").equalsIgnoreCase(")") ) {
                    if(st.peek()+""==")"){
                        st.pop();
                    }else{
                        System.out.println("Incorrect");
                        break;
                    }
                }
            }
            ch++;
        }

        if(st.empty()==true){
            System.out.println("correct");
        }
    }

    public static void minPath(int row, int col, int i, int j, int tr, int tc,String paths,boolean[][] visited){

        if(i >row || j > col || i >tr || j > tc){
            return;
        }else if( i < row &&  j < col && visited[i][j]==true){
            return;
        }
        else if(i == tr && j== tc ){
            paths+=(i+"-"+j+" ** ");
            System.out.println(paths);
        }

        visited[i][j]=true;
        paths+=(i+"-"+j+" ** ");

        minPath(row,col,i+2,j+1,tr,tc,paths,visited);
        minPath(row,col,i+2,j-1,tr,tc,paths,visited);
        minPath(row,col,i-2,j+1,tr,tc,paths,visited);
        minPath(row,col,i-2,j+1,tr,tc,paths,visited);

        minPath(row,col,i+1,j-2,tr,tc,paths,visited);
        minPath(row,col,i-1,j-2,tr,tc,paths,visited);
        minPath(row,col,i+1,j+2,tr,tc,paths,visited);
        minPath(row,col,i-1,j+2,tr,tc,paths,visited);


        paths=new String();

        visited[i][j]=false;
    }



}
