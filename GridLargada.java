import java.util.*;

public class GridLargada {

    public static int arrayCmp(int n, int[] largada, int[] chegada) {
        int cont = 0;

        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pos[largada[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pos[chegada[i]] > pos[chegada[j]]) {
                    cont++;
                }
            }
        }

        return cont;
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        while (ler.hasNextInt()) {
            int n = ler.nextInt();
            int[] largada = new int[n];
            int[] chegada = new int[n];

            for (int i = 0; i < n; i++) {
                largada[i] = ler.nextInt();
            }

            for (int i = 0; i < n; i++) {
                chegada[i] = ler.nextInt();
            }

            int r = arrayCmp(n, largada, chegada);
            System.out.println(r);
        }

        ler.close();
    }
}
