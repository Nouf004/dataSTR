import java.util.*;

public class Ranking {
    static String Q; // query
    static InvertedIndexBST inverBST;
    static index Index;
    static LinkedList<Integer> allDocInQ;
    static LinkedList<Doc_Rank> allDocRank;

    public Ranking(InvertedIndexBST BSTinv, index ind, String Q) {
        inverBST = BSTinv;
        Index = ind;
        this.Q = Q;
        allDocInQ = new LinkedList<Integer>();
        allDocRank = new LinkedList<Doc_Rank>();
    }

    // diplaying all doc with score using list
    public static void displayAllDoc() {
        if (allDocRank.empty()) {
            System.out.println("empty");
            return;
        } // end if
        allDocRank.FindFirst();
        while (!allDocRank.last()) {
            allDocRank.retrieve().display();
            allDocRank.FindNext();
        } // end while loop
        allDocRank.retrieve().display();
    }// end method

    public static Document getDocGivenId(int id) {
        return Index.getID(id);// ?
    }

    // trem frequency in doc
    public static int termFreqInDoc(Document doc, String term) {

        int frq = 0;
        LinkedList<String> Word = doc.List_ID;
        if (Word.empty())
            return 0;
        Word.FindFirst();
        while (!Word.last()) {
            if (Word.retrieve().equalsIgnoreCase(term))
                frq++;
            Word.FindNext();
        } // end while loop
        if (Word.retrieve().equalsIgnoreCase(term))
            frq++;
        return frq;
    }// end method

    public static int getDocRankScor(Document doc, String Q) {
        if (Q.length() == 0)
            return 0;
        String terms[] = Q.split(" ");
        int sumFrq = 0;// sum of frequncy
        for (int i = 0; i < terms.length; i++) {
            sumFrq += termFreqInDoc(doc, terms[i].trim().toLowerCase());
        }
        return sumFrq;
    }// end method

    public static void RankQ(String Q) {
        LinkedList<Integer> X = new LinkedList<Integer>();
        if (Q.length() == 0)
            return;
        String terms[] = Q.split(" ");
        boolean find = false;
        for (int i = 0; i < terms.length; i++) {
            find = inverBST.search(terms[i].trim().toLowerCase());
            if (find)
                X = inverBST.inverted_index.retrieve().document_IDs;// ?
            AddingInOneList(X);
        } // end for loop
    }// end of method

    public static void AddingInOneList(LinkedList<Integer> x) {
        if (x.empty())
            return;
        x.FindFirst();
        while (!x.empty()) {
            boolean found = existsInResult(allDocInQ, x.retrieve());
            if (!found) {// when it is not found in result
                allDocInQ.insert(x.retrieve());

            } // end if
            if (!x.last())
                x.FindNext();
            else
                break;
        } // end while loop
    }// end method

    public static boolean existsInResult(LinkedList<Integer> result, Integer id) {
        if (result.empty())
            return false;
        result.FindFirst();
        while (!result.last()) {
            if (result.retrieve().equals(id)) {
                return true;
            }
            result.FindNext();

        } // end while loop
        if (result.retrieve().equals(id)) {
            return true;
        }
        return false;
    }// end method

    public static void insertStoredInList() {
        RankQ(Q);// to fill allDocInQ
        if (allDocInQ.empty()) {
            System.out.println("Empty Query");
            return;
        } // end if

        allDocInQ.FindFirst();

        while (!allDocInQ.last()) {
            Document doc = getDocGivenId(allDocInQ.retrieve());
            int rank = getDocRankScor(doc, Q);
            insertStoredInList(new Doc_Rank(allDocInQ.retrieve(), rank));// **
            allDocInQ.FindNext();
        } // end while
        Document doc = getDocGivenId(allDocInQ.retrieve());
        int rank = getDocRankScor(doc, Q);
        insertStoredInList(new Doc_Rank(allDocInQ.retrieve(), rank));// **

    }// end method

    public static void insertStoredInList(Doc_Rank dr) {// **
        if (allDocRank.empty()) {
            allDocRank.insert(dr);
            return;
        } // if
        allDocRank.FindFirst();
        while (!allDocRank.last()) {
            if (dr.rank > allDocRank.retrieve().rank) {
                Doc_Rank dr1 = allDocRank.retrieve();
                allDocRank.update(dr);
                allDocRank.insert(dr1);
                return;
            } // end if
            else if (dr.rank == allDocRank.retrieve().rank) {
                while (!allDocRank.last() && dr.rank == allDocRank.retrieve().rank && dr.id > allDocRank.retrieve().id)
                    allDocRank.FindNext();
            }
        } // end while

    }// end method
}// end class