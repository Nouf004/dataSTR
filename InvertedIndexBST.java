public class InvertedIndexBST {

   BST<Word> inverted_indexbst;

   public InvertedIndexBST() {
      inverted_indexbst = new BST<Word>();

   }

   void addFromList(invertedIndex inverted) { // adding from inverted list

      if (inverted.inverList.empty())
         return;
      inverted.inverList.FindFirst();
      while (!inverted.inverList.last()) {

         inverted_indexbst.insert(inverted.inverList.retrieve().text, inverted.inverList.retrieve());

         inverted.inverList.FindNext();

      } // while

      inverted_indexbst.insert(inverted.inverList.retrieve().text, inverted.inverList.retrieve());

   }// end add from list

   void add(String str, int ID) {
      if (!search(str)) {
         Word w = new Word(str);
         w.document_IDs.insert(ID);
         inverted_indexbst.insert(str, w);
      } // end if
      else {
         Word existWord = inverted_indexbst.retrieve();
         existWord.addID(ID);
      }
   }// end method

   boolean search(String word) {
      return inverted_indexbst.findkey(word);
   }// end method

   void displayInvertedIndex() {

      if (inverted_indexbst == null) {
         System.out.println("null");
         return;
      } else if (inverted_indexbst.empty()) {
         System.out.println("it is empty");
         return;
      }
      inverted_indexbst.inOrder();
   }
} // end class
