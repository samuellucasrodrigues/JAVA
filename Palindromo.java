import java.util.Scanner;

public class Palindromo {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        
        while (ler.hasNextLine()) {
            String linha = ler.nextLine();
            
            if (linha.equals("FIM")) {
                break;
            }
            
            boolean resultado = ehPalindromo(linha);
            
            if (resultado) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }
        
        ler.close();
    }
    

    public static boolean ehPalindromo(String texto) {
        int inicio = 0;
        int fim = texto.length() - 1;

        while (inicio < fim) {
            if (texto.charAt(inicio) != texto.charAt(fim)) {
                return false; 
            }
            inicio++;
            fim--;
        }
        
        return true;
    }
}