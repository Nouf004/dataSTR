import java.util.*;
import java.io.*;

public class Document_ID {

    int ID;
    DoubleLinkedList<String> List_ID = new DoubleLinkedList<>();

    Document_ID(int id, DoubleLinkedList<String> Text) {
        ID = id;
        List_ID = Text;
    }

}
