public class InvertedIndexBST {

BST<Word> inverted_index;

public InvertedIndexBST() {
inverted_index = new BST<Word>();

}

void addFromList(InvertedIndex inverted) { //adding from inverted list

if (inverted.inverted_index.empty() )
return;
inverted.inverted_index.FindFirst();
while(!inverted.inverted_index.last() ) {

inverted.inverted_index.insert(inverted.inverted_index.retrieve().text , inverted.inverted_index.retrieve() );

inverted.inverted_index.FindNext();

} //while

inverted.inverted_index.insert(inverted.inverted_index.retrieve().text , inverted.inverted_index.retrieve() );


}//end add from list


 void add(String str, int ID){
      if(!search(str)){
         Word w= new Word(str);
         w.document_IDs.insert(ID);
         inverted_index.insert(w);
      }//end if
      else{
         Word existWord=inverted_index.retrieve();
         existWord.addID(ID);
      }
   }//end method
 boolean search(String word){
      if(inverted_index==null||inverted_index.empty())
         return false;
      inverted_index.FindFirst();
      while(!inverted_index.last()){
         if(inverted_index.retrieve().text.equals(word))
            return true;
         inverted_index.FindNext();
      }//while
      if(inverted_index.retrieve().text.equals(word))
         return true;
      return false;
   }//end method

void displayInvertedIndex() {

if(inverted_index ==null) {
System.out.println("null");
return;
}
else if (inverted_index.empty() ) {
System.out.println("it is empty");
return;
}
inverted_index.inOrder();
}
} //end class
