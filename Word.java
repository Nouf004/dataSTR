import java.util.*;
import java.io.*;

public class Word {

    LinkedList<Integer> document_IDs;
    String text;

    Word(String Text) {
        document_IDs = new LinkedList<Integer>();
        text = Text;

    }

    void addID(int ID) {
        if (!exists(ID))
            document_IDs.insert(ID);

    }

    boolean exists(int id) {

        if (document_IDs.empty())
            return false;
        document_IDs.FindFirst();
        while (!document_IDs.last()) {
            if (document_IDs.retrieve() == id)
                return true;
            document_IDs.FindNext();
        }
        if (document_IDs.retrieve() == id)
            return true;

        return false;

    }

}
// insert the id of document for the word that has appered in it
