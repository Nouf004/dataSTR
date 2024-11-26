import java.util.*;
import java.io.*;

public class Document {

   int ID;
   LinkedList<String> List_ID = new LinkedList<>();
   String content;

   Document(int id, LinkedList<String> Text, String cont) {
      ID = id;
      List_ID = Text;
      content = cont;
   
   }

}
// to take the id