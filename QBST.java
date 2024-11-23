public class QBST {

    static InvertedIndexBST invert;

    public QBST(InvertedIndexBST inver) {
        invert = inver;
    }
    
      static LinkedList<Integer> Boolean_Query(String q) {
        if (!q.contains("AND") && !q.contains("OR"))
            return AndQ(q);
        else if (q.contains("AND") && !q.contains("OR"))
            return AndQ(q);
        else if (!q.contains("AND") && q.contains("OR"))
            return OrQ(q);
        else
            return mixedQuery(q);

    }
      static LinkedList<Integer> mixedQuery(String q) {
        LinkedList<Integer> X = new LinkedList<Integer>();
        LinkedList<Integer> Y = new LinkedList<Integer>();

        if (q.length() == 0)
            return X;
        String[] ors = q.split("OR");
        X = AndQ(ors[0]);
        for (int i = 1; i < ors.length; i++) {
            Y = AndQ(ors[i]);
            X = OrQ(X, Y);
        }
        return X;
    }

    public static LinkedList<Integer> AndQ(String Q) {

        LinkedList<Integer> X = new LinkedList<Integer>();
        LinkedList<Integer> Y = new LinkedList<Integer>();
        String LAnd[] = Q.split("AND");

        if (LAnd.length == 0)
            return X;
        boolean find = invert.search(LAnd[0].trim().toLowerCase());
        if (find) {
            X = invert.inverted_index.retrieve().document_IDs;
        }
        for (int i = 1; i < LAnd.length; i++) {
            find = invert.search(LAnd[i].trim().toLowerCase());
            if (find)
                Y = invert.inverted_index.retrieve().document_IDs;
            X = AndQ(X, Y);
        }
        return X;

    }

    public static LinkedList<Integer> AndQ(LinkedList<Integer> A, LinkedList<Integer> B) {

        LinkedList<Integer> X = new LinkedList<Integer>();
        if (A.empty() || B.empty())
            return X;
        A.FindFirst();
        while (true) {
            boolean find = existent_results(X, A.retrieve());
            if (!find) {
                B.FindFirst();
                while (true) {
                    if (B.retrieve().equals(A.retrieve())) {
                        X.insert(A.retrieve());
                        break;
                    } // end inner if
                    if (!B.last())
                        B.FindNext();
                    else
                        break;
                } // end inner while

            } // end if
            if (!A.last())
                A.FindNext();
            else
                break;

        } // end while
        return X;
    }

    public static LinkedList<Integer> OrQ(String Q) {
        LinkedList<Integer> X = new LinkedList<Integer>();
        LinkedList<Integer> Y = new LinkedList<Integer>();
        String LOr[] = Q.split("OR");

        if (LOr.length == 0)
            return X;
        boolean find = invert.search(LOr[0].trim().toLowerCase());
        if (find) {
            X = invert.inverted_index.retrieve().document_IDs;
        }
        for (int i = 1; i < LOr.length; i++) {
            find = invert.search(LOr[i].trim().toLowerCase());
            if (find)
                Y = invert.inverted_index.retrieve().document_IDs;
            X = OrQ(X, Y);
        }
        return X;

    }

    public static LinkedList<Integer> OrQ(LinkedList<Integer> A, LinkedList<Integer> B) {

        LinkedList<Integer> X = new LinkedList<Integer>();
        if (A.empty() && B.empty())
            return X;
        A.FindFirst();
        while (!A.empty()) {
            boolean find = existent_results(X, A.retrieve());
            if (!find) {
                X.insert(A.retrieve());
            } // end if
            if (!A.last())
                A.FindNext();
            else
                break;
        } // end while
        B.FindFirst();
        while (!B.empty()) {
            boolean find = existent_results(X, B.retrieve());
            if (!find) {
                X.insert(B.retrieve());
            } // end if
            if (!B.last())
                B.FindNext();
            else
                break;

        }
        return X;
    }

    public static boolean existent_results(LinkedList<Integer> result, Integer ID) {
        if (result.empty())
            return false;
        result.FindFirst();
        while (!result.last()) {
            if (result.retrieve().equals(ID)) {
                return true;
            }
            result.FindNext();
        }
        if (result.retrieve().equals(ID)) {
            return true;
        }
        return false;

    }

 

}