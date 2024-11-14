public class BST<T> {

BSTNode<T> root, current;

BST() {

root = cuurent = null;

}

boolean empty() {

return root ==null;
}



boolean full() {
return false;
}

T retrieve() {

return current.data;
}

boolean findkey (int tkey) {
BSTNode<T> p = root , q= root;

if (empty())
return false;

while(p != null) {

q =p;
if (p.key ==tkey) {
current = p;
return true;
}

else if(tkey <p.key)
p = p.left;

else 
p = p.right;
}

current =q;
return false;
}

boolean insert(int k, T val) {
BSTNode<T> p, q = current;

if (findkey(k)) {

current = q;
return false;
}

p = new BSTNode<T> (k, val);

if(empty()) {

root= current = p;
return true;
}
else {
if (k < current.key) 
current.left =p;
else 
current.right = p;

current = p;
return true;
}

} //end insert

boolean remove_key (int tkey) {

BooleanWrapper removed = new Boolean (false);

BSTNode<T> p;
p = remove_aux(tkey , root, removed);
current = root = p;
return removed;

}//end remove key

private BSTNode<T> remove_aux(int key, BSTNode<T> p , BooleanWrapper flag){

BSTNode<T> q, child = null;

if(p==null) 
return null;

if(key<p.key) 
p.left = remove_aux(key, p.left , flag);
else if(key>p.key)
p.right = remove_aux(key, p.right , flag);

else {
flag.set(true);
if(p.left!= null && p.right !=null) {
q = find_min(p.right);
p.key = q.key;
p.data = q.data;
p.right = remove_aux(q.key, p.right, flag);
}

else {
if (p.right ==null) 
child = p.left;
else 
if(p.left == null) 
child = p.right;
return child; 
}
}
return p ;

} //remove aux

private BSTNode<T> find_min(BSTNode<T> p) {

if (p==null) 
return null;

while(p.left !==null) {
p=p.left;
}

return p;
} //end find min

boolean update( int key, T data) {
 
remove_key(current.key);

return insert(key,data);
}//end update


void inOrder() {

if (root==null)
System.out.println("the tree is empty");

inOrder(root);

}//end inorder


private void inOrder(BSTNode p) {


if(p ==null)
return;
inOrder(p.left);
System.out.println("key=" + p.key);
System.out.println(p.data);
inOrder(p.right);
}


void preOrder() {

if(root==null) 
System.out.println("the tree is empty");
else
preOrder(root);
}

private void preOrder(BSTNode p) {


if(p ==null)
return;
System.out.println("key=" + p.key);
System.out.println(p.data.toString());
preOrder(p.left);
preOrder(p.right);

}





} //end class
