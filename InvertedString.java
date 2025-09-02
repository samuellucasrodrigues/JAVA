import java.util.Scanner;

public class InvertedString {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      while(scanner.hasNextLine()) {
         String linha = scanner.nextLine();

         if (linha.equals("FIM")) {
            break; 
         }
         
         String invertida = "";

         for(int i = linha.length() - 1; i >= 0; i--) {
            invertida = invertida + linha.charAt(i);
         }

         System.out.println(invertida);
      }

      scanner.close();
   }
}