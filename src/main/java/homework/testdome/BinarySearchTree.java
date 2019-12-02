package homework.testdome;

class MyNode {
    public int value;
    public MyNode left, right;

    public MyNode(int value, MyNode left, MyNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class BinarySearchTree {
	
    public static boolean contains(MyNode root, int value) {
    	if(root == null) {
    		return false;
    	}
    	
    	if(root.value == value) {
    		return true;
    	}else if(value <= root.value) {
    		return contains(root.left, value);
    	}else {
    		return contains(root.right, value);
    	}
    }
    
    public static void main(String[] args) {
        MyNode n1 = new MyNode(1, null, null);
        MyNode n3 = new MyNode(3, null, null);
        MyNode n2 = new MyNode(2, n1, n3);
        
        System.out.println(contains(n2, 3));
    }
}