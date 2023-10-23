import java.util.ArrayList;

public class CustomPriorityQueue {
    public static void main(String []args){
        CPQueue cpq=new CPQueue();
        cpq.add(10);
        cpq.add(20);
        cpq.add(30);
        cpq.add(40);
        System.out.println(cpq.data);
        cpq.remove();
        System.out.println(cpq.data);
        cpq.add(5);
        cpq.add(1);
        System.out.println(cpq.data);
        cpq.peek();

    }

    static class CPQueue{
        public static ArrayList<Integer> data=new ArrayList<>();

        public static void add(int val){
            data.add(val);
            upheapify(data.size()-1);

        }

        public static void  upheapify(int ind){
            if(ind==0){
                return;
            }
            int cind=ind;
            int pind=(cind-1)/2;

            if(data.get(pind) > data.get(cind)){
                swap(cind,pind);
                upheapify(pind);
            }
        }

        public static void swap(int i,int j){
            int vali=data.get(i);
            int valj=data.get(j);
            data.set(i,valj);
            data.set(j,vali);
        }

        public static void remove(){
            // fisrt swap the value of first and last
           swap(0,data.size()-1);
           data.remove(data.size()-1);
            downheapify(0);
        }

        public static void downheapify(int ind){

            int pid=ind;
            int lcind=(2*pid)+1;
            int rcind=(2*pid)+2;

            if(lcind < data.size() && data.get(lcind)<data.get(pid)){
                pid=lcind;
            }

            if(rcind < data.size() &&  data.get(rcind)<data.get(pid)){
                pid=rcind;
            }

            if(pid!=ind){
                swap(pid,ind);
                downheapify(pid);
            }

        }

        public static void peek(){
            System.out.println(data.get(0));
        }

        public static void size(){
            System.out.println(data.size());
        }

    }




}
