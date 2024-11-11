import java.util.*;
public class invertedIndex{
   DoubleLinkedList<Word> inverList;

   invertedIndex(){
      inverList=new DoubleLinkedList<Word>();
   }//const

   void addWord(String str, int ID){
      if(!search(str)){
         Word w= new Word(str);
         w.document_IDs.insert(ID);
         inverList.insert(w);
      }//end if
      else{
         Word existWord=inverList.retrieve();
         existWord.addID(ID);
      }
   }//end method

   boolean search(String word){
      if(inverList==null||inverList.empty())
         return false;
      inverList.FindFirst();
      while(!inverList.last()){
         if(inverList.retrieve().text.equals(word))
            return true;
         inverList.FindNext();
      }//while
      if(inverList.retrieve().text.equals(word))
         return true;
      return false;
   }//end method
}
