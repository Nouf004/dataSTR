import java.util.*;
import java.io.*;


class Node <T> {

public T data;
public Node <T> next;
public Node <T> previous;

public Node (T val) {

data = val;
next = null;
previous = null;
}

}

public class DoubleLinkedList<T> {

Node <T> head;
Node <T> current;

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

public void FindPrevious() {
current = current.previous:
}

public T retrieve() {

return current.data;
}

public void update(T val){
current.data = val;
}

public boolean last() {
return current.next ==null;
}

public void insert (T val) {
Node <T> tmp= new Node <T> (val);

if (empty()) {
current = head = tmp;
}
else {
tmp.next = current.next; 
tmp.previous =current;

if(current.next != null) 
current.next.previous = tmp;

current.next = tmp;
current = tmp;
}
}

public void remove() {

if(current == head) {
head = head.next;

if (head!=null) 
head.previous = null;
}
else {
current.previous.next = current.next;

if(current.next != null)
current.next.previous = current.previous;
}
if (current.next == null)
current = head;

else 
current = current.next;
}
}
//=================


public class read { 

public void Load(String fileName)
try{ 
File f = new File(fileName);
Scanner s = new Scanner(f);

s.nextLine();

while(s.hasNextLine()) {
line = s.nextLine();

if(line.trim().length()<3) {
System.out.println("Empty line");
break;
}
System.out.println(line);


String str = file.next();

file.close();
}
catch(FileNotFoundException e) {
System.out.println("Error opening file");
system.exit(1);
}
}




