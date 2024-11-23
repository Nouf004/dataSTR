

public class BST_int<T> {

    private BSTNode<T> root, current;

    public BST_int() {
        current = root = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {

        return current.data;
    }

    public boolean findKey(int k) {

        BSTNode<T> p = root;
        while (p != null) {
            current = p;

            if (k == (p.key)) {
                return true;
            } else if (k > p.key) {
                p = p.right;
            }

            else {
                p = p.left;
            }
        }
        return false;
    }

    public boolean insert(int k, T val) {

        if (root == null) {

            current = root = new BSTNode<T>(k, val);
            return true;
        }

        BSTNode<T> p = root;
        while (p != null) {
            current = p;
            if (k < p.key) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        BSTNode<T> tmp = new BSTNode<T>(k, val);

        if (k < current.key) {
            current.left = tmp;
        }

        else {
            current.right = tmp;
        }

        current = tmp;
        return true;
    }

    public void decreasing(BSTNode p) {

        if (root == null)
            return;

        decreasing(p.right);
        System.out.println(p.data);
        System.out.println("    " + p.key);
        decreasing(p.left);

    }

    public void display_decreasing() {
        if (root == null)
            System.out.println("empty tree");

        else
            System.out.println("DocID     Score");
        decreasing(root);
    }

}
// end class