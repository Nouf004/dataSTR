import java.util.*;
import java.io.*;

public class read {

    public void Load(String fileName) {
        String line = null;

        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);

            s.nextLine();

            while (s.hasNextLine()) {
                line = s.nextLine();

                if (line.trim().length() < 3) {
                    System.out.println("Empty line");
                    break;
                }
                System.out.println(line);

                String str = s.next();

                s.close();
            }
        } catch (EOFException e) {
            System.out.println("End of file");
        } catch (Exception e) {
            System.out.println("Error opening file");
            System.exit(1);
        }

    }
}