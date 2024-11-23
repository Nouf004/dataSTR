import java.util.*;

public class invertedIndex {
   LinkedList<Word> inverList;

   invertedIndex() {
      inverList = new LinkedList<Word>();
   }// const

   void addWord(String str, int ID) {
      if (!search(str)) {
         Word w = new Word(str);
         w.document_IDs.insert(ID);
         inverList.insert(w);
      } // end if
      else {
         Word existWord = inverList.retrieve();
         existWord.addID(ID);
      }
   }// end method

   boolean search(String word) {
      if (inverList == null || inverList.empty())
         return false;
      inverList.FindFirst();
      while (!inverList.last()) {
         if (inverList.retrieve().text.equals(word)){
            return true;}
         inverList.FindNext();
      } // while
      if (inverList.retrieve().text.equals(word)){
         return true;}
      return false;
   }// end method
   

   void display() {
      inverList.FindFirst();
      while (!inverList.last()) {
         System.out.println(inverList.retrieve());
         inverList.FindNext();
      }
      System.out.println(inverList.retrieve());
   
   }
    
   public void diplayInvertedIndex(){
      if(inverList==null){
         System.out.println("null inverted index");
         return;
      }
      else if(inverList.empty()){
         System.out.println("empty inverted index list");
         return;
      }
      inverList.FindFirst();
      while(!inverList.last()){
         inverList.retrieve().display();
         inverList.FindNext();
      }
      inverList.retrieve().display();
   
   }
}
// this class is for searching and adding the uniqe words in list