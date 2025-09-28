
import java.util.*;

class QuicksortPivo {

    private int array[];
    private int n;

    /*Construtor */
    public QuicksortPivo() {
        array = new int[100];
        // array = new int[1000];
        // array = new int[10000]
        n = array.length;
    }

    public QuicksortPivo(int tam) {
        array = new int[tam];
        n = array.length;
    }

    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /*Gera um array ordenado de forma crescente */
    public void ordenadoCrescente() {
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    /*Gera um array ordenado de forma decrescente */
    public void ordenadoDecrescente() {
        for (int i = 0; i < n; i++) {
            array[i] = n - 1 - i;
        }
    }

    /*Gera um array 90% ordenado */
    public void quaseOrdenado() {
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        int q = n / 10;
        Random rand = new Random();
        for (int i = 0; i < q; i++) {
            int pos = rand.nextInt(n - 1);
            swap(pos, pos + 1);
        }
    }

    /*Gera um array aleatório */
    public void aleatorio() {
        Random rand = new Random();
        ordenadoCrescente();
        for (int i = 0; i < n; i++) {
            swap(i, Math.abs(rand.nextInt()) % n);
        }
    }

    /*Ordena usando o primeiro elemento como pivô */
    public void quicksortFirstPivot(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[esq];
        while (i <= j) {
            while (array[i] < pivo) {
                i++;
            }
            while (array[j] > pivo) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j) {
            quicksortFirstPivot(esq, j);
        }
        if (i < dir) {
            quicksortFirstPivot(i, dir);
        }
    }

    /*Ordena usando o ultimo elemento como pivo */
    public void quicksortLastPivot(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[dir];
        while (i <= j) {
            while (array[i] < pivo)i++;
            while (array[j] > pivo) j--;

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j) {
            quicksortLastPivot(esq, j);
        }
        if (i < dir) {
            quicksortLastPivot(i, dir);
        }
    }

    /*Ordena usando um pivo aleatorio */
    public void quicksortRandomPivot(int esq, int dir) {
        int i = esq, j = dir;
        Random rand = new Random();
        int iRand = esq + rand.nextInt(dir - esq + 1);
        int pivo = array[iRand];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j) {
            quicksortRandomPivot(esq, j);
        }
        if (i < dir) {
            quicksortRandomPivot(i, dir);
        }
    }

    /*Ordena usando um pivo da mediana de tres posições do array, inicio,meio e fim */
    public void quicksortMedianOfThree(int esq, int dir) {
        int i = esq, j = dir, meio = (esq + dir) / 2;
        int pivo = array[median(esq, dir, meio)];
        while (i <= j) {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j) {
            quicksortMedianOfThree(esq, j);
        }
        if (i < dir) {
            quicksortMedianOfThree(i, dir);
        }
    }

    /*Pega a mediana do array */
    public int median(int i, int j, int k) {
        int a = array[i], b = array[j], c = array[k];
        if ((a <= b && b <= c) || (c <= b && b <= a))  return j; 
        else if ((b <= a && a <= c) || (c <= a && a <= b)) return i;
        else return k;
    }

    /* Método para verificar se o array está ordenado */
    public boolean ehOrdenado() {
        for (int i = 0; i < n - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void mostrarArrayAleatorio() {
        int[] tamanhos = {100, 1000, 10000};
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("************************************Array Aleatorio******************************************");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.aleatorio();
            long inicio = System.nanoTime();
            qs.quicksortFirstPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo no inicio: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.aleatorio();
            long inicio = System.nanoTime();
            qs.quicksortLastPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo no final: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.aleatorio();
            long inicio = System.nanoTime();
            qs.quicksortRandomPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo aleatorio: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.aleatorio();
            long inicio = System.nanoTime();
            qs.quicksortMedianOfThree(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo com mediana de três elementos(inicio, meio e fim): " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }
    }

    public void mostrarArrayOrdenado() {
        int[] tamanhos = {100, 1000, 10000};

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("************************************Array Ordenado*******************************************");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.ordenadoCrescente();
            long inicio = System.nanoTime();
            qs.quicksortFirstPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo no inicio: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.ordenadoCrescente();
            long inicio = System.nanoTime();
            qs.quicksortLastPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo no final: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.ordenadoCrescente();
            long inicio = System.nanoTime();
            qs.quicksortRandomPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo aleatorio: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.ordenadoCrescente();
            long inicio = System.nanoTime();
            qs.quicksortMedianOfThree(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo com mediana de três elementos(inicio, meio e fim): " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }
    }

    public void mostrarArrayQuaseOrdenado() {
        int[] tamanhos = {100, 1000, 10000};

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("**********************************Array Quase Ordenado***************************************");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.quaseOrdenado();;
            long inicio = System.nanoTime();
            qs.quicksortFirstPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo no inicio: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.quaseOrdenado();;
            long inicio = System.nanoTime();
            qs.quicksortLastPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo no final: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.quaseOrdenado();;
            long inicio = System.nanoTime();
            qs.quicksortRandomPivot(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo aleatorio: " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        System.out.println();
        for (int tam : tamanhos) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Testando com " + tam + " elementos:");

            QuicksortPivo qs = new QuicksortPivo(tam);

            qs.quaseOrdenado();;
            long inicio = System.nanoTime();
            qs.quicksortMedianOfThree(0, tam - 1);
            long fim = System.nanoTime();
            System.out.println("Quicksort com pivo com mediana de três elementos(inicio, meio e fim): " + (fim - inicio) + " ns");
            System.out.println("Array ordenado: " + qs.ehOrdenado());
            System.out.println("---------------------------------------------------------------------------------------------");

        }
    }

    public static void main(String[] args) {
        QuicksortPivo qs = new QuicksortPivo();

        System.out.println("=============================== TESTE DO ALGORITMO QUICKSORT ================================");

        // Executar testes completos
        qs.mostrarArrayAleatorio();
        System.out.println();
        qs.mostrarArrayOrdenado();
        System.out.println();
        qs.mostrarArrayQuaseOrdenado();
    }
}
