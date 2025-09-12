
import java.util.*;

public class SequenciaEspelho {

    static int invert(int n) {
        int inv = 0;
        while (n > 0) {
            inv = inv * 10 + n % 10;
            n /= 10;
        }
        return inv;
    }

    static void mirror(int[] vet, int s, int e, int tam) {
        int cont1 = s, cont2 = e;
        for (int i = 0; i < tam; i++) {
            if (i < tam / 2) {
                vet[i] = cont1;
                cont1++;
            } else {
                int original = cont2;
                int inverted = invert(original);
                if (original % 10 == 0) {
                    vet[i] = -inverted;
                } else {
                    vet[i] = inverted;
                }
                cont2--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        while (ler.hasNext()) {
            int s = ler.nextInt();
            int e = ler.nextInt();

            int tam = 2 * (e - s + 1);

            int[] vet = new int[tam];
            mirror(vet, s, e, tam);

            for (int i = 0; i < tam; i++) {
                if (vet[i] < 0) {
                    System.out.print("0" + (-vet[i]));
                } else {
                    System.out.print(vet[i]);
                }
            }
            System.out.println(" ");
        }
        ler.close();
    }
}
