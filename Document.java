import java.util.*;
import java.io.*;

public class Document{

    int ID;
    DoubleLinkedList<String> List_ID = new DoubleLinkedList<>();

    Document(int id, DoubleLinkedList<String> Text) {
        ID = id;
        List_ID = Text;
    }

}
//to take the id
