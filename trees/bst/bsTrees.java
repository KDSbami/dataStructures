

import java.util.*;


public class bsTrees {

    static class Node {
        /* 
        This class is used for creation of each node of the bst.
        */
        int value;
        Node left,right;

        Node(int val) {
            value = val;
            left = right = null;
        }
    }

    static Node root;

    bsTrees() {
        root = null;
    }

    //find minimum value of a given tree
    static int minVal(Node head) {
        
        if(head.left != null) {
            return minVal(head.left);
        }
        else {
            return head.value;
        }
         
    }

    //recursive operation to set value in bst.
    static Node addAndUpdate(Node head, int value) {
        
        if(head == null) {
            return new Node(value);
        }

        if(head.value == value) {
            return head;
        }

        if(value < head.value) {
            head.left = addAndUpdate(head.left,value);
        }

        if(value > head.value) {
            head.right = addAndUpdate(head.right,value);
        }

        return head;
    }

    //recursive operation to update value in bst.
    static Node updateOld(Node head,int value, int newValue) {
        
        if(head ==  null) {
            return head; 
        }

        if(head.value == value) {
            head.value = newValue;
            return head;
        }

        if(value < head.value) {
            head.left = updateOld(head.left,value,newValue);
        }

        if(value > head.value) {
            head.right = updateOld(head.right,value,newValue);
        }

        return head;
    }

    //recursive operation to delete value in bst.
    static Node deleteRec(Node head,int value) {
        
        if(head ==  null) {
            return head; 
        }
        
        if(value < head.value) {
            head.left = deleteRec(head.left,value);
        }

        if(value > head.value) {
            head.right = deleteRec(head.right,value);
        }

        else {
            if(head.right == null ) {
                return head.left;
            }

            if(head.left == null ) {
                return head.right;
            }

            else {
                head.value = minVal(head.right);
                deleteRec(head.right,head.value);
            }
        }
        return head;
    }

//Q1  kth min sum
    static int sum = 0;
    static int counter = 0;
    static int kthMinSumVal(Node head , int k) {
        if(head == null) {
            return -1 ;
        }

        kthMinSumVal(head.left,k);

        counter++;

        if(counter<=k) {
            sum = sum + head.value;
        }
        else{
            return sum;
        }

        kthMinSumVal(head.right,k);
          
        return sum;     
    }
    public static void kthMinSum(int value) {
        System.out.println(kthMinSumVal(root,value));
    }
    
//Q2    Vertical order traversal
    
        class Values {
            int min;
            int max;
            Values() {
                this.min = Integer.MIN_VALUE;
                this.max = Integer.MIN_VALUE;
            }
        }

        void horizontalMinMax(Node head,Values value,int dist) {
            if(head == null) {
                return;
            }
            System.out.println(dist+" "+head.value);
            if(dist < value.min) {
                value.min = dist;
            }
            if(dist > value.max) {
                value.max = dist;
            }

            horizontalMinMax(head.left,value,dist-1);
            horizontalMinMax(head.right,value,dist+1);
        }
        
        void showVerticalLine() {
            Values val = new Values();
            horizontalMinMax(root,val,0);
        }


    

    //CRUD operations

    //adding to the structure
    static void add(int value) {
        root = addAndUpdate(root,value);
    }


    //updating from the structure
    static void update(int value , int newValue) {
        root = updateOld(root,value,newValue);
    }

    //delete from the structure
    static void delete(int value) {
        root = deleteRec(root,value);
    }

    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        bsTrees bst = new bsTrees();
        for(int i=0;i<n;i++) {
            bst.add(sc.nextInt());
        }

        int kthSmallestSum = sc.nextInt();

        //kthMinSum(kthSmallestSum);
        bst.showVerticalLine();
        
    }
}