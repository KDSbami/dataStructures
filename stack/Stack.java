import java.util.*;

class StackStr {
    
    class Node {
        Node next;
        int data;
        
        Node(int value) {
            this.data = value;
        }
    }

    

    Node root = null;

    void push(int value) {
        Node newHead = new Node(value);
        newHead.next = this.root;
        this.root = newHead;
    }

    int peak() {
        if(root == null) {
            return -1;
        } 
        else {
            return this.root.data;
        }
    }
    int pop(){
        if(this.root == null) {
            return -1;
        }
        else {
            int temp = this.root.data;
            root = root.next;
            return temp;
        }
    }

//Q1 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    boolean checkBrackets(String s) {
        if(s == null) {
            return true;
        }   
        StackStr stack = new StackStr();
        for(int i=0;i<s.length();i++) {
            char current = s.charAt(i);
            if(current == '{' || current == '(' || current == '[') {
                stack.push(s.charAt(i));
            }
            else if(current == '}') {
                if((char)stack.peak() == '{') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
            else if(current == ')') {
                if((char)stack.peak() == '(') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
            else if(current == ']') {
                if((char)stack.peak() == '[') {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }

        if(stack.peak() != -1){
            return false;
        }
        else{
            return true;
        }

    }


}


public class Stack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // int t = sc.nextInt();
         StackStr stacks = new StackStr();
        // for(int i=0;i<t;i++) {
        //     stack.push(sc.nextInt());
            
        // }
       String test = sc.next();
       System.out.print(stacks.checkBrackets(test));
        
    }
}