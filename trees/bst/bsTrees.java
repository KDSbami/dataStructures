

import java.util.*;


class BST {

     class Node {
        /* 
        This class is used for creation of each node of the bst.
        */
        int value;
        int data;
        Node left,right;

        Node(int val) {
            this.value = val;
            this.left = this.right = null;
        }
        Node(int val,int data) {
            this.value = val;
            this.data = data;
            this.left = this.right = null;
        }
    }
    Node root;
    BST() {
        root=null;
    }

    //find minimum value of a given tree
     int minVal(Node head) {
        
        if(head.left != null) {
            return minVal(head.left);
        }
        else {
            return head.value;
        }
         
    }
    //recursive operation to set value in bst.
     Node addAndUpdate(Node head, int value,int data) {
        
        if(head == null) {
            
            return new Node(value,data);
        }

        if(head.value == value) {
            return head;
        }

        if(value < head.value) {
            head.left = addAndUpdate(head.left,value,data);
        }

        if(value > head.value) {
            head.right = addAndUpdate(head.right,value,data);
        }

        return head;
    }

    //recursive operation to set value in bst.
     Node addAndUpdate(Node head, int value) {
        
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
     void inorderTraversal(Node root) {
        if(root == null) {
            return;
        }

        inorderTraversal(root.left);
        System.out.print(root.data+" ");
        inorderTraversal(root.right);

    }

    //recursive operation to update value in bst.
     Node updateOld(Node head,int value, int newValue) {
        
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
     Node deleteRec(Node head,int value) {
        
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
     int sum = 0;
     int counter = 0;
     int kthMinSumVal(Node head , int k) {
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
    public  void kthMinSum(int value) {
        System.out.println(kthMinSumVal(root,value));
    }
    
//Q2    Vertical order traversal
    
        class Values {
            int min;
            int max;
            Values() {
                this.min = Integer.MAX_VALUE;
                this.max = Integer.MIN_VALUE;
            }
        }
        // Positional grid 
        void horizontalMinMax(Node head,Values value,int dist,BST top) {
            //System.out.println(dist+"sds ");
            if(head == null) {
                return;
            }
            System.out.println(dist+" "+head.value);
            top.add(dist,head.value);
            if(dist < value.min) {
                value.min = dist;
            }
            if(dist > value.max) {
                value.max = dist;
            }
            horizontalMinMax(head.left,value,dist-1,top);
            horizontalMinMax(head.right,value,dist+1,top);
        }
        
        void showVerticalLine() {
            Values val = new Values();
            BST top = new BST();
            horizontalMinMax(root,val,0,top);
            //System.out.print(val.min+" "+val.max);
            top.printInorderData();

        }


    

    //CRUD operations

    //adding to the structure
    void add(int value) {
        root = addAndUpdate(root,value);
    }
    void add(int value,int data) {
        root = addAndUpdate(root,value,data);
    }
    void printInorderData(){
        inorderTraversal(root);
    }

    //updating from the structure
    void update(int value , int newValue) {
        root = updateOld(root,value,newValue);
    }

    //delete from the structure
    void delete(int value) {
        root = deleteRec(root,value);
    }
}


class bsTrees extends BST{
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        BST bst = new BST();
        for(int i=0;i<n;i++) {
            bst.add(sc.nextInt());
        }

        int kthSmallestSum = sc.nextInt();

        //kthMinSum(kthSmallestSum);
        bst.showVerticalLine();
        
    }
}