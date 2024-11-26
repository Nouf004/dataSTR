public class BSTNode1<T> {

   public String key;
   public T data;
   public BSTNode1<T> left, right;

   public BSTNode1(String k, T val) {
   
      key = k;
      data = val;
      left = right = null;
   
   }

}