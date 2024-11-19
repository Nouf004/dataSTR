import java.io.*;
import java.util.*;

class StopWords{
   index index1;
   invertedIndex invers;
   InvertedIndexBST invertedBST;
   LinkedList<String> stop;

   StopWords(){
      stop=new LinkedList<String>();
      index1=new index();
      invers=new invertedIndex();
       invertedBST = new InvertedIndexBST();

   }

   void Load_Stop(String fileName){
      try {
         File f = new File(fileName);
         Scanner s = new Scanner(f);
            
         while (s.hasNextLine()) {
            String line = s.nextLine(); 
            stop.insert(line);
         }
      }//  catch (Exception e) {
//          System.out.println("Error opening file");
//          System.exit(1);

catch (IOExecption e) {
//e.printStackTrace();
      }
   }//end method

public void loadAllDocs (String fileName) {

String line = null;

try {
File f = new File(fileName);
Scanner s = new Scanner(f);
  
while (s.hasNextLine()) {

line= s.nextLine(); //skips the header in the file

if(line.trim().length()<3) {
System.out.println("Empty line, skipping=" + line);
break; //to skip that line
}

String x = line.substring(0, line.indexOf(','));
int id = Integer.parseInt(x.trim());
String content = line.substring(line.indexOf(',')+1).trim();
LinkedList<String>wordsInDoc = make_words_inverted(content,id);
index1.add_document(new Document(id, addWord));
   
}
}
catch (Exception e) {
System.out.println("end of file");

}
}

LinkedList<String> make_words_inverted (String content, int id){

LinkedList<String> words = new LinkedList<String>();
createIndex_InvertedIndex(content,words,id);
return words;
}

void createIndex_InvertedIndex(String content, LinkedList<String> , int id){

content = content.toLowerCase().replaceAll("[^a-zA-Z0-9]" , "");
String[] tokens = content.split("\\s+");
for (int i = 0; i<tokens.length; i++) {
if (!existStopWords(words)) {
wordsInDoc.insert(words);
invers.add(words , id);
}
}
}


boolean existStopWords(String word) {

      if(inverList==null||inverList.empty())
         return false;
      inverList.FindFirst();
      while(!inverList.last()){
         if(inverList.retrieve().equals(word))
            return true;
         inverList.FindNext();
      }//while
      if(inverList.retrieve().equals(word))
         return true;
      return false;
   }//end method

void loadAllFiles (String Sfile , String docFiles) {
 Load_Stop(Sfile);
 loadAllDocs(docFiles);
 }


}//end of class

// main?
// // 
// void displayDocWithID(LinkedList<Integer>id) {
// 
// if(id.empty()) {
// System.out.println("Documents do not exist");
// return;
// }
// id.FindFirst();
// while(!id.last()) {
// 
// Document d = index1.
