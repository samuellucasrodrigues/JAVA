
import java.util.*;

public class SomaDigito {

    public static int somar(int n) {
        if (n == 0) {
            return 0;
        } else {
            return (n % 10 + somar(n / 10));
        }
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        while (true) {
            if (!ler.hasNextInt()) { // se o próximo valor NÃO for inteiro
                break;               // sai do loop
            }
            int n = ler.nextInt();
            int res = somar(n);
            System.out.println(res);
        }
        ler.close();
    }
}
