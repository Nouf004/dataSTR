import java.util.*;
import java.io.*;

class Node<T> {

    public T data;
    public Node<T> next;
    public Node<T> previous;

    public Node(T val) {

        data = val;
        next = null;
        previous = null;
    }

}
//node
