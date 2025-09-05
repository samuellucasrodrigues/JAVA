import java.util.*;

public class AlgebraBooleana {

    public static int or(int x, int y){
        return (x != 0 || y != 0) ? 1 : 0;
    }

    public static int and(int x, int y){
        return (x != 0 && y != 0) ? 1 : 0;
    }

    public static int not(int x){
        return (x ^ 1);
    }

    public static char intToChar(int x){
        return (x == 1) ? '1' : '0';
    }

    static int[] valores(String linha, int nVar){
        int[] var = new int[nVar];
        int p = 2;
        for(int i = 0; i < nVar; i++){
            while(linha.charAt(p) == ' ') p++;
            var[i] = linha.charAt(p) - '0';
            p++;
        }
        return var;
    }

    static String replace(String linha, int[] var, int posInicio){
        String resultado = "";
        for(int i = posInicio; i < linha.length(); i++){
            char c = linha.charAt(i);
            if(c != ' '){
                if(c >= 'A' && c <= 'Z'){
                    resultado += var[c - 'A'];
                } else {
                    resultado += c;
                }
            }
        }
        return resultado;
    }

    static int[] getSubExpr(String expressao){
        int[] posicao = new int[2];
        for(int i = 0; i < expressao.length(); i++){
            if(expressao.charAt(i) == ')'){
                posicao[1] = i;
                int j = i;
                while(expressao.charAt(j) != '(') j--;
                posicao[0] = j;
                break;
            }
        }
        return posicao;
    }

    static int calcular(String expressao, int ps, int pe){
        char operador = expressao.charAt(ps - 1);
        int result = (operador == 'd') ? 1 : 0;

        for(int i = ps + 1; i < pe; i++){
            char c = expressao.charAt(i);
            if(c == '0' || c == '1'){
                int valor = c - '0';
                if(operador == 'r') result = or(result, valor);
                else if(operador == 'd') result = and(result, valor);
            }
        }

        if(operador == 't'){
            result = not(expressao.charAt(ps + 1) - '0');
        }

        return result;
    }

    static String newExpr(String expressao, int ps, int pe, int result){
        int inicio = (expressao.charAt(ps - 1) == 'r') ? ps - 2 : ps - 3;
        String nova = "";

        for(int i = 0; i < inicio; i++){
            nova += expressao.charAt(i);
        }

        nova += intToChar(result);

        for(int i = pe + 1; i < expressao.length(); i++){
            nova += expressao.charAt(i);
        }

        return nova;
    }

    public static void main(String args[]){
        Scanner ler = new Scanner(System.in);
        ArrayList<String> entrada = new ArrayList<>();
        while(ler.hasNextLine()){
            entrada.add(ler.nextLine());
        }

        for(String linha : entrada){
            if(linha.charAt(0) == '0') break;

            int nVar = linha.charAt(0) - '0';
            int[] val = valores(linha, nVar);

            int exprTrim = 2;
            for(int i = 0; i < nVar; i++){
                while(linha.charAt(exprTrim) == ' ') exprTrim++;
                exprTrim++;
            }

            String expressao = replace(linha, val, exprTrim);

            while(expressao.length() > 1){
                int[] posicao = getSubExpr(expressao);
                int ps = posicao[0];
                int pe = posicao[1];

                if(pe == 0 && ps == 0) break;

                int result = calcular(expressao, ps, pe);
                expressao = newExpr(expressao, ps, pe, result);
            }

            if(expressao.length() > 0 && expressao.charAt(0) == '1'){
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
