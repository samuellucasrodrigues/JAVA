import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CiframentoCesar {
    
    public static String cifrar(String texto) {
        String resultado = "";
        
        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            

            if (caractere == '\uFFFD') {
                resultado += caractere;
            }
            else {
                // Para TODOS os outros caracteres, aplica +3
                char cifrado = (char) (caractere + 3);
                resultado += cifrado;
            }
        }
        return resultado;
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("FIM")) {
                    break;
                }
                
                String cifrada = cifrar(linha);
                System.out.println(cifrada);
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}