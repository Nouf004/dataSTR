
import java.util.*;
import java.io.*;

public class LinkedList<T>{

   private Node<T> head;
   private Node<T> current;
   int n = 0;

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
      n++;
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

   public boolean exist(T x) {
      Node<T> tmp = head;
      while (tmp != null) {
         if (tmp.data.equals(x))
            return true;
         tmp = tmp.next;
      }
      return false;
   }

   public void display() {
      if (this == null)
         System.out.println("List is null");
      if (head == null)
         System.out.println("List is empty");
      Node<T> p = head;
      while (p != null) {
         System.out.print(p.data + " ");
         p = p.next;
      }

   }

}

// list