
import java.util.*;

public class ArrayLength{

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int n = ler.nextInt();
        ler.nextLine();

        String[] s = new String[n];

        for (int i = 0; i < n; i++) {
            s[i] = ler.nextLine();
        }

        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (s[j].length() < s[index].length()) {
                    index = j;
                }
            }
            String t = s[i];
            s[i] = s[index];
            s[index] = t;
        }

        System.out.println("\nStrings ordenadas por tamanho:");
        for (int i = 0; i < n; i++) {
            System.out.println(s[i] + " (tamanho: " + s[i].length() + ")");
        }
        ler.close();
    }
}
