import java.util.*;

class LinkedList {
    
    class Node {
        Node next;
        int value;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    Node root;
    static Node slowPtr;
    static int palindromeBit = 0;

    LinkedList() {
        root = null;
    }

    Node add(Node head,int value) {
        
        if(head == null) {
            return new Node(value);
        }
        else {
            head.next = add(head.next,value);
        }
        return head;
    }
    void add(int value) {
        root = add(root,value);
    }

    Node delete(Node head, int value) {
        
        Node top = head;
        if(top.next == null && top.value == value) {
            head= null;
        }
        while(top.next != null) {

            if(top.value == value) {
                head = head.next;
                break;
            }

            if(top.next.value == value) {
                top.next = top.next.next;
                break;
            }
            else {
                top = top.next;
            }
        }
        return head;
    }
    void delete(int value) {
        root = delete(root,value);
    }

    void printList() {
        Node top = root;

        while(top != null) {
            System.out.print(top.value+" ");
            top = top.next;
        }
    }



//Q1 palindrome in linked list in O(n).
    void palindromeCheck(Node slow, Node fast) {
        slowPtr = slow;
        if(fast ==  null || slowPtr == null) {
                return ;
        }

        int temp = slowPtr.value;
        if(fast.next == null) {
            fast = fast.next;

        }
        else {
            fast = fast.next.next;
            slowPtr = slowPtr.next;
        }

        palindromeCheck(slowPtr,fast);

        if(slowPtr.value != temp) {
            palindromeBit = 1;
            slowPtr = slowPtr.next;
        }
        else {
            slowPtr = slowPtr.next;
        }
    
    }
    boolean palindromeCheck() {
        this.palindromeBit = 0;
        palindromeCheck(root,root);
        if(palindromeBit == 0) {
            return true;
        }
        else {
            return false;
        }
    }


//Q2 Duplicates from sorted list
    void removeDuplicatesSorted(Node head) {

           
            while(head.next != null) {
                if(head.value == head.next.value) {
                    head.next = head.next.next;
                }
                else {
                    head = head.next;
                }
            }
            
        }

//Q3 Delete duplicates
void deleteDuplicatesSorted(Node head) {

       
    while( head != null && head.next != null ) {
        
        if(head.value == head.next.value) {
           int data = head.value;
           
           while(head.value == data) {
               head = head.next;
               root = delete(root,data);
               Node test = root;
               while(test != null)
               {
                test = test.next;
                
               }
               if(head == null) {
                   break;
               }
           }
           
        }
        else {
            head = head.next;
        }
       
    }
    
}


        void removeDuplicates(String sortStatus){
            if(sortStatus == "sorted") {
                removeDuplicatesSorted(root);
            }
            else {
                System.out.print("Sorting status undefined.");
            }
        }
        void deleteDuplicates(String sortStatus){
            if(sortStatus == "sorted") {
                deleteDuplicatesSorted(root);
            }
            else {
                System.out.print("Sorting status undefined.");
            }
        }
}

       

  



public class singlyLinkedList extends LinkedList {

    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
       
        for(int i=0;i<n;i++) {
           list.add(sc.nextInt());
        }

        list.printList();
        System.out.println();
        list.deleteDuplicates("sorted");
        list.printList();
        
    }

}

