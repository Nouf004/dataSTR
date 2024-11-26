public class QueryProcessingFromIndex {

   static index index1;

   QueryProcessingFromIndex(index ind) {
      index1 = ind;
   }

   static LinkedList<Integer> Boolean_Query(String q) {
      if (!q.contains("AND") && !q.contains("OR"))
         return AndQuery(q);
      else if (q.contains("AND") && !q.contains("OR"))
         return AndQuery(q);
      else if (!q.contains("AND") && q.contains("OR"))
         return OrQuery(q);
      else
         return mixedQuery(q);
   
   }

   static LinkedList<Integer> mixedQuery(String q) {
      LinkedList<Integer> X = new LinkedList<Integer>();
      LinkedList<Integer> Y = new LinkedList<Integer>();
   
      if (q.length() == 0)
         return X;
      String[] ors = q.split("OR");
      X = AndQuery(ors[0]);
      for (int i = 1; i < ors.length; i++) {
         Y = AndQuery(ors[i]);
         X = OrQuery(X, Y);
      }
      return X;
   }

   static LinkedList<Integer> AndQuery(String q) {
      LinkedList<Integer> X = new LinkedList<Integer>();
      LinkedList<Integer> Y = new LinkedList<Integer>();
      String[] terms = q.split("AND");
   
      if (q.length() == 0)
         return X;
      X = index1.getallDocumentInTerms(terms[0].trim().toLowerCase());
      for (int i = 1; i < terms.length; i++) {
         Y = index1.getallDocumentInTerms(terms[i].trim().toLowerCase());
         X = AndQuery(X, Y);
      }
      return X;
   }

   static LinkedList<Integer> AndQuery(LinkedList<Integer> X, LinkedList<Integer> Y) {
      LinkedList<Integer> Z = new LinkedList<Integer>(); // result
      if (X.empty() || Y.empty()) {
         return Z;
      }
      while (true) {
         boolean find = existsInResult(Z, X.retrieve());
         if (!find) {
            Y.FindFirst();
            while (true) {
               if (Y.retrieve().equals(X.retrieve())) {
                  Z.insert(X.retrieve());
                  break;
               }
               if (!Y.last())
                  Y.FindNext();
               else
                  break;
            }
         }
         if (!X.last())
            X.FindNext();
         else
            break;
      }
      return Z;
   }

   static LinkedList<Integer> OrQuery(String q) {
      LinkedList<Integer> X = new LinkedList<Integer>();
      LinkedList<Integer> Y = new LinkedList<Integer>();
      String[] terms = q.split("OR");
   
      if (q.length() == 0)
         return X;
      X = index1.getallDocumentInTerms(terms[0].trim().toLowerCase());
      for (int i = 1; i < terms.length; i++) {
         Y = index1.getallDocumentInTerms(terms[i].trim().toLowerCase());
         X = AndQuery(X, Y);
      }
      return X;
   
   }

   static LinkedList<Integer> OrQuery(LinkedList<Integer> X, LinkedList<Integer> Y) {
      LinkedList<Integer> Z = new LinkedList<Integer>(); // result
      if (X.empty() && Y.empty())
         return Z;
      X.FindFirst();
   
      while (!X.empty()) {
         boolean find = existsInResult(Z, Y.retrieve());
         if (!find) {
            Z.insert(X.retrieve());
         }
         if (!X.last())
            X.FindFirst();
         else
            break;
      }
      Y.FindFirst();
      while (!Y.empty()) {
         boolean find = existsInResult(Z, Y.retrieve());
         if (!find) {
            Z.insert(X.retrieve());
         }
         if (!X.last())
            X.FindFirst();
         else
            break;
      }
      return Z;
   }

   static boolean existsInResult(LinkedList<Integer> result, int id) {
      if (result.empty())
         return false;
   
      result.FindFirst();
      while (!result.last()) {
         if (result.retrieve().equals(id)) {
            return true;
         }
         result.FindNext();
      } // end while
      if (result.retrieve().equals(id)) {
         return true;
      }
      return false;
   
   }

}