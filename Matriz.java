public class Matriz {
    Celula inicio;
    int linha, coluna, l, c;

    public Matriz(){
        this(3,3);
    }

    public Matriz(int linha, int coluna){
        this.inicio=null;
        this.linha=linha;
        this.coluna=coluna;
        this.l = 0;
        this.c = 0;
    }

    public void inserirCelula(int x){
        if(l >= linha) return;

        if(inicio==null){
            inicio = new Celula(x);
            c++;
            return;
        }

        Celula atual = inicio;

        for(int i=0; i<l; i++){
            if(atual.inf==null){
                Celula nova = new Celula();
                atual.inf = nova;
                nova.sup = atual;
            }
            atual=atual.inf;
        }

        for(int i=0; i < c; i++){
            if(atual.dir==null){
                Celula nova = new Celula();
                atual.dir = nova;
                nova.esq=atual;
                if(atual.sup!= null && atual.sup.dir != null){
                    nova.sup=atual.sup.dir;
                    atual.sup.dir.inf=nova;
                }
            }
            atual = atual.dir;
        }

        atual.elemento=x;

        c++;

        if(c >= coluna){
            c=0;
            l++;
        }
    }

    public void mostrar() {
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                System.out.print(j.elemento + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Criar matriz 2x3
        Matriz m = new Matriz(3, 3);

        for(int i=1; i <=9; i++){
            m.inserirCelula(i);
        }
        
        // Mostrar matriz
        m.mostrar();
    }
}

class Celula{
    int elemento;
    Celula esq, sup, dir, inf;

    public Celula(){
        this(0);
    }

    public Celula(int x){
        this.elemento=x;
        this.esq=null;
        this.sup=null;
        this.dir=null;
        this.inf=null;
    }
}