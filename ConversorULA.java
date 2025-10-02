import java.io.*;

public class ConversorULA {

    //Tabela de mnemonicos
    public static String converterMnemonico(String mnemonico) {
        switch(mnemonico) {
            case "umL": return "0";
            case "zeroL": return "1"; 
            case "AonB": return "2";
            case "nAonB": return "3";
            case "AeBn": return "4";
            case "nB": return "5";
            case "nA": return "6";
            case "nAxnB": return "7";
            case "AxB": return "8";
            case "copiaA": return "9";
            case "copiaB": return "A";
            case "AeB": return "B";
            case "AenB": return "C";
            case "nAeB": return "D";
            case "AoB": return "E";
            case "nAeBn": return "F";
            default: return "0"; 
        }
    }

    public static void main(String[] args) throws IOException {
        // Arquivos de entrada e saída
        String arquivoEntrada = "testeula.ula";
        String arquivoSaida = "testeula.hex";

        // Variáveis X e Y
        String X = "";
        String Y = "";

        // Metodos ler e escrever, que leem e escrevem nos arquivos
        BufferedReader ler = new BufferedReader(new FileReader(arquivoEntrada));
        BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivoSaida));

        // Le uma linha do arquivo testeula.ula e se ela não for NULL entra no laço
        String linha;
        while ((linha = ler.readLine()) != null) {
            // tira os espaços da linha
            linha = linha.trim();

            // Se a linha for vazia, ou está no início ou está no fim, pula
            if (linha.isEmpty() || linha.equals("inicio:") || linha.equals("fim.")) {
                continue;
            }

            // Tira o ponto e virgula
            linha = linha.replace(";", "");
            // Quando encontrar o sinal "=" na linha, se quebra em duas
            String[] partes = linha.split("=");

            // Remove qualquer espaço nas linhas quebradas e guarda em "var" a variável(X,Y ou W) e em "valor" o valor atribuído
            String var = partes[0].trim();
            String valor = partes[1].trim();

            if (var.equals("X")) {
                X = valor;
            } else if (var.equals("Y")) {
                Y = valor;
            } else if (var.equals("W")) {
                String op = converterMnemonico(valor);
                String instrucao = X + Y + op;

                escrever.write(instrucao);
                escrever.newLine();
            }
        }

        ler.close();
        escrever.close();

        System.out.println("\nConversão concluída! Arquivo gerado: " + arquivoSaida);
    }
}
