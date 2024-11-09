
import java.util.*;
import java.io.*;

public class DoubleLinkedList<T> {

    Node<T> head;
    Node<T> current;

    DoubleLinkedList() {
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

    public void FindPrevious() {
        current = current.previous;
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
        Node<T> tmp = new Node<T>(val);

        if (empty()) {
            current = head = tmp;
        } else {
            tmp.next = current.next;
            tmp.previous = current;

            if (current.next != null)
                current.next.previous = tmp;

            current.next = tmp;
            current = tmp;
        }
    }

    public void remove() {

        if (current == head) {
            head = head.next;

            if (head != null)
                head.previous = null;
        } else {
            current.previous.next = current.next;

            if (current.next != null)
                current.next.previous = current.previous;
        }
        if (current.next == null)
            current = head;

        else
            current = current.next;
    }
}
