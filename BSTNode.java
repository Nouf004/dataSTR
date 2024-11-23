public class BSTNode<T> {

    public int key;
    public T data;
    public BSTNode<T> left, right;

    public BSTNode(int k, T val) {

        key = k;
        data = val;
        left = right = null;

    }

}