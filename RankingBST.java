import java.util.*;

public class RankingBST {

    static String query;
    static InvertedIndexBST invert;
    static index ind;
    static LinkedList<Integer> allDocuments;
    static BST_int<Integer> IDsRanked;

    public RankingBST(String str, InvertedIndexBST inv, index in, LinkedList<Integer> docs, BST_int<Integer> IDs) {
        query = str;
        invert = inv;
        ind = in;
        allDocuments = docs;
        IDsRanked = IDs;

    }

    public static void AddInlist(LinkedList<Integer> x) {
        if (x.empty())
            return;
        x.FindFirst();
        while (!x.empty()) {
            boolean found = existsInResult(allDocuments, x.retrieve());
            if (!found) {// when it is not found in result
                allDocuments.insert(x.retrieve());

            } // end if
            if (!x.last())
                x.FindNext();
            else
                break;
        } // end while loop
    }

    public static void AddInlistSorted(LinkedList<Integer> x) {
        if (x.empty())
            return;
        x.FindFirst();
        while (!x.empty()) {
            boolean found = existsInResult(allDocuments, x.retrieve());
            if (!found) {// when it is not found in result
                insertSortedList(x.retrieve());

            } // end if
            if (!x.last())
                x.FindNext();
            else
                break;
        }

    }

    public static void insertSortedList(Integer id) {
        if (allDocuments.empty()) {
            allDocuments.insert(id);
            return;
        }
        allDocuments.FindFirst();
        while (!allDocuments.last()) {
            if (id > allDocuments.retrieve()) {
                Integer ID = allDocuments.retrieve();
                allDocuments.update(ID);
                allDocuments.insert(ID);
                return;
            } else
                allDocuments.FindNext();
        }
        if (id > allDocuments.retrieve()) {
            Integer ID = allDocuments.retrieve();
            allDocuments.update(ID);
            allDocuments.insert(ID);
            return;
        } else
            allDocuments.insert(id);

    }

    public static void RankQ() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (query.length() == 0)
            return;
        String A[] = query.split(" ");
        boolean exist = false;
        for (int i = 0; i < A.length; i++) {
            exist = invert.search(A[i].trim().toLowerCase());
            if (exist)
                list = invert.inverted_index.retrieve().document_IDs;
            AddInlist(list);

        }
    }

    public static void insertInBST() {
        RankQ(query);
        if (allDocuments.empty()) {
            System.out.println("Empty query");
            return;
        }
        allDocuments.FindFirst();
        while (!allDocuments.last()) {
            Document D = getDocID(allDocuments.retrieve());
            int rank = getDocRank(D, query);
            IDsRanked.insert(rank, allDocuments.retrieve());
            allDocuments.FindNext();
        }
        Document D = getDocID(allDocuments.retrieve());
        int rank = getDocRank(D, query);
        IDsRanked.insert(rank, allDocuments.retrieve());

    }

    public static void displayAllDoc() {
        IDsRanked.display_decreasing();
    }

    public static Document getDocID(int id) {
        return ind.getID(id);
    }

    public static int frequencyInDoc(Document doc, String term) {
        int f = 0;
        LinkedList<String> words = doc.List_ID;
        if (words.empty())
            return 0;
        words.FindFirst();
        while (!words.last()) {
            if (words.retrieve().equalsIgnoreCase(term))
                f++;
            words.FindNext();
        }
        if (words.retrieve().equalsIgnoreCase(term))
            f++;
        return f;
    }

    public static int getDocRank(Document doc, String q) {
        if (q.length() == 0)
            return 0;

        String term[] = q.split(" ");
        int sum = 0;
        for (int i = 0; i < term.length; i++) {
            sum += frequencyInDoc(doc, term[i].trim().toLowerCase());

        }
        return sum;
    }

    public static void RankQ(String q) {
        LinkedList<Integer> X = new LinkedList<Integer>();
        if (q.length() == 0)
            return;

        String term[] = q.split(" ");
        boolean exist = false;
        for (int i = 0; i < term.length; i++)
            exist = invert.search(term[i].trim().toLowerCase());
        if (exist)
            X = invert.inverted_index.retrieve().document_IDs;
        AddInlistSorted(X);
    }

    public static boolean existsInResult(LinkedList<Integer> result, Integer id) {
        if (result.empty())
            return false;
        result.FindFirst();
        while (!result.last()) {
            if (result.retrieve().equals(id)) {
                return true;
            }
            result.FindNext();
        }
        if (result.retrieve().equals(id))
            return true;
        return false;

    }
}