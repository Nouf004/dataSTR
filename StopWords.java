import java.io.*;
import java.util.*;

class StopWords{
   index index1;
   invertedIndex invers;
   DoubleLinkedList<String> stop;

   StopWords(){
      stop=new DoubleLinkedList<String>();
      index1=new index();
      invers=new invertedIndex();
   }

   void Load_Stop(String fileName){
      try {
         File f = new File(fileName);
         Scanner s = new Scanner(f);
           
         while (s.hasNextLine()) {
            String line = s.nextLine();
            stop.insert(line);
         }
      } catch (Exception e) {
         System.out.println("Error opening file");
         System.exit(1);
      }
   
   
   }//end method

}//end of class
