
import java.util.*;
import java.io.*;

public class LinkedList<T> {

   Node<T> head;
   Node<T> current;

   LinkedList() {
      head = current = null;

   }

   public boolean empty() {
      return head == null;
   }

   public boolean full() {

      return false;
   }

   public void FindFirst() {
      current = head;
   }

   public void FindNext() {
      current = current.next;
   }

   public T retrieve() {

      return current.data;
   }

   public void update(T val) {
      current.data = val;
   }

   public boolean last() {
      return current.next == null;
   }

   public void insert(T val) {
      Node<T> tmp;

      if (empty()) {
         current = head = new Node<T>(val);
      } else {
         tmp = current.next;
         current.next = new Node<T>(val);
         current = current.next;
         current.next = tmp;
      }
   }

   public void remove() {

      if (current == head) {
         head = head.next;
      } else {
         Node<T> tmp = head;

         while (tmp.next != current)
            tmp = tmp.next;
         tmp.next = current.next;
      }
      if (current.next == null)
         current = head;
      else
         current = current.next;
   }

   public void display() { // complete it
   }
}
// list