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

            String x = line.substring(0, line.indexOf(','));
            int id = Integer.parseInt(x.trim()); // term for the whitespace
            String text = line.substring(line.indexOf(',') + 1).trim();

            s.close();
         }
      } catch (Exception e) {
         System.out.println("Error opening file");
         System.exit(1);
      }

   }
}
// reding the file
