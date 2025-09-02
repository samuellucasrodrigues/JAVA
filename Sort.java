import java.util.Scanner;

public class Sort {
    
    public static int modulo(int num, int m) {
        int result = num % m;
        return result;
    }
    
    public static void swap(int array[], int n1, int n2) {
        int t = array[n1];
        array[n1] = array[n2];
        array[n2] = t;
    }

    public static void ordenar(int array[], int m) {
        int tam = array.length;
        for(int i = 0; i < tam - 1; i++) {
            int menor = i;
            for(int j = i + 1; j < tam; j++) {
                int mj = modulo(array[j], m);
                int mm = modulo(array[menor], m);
                
                // Primeiro critério: módulo M
                if(mj < mm) {
                    menor = j;
                } else if(mj == mm) {
                    // Segundo critério: empate no módulo
                    if(array[j] % 2 == 0 && array[menor] % 2 != 0) {
                        // J é par, menor é ímpar - ímpar deve vir primeiro
                        // Não troca, mantém o ímpar na posição
                    } else if(array[j] % 2 != 0 && array[menor] % 2 == 0) {
                        // J é ímpar, menor é par - ímpar deve vir primeiro
                        menor = j;
                    } else if(array[j] % 2 != 0 && array[menor] % 2 != 0) {
                        // Ambos ímpares - maior ímpar primeiro
                        if(array[j] > array[menor]) {
                            menor = j;
                        }
                    } else if(array[j] % 2 == 0 && array[menor] % 2 == 0) {
                        // Ambos pares - menor par primeiro
                        if(array[j] < array[menor]) {
                            menor = j;
                        }
                    }
                }
            }
            if(menor != i) {
                swap(array, menor, i);
            }
        }
    }
    
    public static void main(String[] args) { 
        Scanner ler = new Scanner(System.in);

        int n = ler.nextInt();
        int m = ler.nextInt();
        
        while(n != 0 || m != 0) {
            if(n == 0 && m == 0) break;
            
            int[] array = new int[n];  
            for(int i = 0; i < n; i++) {
                array[i] = ler.nextInt();
            }
            
            ordenar(array, m);
            
            System.out.println(n + " " + m);
            for(int num : array) {
                System.out.println(num);
            }
            
            n = ler.nextInt();
            m = ler.nextInt();
        }
        
        System.out.println("0 0");
        ler.close();
    }
}