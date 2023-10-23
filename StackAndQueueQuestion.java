import java.util.ArrayDeque;
import java.util.Stack;

public class StackAndQueueQuestion {


    public static void main(String a[]){
        //["MyQueue","push","push","push","push","pop","push","pop","pop","pop","pop"]
        //[[],[1],[2],[3],[4],[],[5],[],[],[],[]]
        MyQueue mq=new MyQueue();
        mq.push(1);
        mq.push(2);
        mq.push(3);
        mq.push(4);
        System.out.println(mq.pop());
        mq.push(5);
        System.out.println(mq.pop());
        System.out.println(mq.pop());
        System.out.println(mq.pop());
        System.out.println(mq.pop());

        //[null,null,null,null,null,1,null,4,5,3,2]
        //[null,null,null,null,null,1,null,2,3,4,5]

    }


    public static class MyQueue {

        Stack<Integer> st1;
        Stack<Integer> st2;
        int peek;
        public MyQueue() {
            st1=new Stack<>();
            st2=new Stack<>();
            peek=0;

        }

        public void push(int x) {
            if(st1.isEmpty()){
                peek=x;
            }
            st1.push(x);
        }

        public int pop() {
            int le=0;
            st2=new Stack<>();
            while(!st1.isEmpty()){
                int e=st1.pop();
                if(st1.isEmpty()){
                    le=e;
                    break;
                }else{
                    st2.push(e);
                }
            }
            boolean isFirst=true;
            while(!st2.isEmpty()){
                int e=st2.pop();
                if(isFirst){
                    peek=e;
                    isFirst=false;
                }
               st1.push(e);
            }

            st2=new Stack<>();
            return le;

        }

        public int peek() {
            if(st1.isEmpty()){
                return 0;
            }else{
                return peek;
            }
        }

        public boolean empty() {
            if(st1.size()==0){
                return true;
            }else{
                return false;
            }
        }
    }



    public static class MyStack {
        ArrayDeque<Integer> q1;
        ArrayDeque<Integer> q2;
        int topElement=0;
        public MyStack() {
            q1=new ArrayDeque<>();
            q2=new ArrayDeque<>();
        }

        public void push(int x) {
            q1.add(x);
            topElement=x;
        }

        public int pop() {
            int le=0;
            q2=new ArrayDeque<>();
            while(!q1.isEmpty()){
                int e=q1.remove();
                if(q1.isEmpty()){
                    le=e;
                    break;
                }else{
                    topElement=e;
                    q2.add(e);
                }
            }
            q1=q2;
            q2=new ArrayDeque<>();
            return le;
        }

        public int top() {
            if(q1.isEmpty()){
                return 0;
            }else{
               return topElement;
            }
        }

        public boolean empty() {
            if(q1.size()==0){
                return true;
            }else{
                return false;
            }
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
