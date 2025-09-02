import java.util.Random;
import java.util.Scanner;

public class SubstituicaoAleatoria {
    
    public static String substituirLetras(String texto) {
        Random gerador = new Random();
        gerador.setSeed(4);
        
        // Sorteia duas letras minúsculas aleatórias
        char c1 = (char) ('a' + Math.abs(gerador.nextInt()) % 26);
        char c2 = (char) ('a' + Math.abs(gerador.nextInt()) % 26);
        
        String resultado = "";
        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            if (caractere == c1) {
                resultado += c2;
            } else {
                resultado += caractere;
            }
        }
        
        return resultado;
    }
    
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        
        while (ler.hasNextLine()) {
            String linha = ler.nextLine();
            
            if (linha.equals("FIM")) {
                break;
            }
            
            String resultado = substituirLetras(linha);
            System.out.println(resultado);
        }
        
        ler.close();
    }
}