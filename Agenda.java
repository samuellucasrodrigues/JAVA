
public class Agenda {

    No raiz;

    void start() throws Exception {
        raiz = null;
        inserirNo();
    }

    public void inserirNo() throws Exception {
        char[] alfabeto = {'M', 'G', 'S', 'D', 'J', 'P', 'V', 'B', 'F', 'H', 'L', 'N', 'Q', 'U', 'X', 'A', 'C', 'E', 'I', 'K', 'O', 'R', 'T', 'W', 'Y', 'Z'};
        for (char letra : alfabeto) {
            raiz = inserirNo(raiz, letra);
        }
    }

    public No inserirNo(No i, char letra) throws Exception {
        if (i == null) {
            i = new No(letra);
        } else {
            if (letra < i.letra) {
                i.esq = inserirNo(i.esq, letra);
            } else if (letra > i.letra) {
                i.dir = inserirNo(i.dir, letra);
            } else {
                throw new Exception("Nó já existente!\n");
            }
        }
        return i;
    }

    public boolean pesquisarNome(String nome) {
        char letra = Character.toUpperCase(nome.charAt(0));
        return pesquisarNome(raiz, letra, nome);
    }

    public boolean pesquisarNome(No i, char letra, String nome) {
        boolean resp = false;
        if (i == null) {
            return resp;
        } else if (letra < i.letra) {
            resp = pesquisarNome(i.esq, letra, nome);
        } else if (letra > i.letra) {
            resp = pesquisarNome(i.dir, letra, nome);
        } else {
            resp = pesquisarListaNome(i, nome);
        }
        return resp;
    }

    public boolean pesquisarListaNome(No i, String nome) {
        boolean resp = false;
        for (Celula j = i.primeiro; j != null; j = j.prox) {
            if (nome.equals(j.contato.nome)) {
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisarCPF(int CPF){
        return pesquisarCPF(raiz, CPF);
    }

    public boolean pesquisarCPF(No i,int CPF){
            boolean resp=false;
            if(i!=null){
                resp = pesquisarListaCPF(i, CPF) || pesquisarListaCPF(i.esq, CPF) || pesquisarListaCPF(i.dir, CPF);
            }
            return resp;
    }

    public boolean pesquisarListaCPF(No i, int CPF){
        boolean resp = false;
        if(i != null && i.primeiro != null){
            for(Celula j=i.primeiro; j != null; j=j.prox){
                if(CPF == j.contato.CPF){
                    resp=true;
                }
            }
        }
        return resp;
    }
    
    public void remover(String nome) throws Exception {
        char letra = Character.toUpperCase(nome.charAt(0));
        raiz = remover(raiz, letra, nome);
    }

    public No remover(No i, char letra, String nome) throws Exception {
        if (i == null) {
            throw new Exception("Erro!!\n");
        } else if (letra < i.letra) {
            i.esq = remover(i.esq, letra, nome);
        } else if (letra > i.letra) {
            i.dir = remover(i.dir, letra, nome);
        } else {
            i = removerCelula(i, nome);
        }
        return i;
    }

    public No removerCelula(No i, String nome) throws Exception {
        if (i.primeiro == null) {
            throw new Exception("Lista vazia!!\n");
        }

        if (i.primeiro == i.ultimo) {
            if (nome.equals(i.primeiro.contato.nome)) {
                i.primeiro = i.ultimo = null;
                return i;
            }
            throw new Exception("Elemento não encontrado!!\n");
        }

        if (nome.equals(i.primeiro.contato.nome)) {

            i = removerCelulaInicio(i, nome);

        } else if (nome.equals(i.ultimo.contato.nome)) {

            i = removerCelulaFim(i, nome);

        } else {

            for (Celula j = i.primeiro; j.prox != null; j = j.prox) {
                if (nome.equals(j.prox.contato.nome)) {
                    Celula tmp = j.prox;
                    j.prox = tmp.prox;
                    tmp.prox = tmp = null;

                    if (j.prox == null) {
                        i.ultimo = j;
                    }

                }
            }

        }

        return i;
    }

    public No removerCelulaFim(No i, String nome) {
        Celula k = i.primeiro;
        for (; k.prox != i.ultimo; k = k.prox);
        i.ultimo = k;
        k.prox = i.ultimo.prox = null;

        return i;
    }

    public No removerCelulaInicio(No i, String nome) {
        Celula tmp = i.primeiro;
        i.primeiro = i.primeiro.prox;

        if (i.primeiro == null) {
            i.ultimo = null;
        }

        tmp.prox = tmp = null;
        return i;
    }

    public static void main(String[] args) {

    }
}

class Contato {

    String nome;
    String telefone;
    String email;
    int CPF;

    public Contato() {
        this("", "", "", 0);
    }

    public Contato(String nome, String telefone, String email, int CPF) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.CPF = CPF;
    }
}

class Celula {

    Contato contato;
    Celula prox;

    public Celula() {
        this(null);
    }

    public Celula(Contato contato) {
        this.contato = contato;
        this.prox = null;
    }

}

class No {

    char letra;
    No esq, dir;
    Celula primeiro, ultimo;

    public No() {
        this(' ');
    }

    public No(char letra) {
        this.letra = Character.toUpperCase(letra);
        this.esq = null;
        this.dir = null;
        this.primeiro = null;
        this.ultimo = null;
    }

    public void inserirCelula(Contato c) {
        if (primeiro == null) {
            primeiro = ultimo = new Celula(c);
        } else {
            ultimo.prox = new Celula(c);
            ultimo = ultimo.prox;
        }
    }

}
