public class Doc_Rank {
   int id;
   int rank;

   public Doc_Rank(int i, int r) {
      id = i;
      rank = r;
   }

   public void display() {
      System.out.printf("%-8d%-8d\n", id, rank);
   }
}