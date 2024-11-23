
public class BST<T> {

    private BSTNode1<T> root, current;

    BST() {

        root = current = null;

    }

    boolean empty() {

        return root == null;
    }

    boolean full() {
        return false;
    }

    T retrieve() {

        return current.data;
    }

    boolean findkey(String tkey) {
        BSTNode1<T> p = root;

        while (p != null) {

            current = p;
            if (tkey.compareToIgnoreCase(p.key) == 0) {
                return true;
            }

            else if (tkey.compareToIgnoreCase(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        return false;
    }

    boolean insert(String k, T val) {
        if (root == null) {
            current = root = new BSTNode1<T>(k, val);
            return true;
        }

        BSTNode1<T> p = current;

        if (findkey(k)) {

            current = p;
            return false;
        }
        BSTNode1<T> tmp = new BSTNode1<T>(k, val);

        if (k.compareToIgnoreCase(current.key) < 0)
            current.left = tmp;
        else
            current.right = tmp;

        current = tmp;
        return true;

    } // end insert

    /*
     * boolean remove_key(int tkey) {
     * 
     * BooleanWrapper removed = new BooleanWrapper(false);
     * 
     * BSTNode<T> p;
     * p = remove_aux(tkey, root, removed);
     * current = root = p;
     * return removed.get();
     * 
     * }// end remove key
     * 
     * private BSTNode<T> remove_aux(int key, BSTNode<T> p, BooleanWrapper flag) {
     * 
     * BSTNode<T> q, child = null;
     * 
     * if (p == null)
     * return null;
     * 
     * if (key < p.key)
     * p.left = remove_aux(key, p.left, flag);
     * else if (key > p.key)
     * p.right = remove_aux(key, p.right, flag);
     * 
     * else {
     * flag.set(true);
     * if (p.left != null && p.right != null) {
     * q = find_min(p.right);
     * p.key = q.key;
     * p.data = q.data;
     * p.right = remove_aux(q.key, p.right, flag);
     * }
     * 
     * else {
     * if (p.right == null)
     * child = p.left;
     * else if (p.left == null)
     * child = p.right;
     * return child;
     * }
     * }
     * return p;
     * 
     * } // remove aux
     */

    /*
     * private BSTNode<T> find_min(BSTNode<T> p) {
     * 
     * if (p == null)
     * return null;
     * 
     * while (p.left != null) {
     * p = p.left;
     * }
     * 
     * return p;
     * } // end find min
     * 
     * boolean update(int key, T data) {
     * 
     * remove_key(current.key);
     * 
     * return insert(key, data);
     * }// end update
     */

    void inOrder() {

        if (root == null)
            System.out.println("the tree is empty");

        inOrder(root);

    }// end inorder

    private void inOrder(BSTNode1 p) {

        if (p == null)
            return;
        inOrder(p.left);
        // System.out.println("key=" + p.key);
        // System.out.println(p.data);
        ((Word) p.data).display();

        inOrder(p.right);
    }

    void preOrder() {

        if (root == null)
            System.out.println("the tree is empty");
        else
            preOrder(root);
    }

    private void preOrder(BSTNode1 p) {

        if (p == null)
            return;
        System.out.println("key=" + p.key);
        System.out.println(p.data.toString());
        preOrder(p.left);
        preOrder(p.right);

    }

} // end class