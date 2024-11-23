import java.util.*;

public class index {

   LinkedList<Document> indexList;

   index() {
      indexList = new LinkedList<Document>();
   }

   void addDoc(Document text) {
      indexList.insert(text);
   }

   Document getID(int id) {
      if (indexList.empty()) {
         System.out.println("the document does not exists");
         return null;
      }
      indexList.FindFirst();
      while (!indexList.last()) {
         if (indexList.retrieve().ID == id)
            return indexList.retrieve();
         indexList.FindNext();
      }
      if (indexList.retrieve().ID == id)
         return indexList.retrieve();
      return null;

   }

   void displayDoc() {
      if (indexList == null) {
         System.out.println(indexList.retrieve());
         return;
      } else if (indexList.empty()) {
         System.out.println("empty");
         return;
      }
      indexList.FindFirst();
      while (!indexList.last()) {
         Document doc = indexList.retrieve();
         System.out.println("\n--------------------------------");
         System.out.println("ID:" + doc.ID);
         doc.List_ID.display();//*
         indexList.FindNext();
      } // end while
      Document doc = indexList.retrieve();
      System.out.println("\n--------------------------------");
      System.out.println("ID:" + doc.ID);
   doc.List_ID.display();//*

   }// end method displa
   
/////////////////////////////////////////////////////
   LinkedList<Integer> getallDocumentInTerms(String term) {
      LinkedList<Integer> list = new LinkedList<Integer>();
      if (indexList.empty()) {
         System.out.println("there is no document");
         return list;
      }
      indexList.FindFirst();
      while (!indexList.last()) {
         if (indexList.retrieve().List_ID.equals(term.toLowerCase().trim()))
            list.insert(indexList.retrieve().ID);
         list.FindNext();
      }
      if (indexList.retrieve().List_ID.equals(term.toLowerCase().trim()))
         list.insert(indexList.retrieve().ID);
      return list;

   }

   LinkedList<Integer> displayDoc(String term) {
      LinkedList<Integer> list = new LinkedList<Integer>();
      if (indexList.empty()) {
         System.out.println("there is no document");
         return list;
      }
      indexList.FindFirst();
      while (!indexList.last()) {
         if (indexList.retrieve().List_ID.equals(term.toLowerCase().trim()))
            list.insert(indexList.retrieve().ID);
         indexList.FindNext();
      }
      if (indexList.retrieve().List_ID.equals(term.toLowerCase().trim()))
         list.insert(indexList.retrieve().ID);
      return list;

   }

  
}// end of class
 // this class for id and doc
