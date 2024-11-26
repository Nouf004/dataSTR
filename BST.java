
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
      System.out.println(p.data);//
      preOrder(p.left);
      preOrder(p.right);
   }
   

} // end class