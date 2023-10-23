import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

   static  PriorityQueue<Integer> left=new PriorityQueue<>(Collections.reverseOrder());
   static  PriorityQueue<Integer> right=new PriorityQueue<>();

    public MedianFinder() {

    }

    public static void main(String []args){

        int a[]={-1,-2,-3,-4,-5};
        for(int i=0;i<a.length;i++){
            addNum(a[i]);
            System.out.println(left+" "+right);
        }

//
//[-1] []
//[-1] [-2]
//[-3, -1] [-2]
//[-3, -1] [-4, -2]
//[-5, -1, -3] [-4, -2]
    }

    public static  void addNum(int num) {

        left.add(num);
        if(left.size()==0 &&  right.size()==0){
            left.add(num);
        }else if(right.peek() !=null && num >= right.peek()){
            right.add(num);
            left.remove(num);
        }

        if(left.size()-right.size()>1){
            int v=left.remove();
            right.add(v);
        }
        else if(right.size()-left.size()>1){
            int v=right.remove();
            left.add(v);
        }

    }

    public static  double findMedian() {
        System.out.println(left+" , "+right);
        if((left.size()+right.size())%2 == 0){
            return (double)(left.poll()+right.poll())/2;
        }else{
            if(left.size()>right.size()){
                return (double)( left.poll());
            }else{
                return  (double)( right.poll());
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */