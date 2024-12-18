import java.io.*;
import java.util.*;

class StopWords {
   static index index1;
   invertedIndex invers;
   InvertedIndexBST invertedBST;
   LinkedList<String> stop;
   int numOftokens = 0;
   LinkedList<String> unique = new LinkedList<>();

   StopWords() {
      stop = new LinkedList<String>();
      index1 = new index();
      invers = new invertedIndex();
      invertedBST = new InvertedIndexBST();
   
   }

   void Load_Stop(String fileName) {
      try {
         File f = new File(fileName);
        
         Scanner s = new Scanner(f);
         while (s.hasNextLine()) {
            String line = s.nextLine();
            stop.insert(line);
         }
         s.close();//check here
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void loadAllDocs(String fileName) {
      try {
         File file = new File(fileName);
         if (!file.exists()) { // Check if the file exists
            System.out.println("File not found: " + fileName);
            return;
         }
      
         System.out.println("Loading documents from: " + fileName); // Debug
         Scanner scanner = new Scanner(file);
      
         boolean isHeader = true; // Flag to skip the header
         while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
         
            // Skip header or empty lines
            if (isHeader) {
               isHeader = false;
               continue;
            }
         
            // Check if the line is invalid (e.g., missing ID or content)
            if (line.isEmpty() || !line.contains(",") || line.split(",").length < 2) {
               System.out.println("Skipping invalid or empty line: " + line);
               continue;
            }
         
            try {
                // Extract document ID and content
               String[] parts = line.split(",", 2); // Split into at most two parts
               int id = Integer.parseInt(parts[0].trim()); // Parse ID
               String content = parts[1].trim(); // Get content
            
                // Debug info
               System.out.println("Document ID: " + id);
               System.out.println("Content: " + content);
            
                // Process the document
               LinkedList<String> wordsInDoc = make_words_inverted(content, id);
               index1.addDoc(new Document(id, wordsInDoc, content));
            } catch (NumberFormatException e) {
               System.out.println("Error parsing line, invalid ID: " + line);
            } catch (Exception e) {
               System.out.println("Unexpected error processing line: " + line);
               e.printStackTrace();
            }
         }
         scanner.close(); // Close the scanner
      } catch (IOException e) {
         e.printStackTrace();
      }
   }   

      
     

   LinkedList<String> make_words_inverted(String content, int id) {
      LinkedList<String> words = new LinkedList<String>();
   
    // Preprocess content
      content = content.replaceAll("'", " "); // Replace single quotes
      content = content.replaceAll("-", " "); // Replace hyphens
      content = content.toLowerCase().replaceAll("[^a-z0-9\\s]", ""); // Keep alphanumeric and spaces
   
    // Split content into tokens
      String[] tokens = content.split("\\s+"); // Split by whitespace
      numOftokens += tokens.length;
   
      for (String token : tokens) {
        // Check if the token is not a stop word
         if (!existStopWords(token)) {
            words.insert(token); // Add word to document list
            invers.addWord(token, id); // Add word to inverted index
            invertedBST.add(token, id); // Add word to BST if used
         } else {
            System.out.println("Skipping stop word: " + token); // Debug skipped stop words
         }
      
        // Add to unique words list if it's new
         if (!unique.exist(token)) {
            unique.insert(token);
         }
      }
   
      return words;
   }
      
   void createIndex_InvertedIndex(String content, LinkedList<String> wordsInDoc, int id) {
      content = content.replaceAll("\'", " ");
      content = content.replaceAll("-", " ");
      content = content.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
      String[] tokens = content.split("\\s+");
      numOftokens += tokens.length;
      for (String w : tokens) {
         if (!existStopWords(w)) {
            wordsInDoc.insert(w);
            invers.addWord(w, id);
            invertedBST.add(w, id);
         }
         if (!unique.exist(w))
            unique.insert(w);
      }
   }

   boolean existStopWords(String word) {
      if (stop == null || stop.empty()) 
         return false; // Handle null or empty stop word list
   
      stop.FindFirst(); // Start at the first element in the stop list
      while (!stop.last()) {
         if (stop.retrieve().equalsIgnoreCase(word)) {
            return true; // The word is a stop word
         }
         stop.FindNext();
      }
   
    // Check the last element
      return stop.retrieve().equalsIgnoreCase(word);
   } 
     
   void loadAllFiles(String Sfile, String docFiles) {
      Load_Stop(Sfile);
      loadAllDocs(docFiles);
   }

   void displayDocWithID() {
      stop.display();
   }

   void displayDocWithID(LinkedList<Integer> id) {
      if (id.empty()) {
         System.out.println("No documents found");
         return;
      }
      id.FindFirst();
      while (!id.last()) {
         Document d = index1.getID(id.retrieve());
         if (d != null) {
            System.out.println("Document: " + d.ID + ":" + d.content);
         }
         id.FindNext();
      }
      Document d = index1.getID(id.retrieve());
      if (d != null) {
         System.out.println("Document: " + d.ID + ":" + d.content);
      }
      System.out.println("");
   }

   public static void testmenu() {
   
      StopWords s = new StopWords();
      s.loadAllFiles("stop.txt", "dataset.csv");
      s.index1.displayDoc();
      System.out.println("\n--------------------------");
      s.invers.diplayInvertedIndex();//come
      Scanner input = new Scanner(System.in);
   
      int ch = 0;
      do {
      
         displayMenu();
      
         ch = input.nextInt();
         switch (ch) {
         
            case 1:
               System.out.println("Enter a word to retrieve it");
               String term = input.next();
               term = term.toLowerCase().trim();
            
               System.out.println(":using index with the lists");
               LinkedList<Integer> result = StopWords.index1.displayDoc(term); // error come back later
               System.out.print("word:" + term + "[");
               result.display();
               System.out.println("]");
               System.out.println("-----------------------------------------------------");
            
               System.out.println("-inverted index with lists");
               boolean found = s.invers.search(term);
            
               if (found)
                  s.invers.inverList.retrieve().display();
               else
                  System.out.println("term not found in inverted list index list.");
            
               System.out.println("-inverted index with BST");
            
               boolean found2 = s.invertedBST.search(term);
            
               if (found2)
                  s.invers.inverList.retrieve().display();
               
               else
                  System.out.println("term not found in inverted list index list.");
            
               break; // end case1
         
            case 2:
               input.nextLine();
               System.out.println("enter a query to retrieve");
               String Q = input.nextLine();
               Q = Q.toLowerCase();
               Q = Q.replaceAll(" and ", " AND ");
               Q = Q.replaceAll(" or ", " OR ");
            
               System.out.println(
                     "which method would you want to make a query? \n 1-using index with lists.\n 2- inverted index with lists.\n 3- inverted index with BST.");
            
               int x = input.nextInt();
               do {
                  if (x == 1) {
                     if (StopWords.index1 == null) {
                        System.out.println("Error: StopWords.index1 is null.");
                        return;
                     }
                  
                     if (StopWords.index1 == null) {
                        System.out.println("Error: StopWords.index1 is null.");
                        return;
                     }
                  
                     QueryProcessingFromIndex q = new QueryProcessingFromIndex(StopWords.index1);
                  
                  // Check if the query is valid
                     System.out.println("Processing query: " + Q);
                     LinkedList<Integer> res = QueryProcessingFromIndex.mixedQuery(Q);
                  
                  // Check if mixedQuery returned results
                     if (res == null || res.empty()) {
                        System.out.println("No results found for query: " + Q);
                        return;
                     }
                  
                  // Check if the 's' object is initialized
                     if (s == null) {
                        System.out.println("Error: Object 's' is null.");
                        return;
                     }
                  
                  // Display the documents
                     System.out.println("------------" + Q + "-------------");
                     s.displayDocWithID(res);
                  }                        
                  
                  else if (x == 2) {
                     QAndOr q = new QAndOr(s.invers);
                     System.out.println("------------" + Q + "-------------");
                     LinkedList<Integer> res1 = QAndOr.mixedQuery(Q);
                     s.displayDocWithID(res1);
                  
                  }
                  
                  else if (x == 3) {
                     QBST q = new QBST(s.invertedBST);
                     System.out.println("------------" + Q + "-------------");
                     LinkedList<Integer> res1 = QBST.mixedQuery(Q);
                     s.displayDocWithID(res1);
                  
                  } else if (x == 4)
                     break;
                  
                  else
                     System.out.println("wrong query");
               
                  System.out.println(
                        "which method would you want to make a query? \n 1-using index with lists.\n 2- inverted index with lists.\n 3- inverted index with BST.");
                  x = input.nextInt();
               } while (x != 4);
               break;
         
            case 3:
               input.nextLine();
               System.out.println("Enter a query to rank it");
               String Q1 = input.nextLine();
               Q1 = Q1.toLowerCase();
            
               Ranking R5 = new Ranking(s.invertedBST, index1, Q1);
               R5.insertStoredInList();
               R5.displayAllDoc();
            
               break;
         
            case 4:
               s.index1.displayDoc();
               System.out.println("----------------------------");
               break;
         
            case 5:
            
               System.out.println("num of documents=" + StopWords.index1.indexList.n);
               System.out.println("----------------------------");
            
               break;
            case 6:
            
               System.out.println("num of uniqe words without stop words=" + s.invers.inverList.n);
               System.out.println("----------------------------");
            
               break;
         
            case 7:
               s.invers.display();
               break;
         
            case 8:
               s.invertedBST.displayInvertedIndex();
               break;
         
            case 9:
               System.out.println("num of tokens=" + s.numOftokens);
               System.out.println("num of unique words invluding stop words=" + s.stop.n);
               break;
         
            case 10:
               System.out.println("Menu exited");
               break;
            default:
               System.out.println("Error!");
         }// switch
      } while (ch != 10);
   
   } // end main

   public static void displayMenu() {
      System.out.println(
            "1- Retrieve a word (choices are available: using index with lists. - inverted index with lists. - inverted index with BST.");
      System.out.println("2- Boolean retrieval");
      System.out.println("3- Ranked retrieval");
      System.out.println("4- Indexed Documents: print all documents");
      System.out.println("5- Number of documents in the index");
      System.out.println("6- number of unique words in index");
      System.out.println("7- show inverted index with List of lists");
      System.out.println("8- show inverted index with BST");
      System.out.println("9- Indexed tokens, to show the no. of vocabulary & tokens in the index");
      System.out.println("10- EXIT");
   
   }
   public static void main(String[]args){
      testmenu();
   }
}// end of class