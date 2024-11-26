import java.util.*;
import java.io.*;

public class Word {

   LinkedList<Integer> document_IDs;
   String text;

   Word(String Text) {
      document_IDs = new LinkedList<Integer>();
      text = Text;
   
   }

   void addID(int ID) {
      if (!exists(ID))
         document_IDs.insert(ID);
   
   }

   void display() {
      System.out.println("\n-----------------------------");
      System.out.print("word: " + text);
      System.out.print(" [");
      System.out.print(document_IDs.toString()); // Use the result of toString()
      System.out.println("]");
   }

   boolean exists(int id) {
   
      if (document_IDs.empty())
         return false;
      document_IDs.FindFirst();
      while (!document_IDs.last()) {
         if (document_IDs.retrieve().equals(id)){
            return true;}
         document_IDs.FindNext();
      }
      if (document_IDs.retrieve().equals(id))
         return true;
   
      return false;
   
   }
   public String toString() {
   
   return "Word:" + text + "Document ID:" + document_IDs;
   
   }

}
// insert the id of document for the word that has appered in it