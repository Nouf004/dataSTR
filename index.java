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

   public LinkedList<Integer> getallDocumentInTerms(String term) {
      LinkedList<Integer> result = new LinkedList<>();
   
      indexList.FindFirst();
      while (!indexList.last()) {
         Document doc = indexList.retrieve();
         if (docContainsTerm(doc, term)) {
            result.insert(doc.ID);
         }
         indexList.FindNext();
      }
   
        // Check the last document
      Document doc = indexList.retrieve();
      if (docContainsTerm(doc, term)) {
         result.insert(doc.ID);
      }
      return result;
   }  
       
   LinkedList<Integer> displayDoc(String term) {
   LinkedList<Integer> list = new LinkedList<>();
   
   if (indexList.empty()) { 
      System.out.println("There is no document in the index.");
      return list;
   }

   indexList.FindFirst();
   while (!indexList.last()) {
      Document doc = indexList.retrieve();
      if (docContainsTerm(doc, term)) { // Check if the term exists in the document
         list.insert(doc.ID);
      }
      indexList.FindNext();
   }

   // Check the last document
   if (docContainsTerm(indexList.retrieve(), term)) {
      list.insert(indexList.retrieve().ID);
   }

   if (list.empty()) {
      System.out.println("No documents contain the term '" + term + "'.");
   }

   return list;
}
    
   private boolean docContainsTerm(Document doc, String term) {
      if (doc.List_ID == null || doc.List_ID.empty()) {
         return false; // No terms in the document
      }
   
      doc.List_ID.FindFirst();
      while (!doc.List_ID.last()) {
         if (doc.List_ID.retrieve().equalsIgnoreCase(term)) {
            return true;
         }
         doc.List_ID.FindNext();
      }
   
        // Check the last term
      return doc.List_ID.retrieve().equalsIgnoreCase(term);
   }

  
}// end of class
 // this class for id and doc