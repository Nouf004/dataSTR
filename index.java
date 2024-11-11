import java.util.*;

public class index{

   DoubleLinkedList<Document> indexList;

   index(){
      indexList=new  DoubleLinkedList<Document>();
   }

   void addDoc(Document text){
      indexList.insert(text);
   }

   void displayDoc(){
      if (indexList==null){
         System.out.println(indexList.retrieve());
         return;
      }
      else if(indexList.empty()){
         System.out.println("empty");
         return;
      }
      indexList.FindFirst();
      while (!indexList.last()) {
         Document doc= indexList.retrieve();
         System.out.println("\n--------------------------------");
         System.out.println("ID:"+doc.ID);
         indexList.FindNext();
      }//end while
      Document doc= indexList.retrieve();
      System.out.println("\n--------------------------------");
      System.out.println("ID:"+doc.ID);
   }//end method displa

}//end of class
