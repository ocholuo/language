import java.util.Stack;

// First Binary Tree Traversal

// preorder: V-L-R
// inorder: L-V-R
// postorder: L-R-V

public class BTTraversal {

// 1. preorder V L R

    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.data);
            preorder(node.left);
            preorder(node.right);
        }
    }
    

    public void preorderwithStack(Node node) {
        if(node == null) {
            return;
        }

        Stack<Node> s = new Stack<Node>();
        s.add(node);
        while(!s.isEmpty()) {
            node = s.pop();
            System.out.println(node);
            if(node.right != null) {
                s.add(node.right);
            }
            if(node.left != null) {
                s.add(node.left);
            }
        }
    }


// 2. inorder

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data);
            inorder(node.right);
        }
    }


// 3. postorder L - R - V

    public void postorder(Node node) {
        if (node != null) {
            inorder(node.left);
            inorder(node.right);
            System.out.print(node.data);
        }
    }


    public void postorderWithTwoStack(Node node) {
        if (node == null) {
            System.out.print("The node is null.");
            return;
        }
        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();

        s1.push(node);

        while(!s1.isEmpty()) {
            node = s1.pop();
            s2.push(node);
            if(node.left != null) {
                s1.push(node.left);
            }
            if(node.right != null) {
                s1.push(node.right);
            }
        }
        while(!s2.isEmpty()) {
            node = s2.pop();
            System.out.print(node.data + " ");

        }
    }


    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(19, head);
        head = bt.addNode(17, head);
        head = bt.addNode(11, head);
        head = bt.addNode(-11, head);

        BTTraversal traverse = new BTTraversal();
        traverse.postorderWithTwoStack(head);
    }

}
