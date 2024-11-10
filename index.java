import java.util.*;

public class index{

   DoubleLinkedList<Document_ID> indexList=new  DoubleLinkedList<Document_ID>();
int ID;

   /*index(int id,Word text){
   ID=id;
   indexList=
   }*/

void addDoc(Document text){
//if(indexList.empty()
indexList.insert(text);
}//end method addDoc

void displayDoc(){
System.out.println(indexList.retrieve());
}//end method displa

}//end of class