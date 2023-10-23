public class LinkedListQuestion {
    public static class Node{
        int data;
        Node next;
        Node(){};
        Node(int data,Node next){
            this.data=data;
            this.next=next;
        }
        Node(int data){
            this.data=data;
        }
    }
    static Node left;
    static boolean isChecker;
    static int len;
    public static void main(String args[]){
//        LinkedList ll=new LinkedList();
//        ll.addLast(9);
//        ll.addLast(9);
//        ll.addLast(9);
//        ll.addLast(9);
//        ll.addLast(9);
//        ll.addLast(9);
//        ll.addLast(9);
//        ll.display();
//
//        LinkedList ll2=new LinkedList();
//        ll2.addLast(9);
//        ll2.addLast(9);
//        ll2.addLast(9);
//        ll2.addLast(9);
//        ll2.display();


        LinkedList ll=new LinkedList();
        ll.addLast(2);
        ll.addLast(4);
        ll.addLast(3);

        ll.display();

        LinkedList ll2=new LinkedList();
        ll2.addLast(5);
        ll2.addLast(6);
        ll2.addLast(4);

        ll2.display();

        left=ll.head;
        ll.head=ll.addTwoNumbers(ll.head,ll2.head);

    }

    // contains all linkedlist function
    public static class LinkedList{

        Node head;
        Node tail;
        int size;


        public Node addTwoNumbers(Node l1,Node l2) {
            LinkedList ansList=new LinkedList();
            int c=0;

            while(l1!=null && l2!=null){
                int sum=l1.data+l2.data+c;
                c=sum/10;
                if(sum>9){
                    sum=sum%10;
                }
                ansList.addLast(sum);
                l1=l1.next;
                l2=l2.next;

            }
            if(l1!=null){
                while(l1!=null){
                    int sum=l1.data+c;
                    if(sum>9){
                        c=sum/10;
                        sum=sum%10;
                    }
                    ansList.addLast(sum);
                    l1=l1.next;
                }
            }

            if(l2!=null){
                while(l2!=null){
                    int sum=l2.data+c;
                    if(sum>9){
                        c=sum/10;
                        sum=sum%10;
                    }
                    ansList.addLast(sum);
                    l2=l2.next;
                }
            }

            if(l1==null && l2==null && c!=0){
                ansList.addLast(c);
            }

            ansList.display();
            return ansList.head;
        }

        public void display(){
            System.out.println();
            Node temp=this.head;
            if(head==null){
                System.out.println("No elements");
                return;
            }
            while(temp!=null){
                System.out.print(temp.data+" -> ");
                temp=temp.next;
            }
        }

        public void addLast(int data){
            Node node=new Node(data);
            node.next=null;
            if(head == null && tail == null){
                this.head=this.tail=node;
            }else{
                this.tail.next=node;
                this.tail=node;
            }
            size++;
        }

        public void addFirst(int data){
            Node node=new Node(data);
            node.next=null;
            if(head == null && tail == null){
                this.head=this.tail=node;
            }else{
                node.next=head;
                head=node;
            }
            size++;

        }

        public int removeFirst(){
            int data=-1;
            if(head!=null){
                //System.out.println("\nRemoved  : "+head.data);
                data= head.data;;
                head=head.next;
                if(head==null){
                    tail=null;
                }
                size--;
            }else{
                System.out.println("List is empty");
            }

            return data;
        }

        public Node getValueInLinkedList(int ind){
            Node temp=head;
            int i=0;
            for(;i<ind-1 && temp!=null;i++){
               temp=temp.next;
            }

            if(temp!=null &&  i == ind-1){
                System.out.println("\n element at "+ind+" = "+temp.data);
                return temp;
            }else{
                System.out.println("\nIndex out of bound : "+ind +"  size is : "+size);
            }
            return null;
        }

        public void removeLastInLinkedList(){
            if(size==1){
                tail=head=null;
                this.size--;
                return;
            }
            if(size!=0 && tail!=null &&  head!=null){
                Node temp=head;
                while(temp.next.next!=null){
                    temp=temp.next;
                }
                temp.next=null;
                this.size--;
            }
        }

        public void removeAtIndexInLinkedList(int ind){
            if(ind>size){
                System.out.println("\nCannot perform this operaion as size is : "+size +" and you are trying to remove : "+ind);
                return;
            }
            if(ind ==1){
                removeFirst();
                return;
            }
            if(ind==size){
                removeLastInLinkedList();
                return;
            }
            Node temp = head;
            Node node=null;
           int i=0;
           for(;i<ind-1 &&  temp!=null; i++){
               node=temp;
               temp=temp.next;
           }
           if(node!=null){
               System.out.println("\nRemove at ind = "+ind+" data = "+node.data);
               node.next=node.next.next;
               this.size--;
           }else{
               System.out.println("\nUnable to  perform this operaion as size is : "+size +" and you are trying to remove : "+ind);
           }


        }

        public void reverseALinkedListDataIterative(){
            Node temp=head;
            for(int i=0;i<size/2 &&temp!=null;i++){
                int td=temp.data;
                Node sn=getValueInLinkedList(this.size-i);
                if(sn==null){
                    return;
                }
                temp.data=sn.data;
                sn.data=td;
                temp=temp.next;
            }
        }

//        public void kthNodeFromEndOfLinkedList(int ind){
//            if(ind>size){
//                return;
//            }
//            int len=this.size-ind-1;
//            Node temp=head;
//            for(int i=0;i<=len &&  temp!=null;i++){
//                temp=temp.next;
//            }
//            if(temp!=null){
//                System.out.println("\nreverse at : ind = "+ind+" - data = "+temp.data);
//            }else{
//                System.out.println("\nOut of bound index");
//            }
//
//        }

        public void kthNodeFromEndOfLinkedList(int ind){
            Node slow=head,fast=head;
            int i=0;
            while(i<ind &&  fast!=null){
                fast=fast.next;
                i++;
            }

            while(fast!=null){
                slow=slow.next;
                fast=fast.next;

            }
            System.out.println("\n data at ind = "+ind+" - "+slow.data);

        }

        public void midOfLinkedList(){
            Node fast=head;
            Node slow=head;

            while( fast.next!=null && fast.next.next!=null){
                slow=slow.next;
                fast=fast.next.next;
            }


        }

        public Node mergeTwoSortedLinkedLists(Node h1,Node h2){

            LinkedList sortedLL=new LinkedList();
            Node tempH1=h1;
            Node tempH2=h2;

            while(tempH1!=null && tempH2!=null){

                if(tempH1.data>tempH2.data){
                    sortedLL.addLast(tempH2.data);
                    tempH2=tempH2.next;

                }else{
                    sortedLL.addLast(tempH1.data);
                    tempH1=tempH1.next;
                }
            }

            if(tempH1!=null){
                while(tempH1!=null){
                    sortedLL.addLast(tempH1.data);
                    tempH1=tempH1.next;
                }
            }

            if(tempH2!=null){
                while(tempH2!=null){
                    sortedLL.addLast(tempH2.data);
                    tempH2=tempH2.next;
                }
            }


            return sortedLL.head;
        }

        public Node mergeSort(Node head,Node tail){
            if(head==tail){
//               LinkedList lln=new LinkedList();
//               lln.addLast(head.data);
//               return lln.head;
                return head;
            }
            Node mid=midOfLinkedList(head,tail);
            Node n1=mergeSort(head,mid);
            Node n2=mergeSort(mid.next,tail);
            Node sortedNodes=mergeTwoSortedLinkedLists(n1,n2);
            return sortedNodes;
        }
        public Node midOfLinkedList(Node head,Node tail){
            Node fast=head;
            Node slow=head;

            while( fast.next!=tail && fast.next.next!=tail){
                slow=slow.next;
                fast=fast.next.next;
            }


            return slow;
        }

        public void removeDuplicatesInASortedLinkedList(){

            Node slow=this.head;
            Node fast=this.head.next;

            while(fast!=null){
                if(fast.data== slow.data){
                    slow.next=fast.next;
                    slow=fast;
                    fast=fast.next;
                }else{
                    slow=slow.next;
                    fast=fast.next;
                }
            }
        }


        public Node oddEvenList(Node head){
            if(head.next==null){
                return head;
            }
            Node s=head;
            Node f=head.next.next;
            Node sfm=head.next;
            while(f!=null){
                Node t1=s.next;
                Node t2=null;
                if(f.next!=null){
                    t2=f.next.next;
                }
                s.next=f;
                sfm.next=f.next;
                f.next=t1;
                s=s.next;
                f=t2;
                sfm=sfm.next;

            }
            return head;
        }

        public void KReverseInLinkedList(Node head,int k){
            LinkedList nl=new LinkedList();

            Node temp=head;

            while(this.size>0){
                LinkedList tempLL=new LinkedList();
                if(size>k){
                    for(int i=0;i<k;i++){
                        int data=removeFirst();
                        tempLL.addFirst(data);
                    }
                }else{
                    int s=size;
                    for(int i=0;i<s;i++){
                        int data=removeFirst();
                        tempLL.addLast(data);
                    }
                }
                if(nl.head == null){
                    nl.head=tempLL.head;
                    nl.tail=tempLL.tail;
                }else{
                    nl.tail.next=tempLL.head;
                    nl.tail=tempLL.tail;
                }
            }

            this.head=nl.head;
        }


        static boolean isReverseNeeded=true;

        public void reverseLinkedListPointerRecursive(Node right){

            if(right==null){
                return;
            }

            reverseLinkedListPointerRecursive(right.next);

            if(right==left){
                isReverseNeeded=false;
            }
            if(isReverseNeeded){
                int data= right.data;
                right.data= left.data;
                left.data=data;
                left=left.next;
            }else{
                return;
            }

        }

        public void isLinkedListAPalindrome(Node right){

            if(right==null){
                return;
            }

            reverseLinkedListPointerRecursive(right.next);

            if(right==left){
                isReverseNeeded=false;
            }
            if(isReverseNeeded){
                int data= right.data;
                right.data= left.data;
                left.data=data;
                left=left.next;
            }else{
                return;
            }


        }


        public boolean isPalindrome(Node head){
            isChecker=true;
            len=0;
            left=head;
            boolean isPal=helper(head,0);
            return isPal;
        }

        public boolean helper(Node right,int s){
            if(right==null){
                len=s;
                return true;
            }
            boolean ans=helper(right.next,s+1);
            if(ans==false){
                return ans;
            }
            if(s>=(len/2) && isChecker){
                if(left.data==right.data){
                    left=left.next;
                    return true;
                }else{
                    return false;
                }

            }else{
                isChecker=false;
                return ans;
            }

        }

        public void  reorderList(Node righ,int s){

            if(righ.next==null){
                len=s;
                return;
            }
            reorderList(righ.next,s+1);

            if(s>(len/2)){
                Node temp1=left.next;
                Node temp2=righ.next;
                righ.next=null;
                left.next=temp2;
                temp2.next=temp1;
                left=temp1;
            }

        }


    }


}
