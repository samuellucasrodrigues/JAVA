
public class AgendaArvore {

    No raiz;

    public void start() {
        raiz = null;
    }

    public boolean pesquisarLetra(String nome) {
        char letra = Character.toUpperCase(nome.charAt(0));
        return pesquisarLetra(raiz, letra);
    }

    public boolean pesquisarLetra(No i, char letra) {
        boolean resp = false;

        if (i != null) {

            resp = pesquisarLetra(i,letra) || pesquisarLetra(i.esq, letra) || pesquisarLetra(i.dir, letra);
        } 

        return resp;
    }

    public void inserirNo(String nome) {
        char letra = Character.toUpperCase(nome.charAt(0));
        raiz = inserirNo(raiz, nome, letra);
    }

    public No inserirNo(No i, String nome, char letra) {
        if (i == null) {
            No novo = new No(nome);
            novo.inserirNoInterno(nome);
            return novo;
        }

        if (letra == i.letra) {
            i.inserirNoInterno(nome);
        } else if (letra < i.letra) {
            i.esq = inserirNo(i.esq, nome, letra);
        } else {
            i.dir = inserirNo(i.dir, nome, letra);
        }

        return i;
    }

    public void mostrarAgenda() {
        mostrarAgenda(raiz);
    }

    private void mostrarAgenda(No i) {
        if (i != null) {
            mostrarAgenda(i.esq);
            System.out.println("Letra: " + i.letra);
            i.mostrarNomes();
            mostrarAgenda(i.dir);
        }
    }

    public boolean hasStringTam10() {
        return hasStringTam10(raiz);
    }

    public boolean hasStringTam10(String nome) {
        boolean resp = pesquisarLetra(nome);
        if (resp) {
            return hasStringTam10(raiz);
        } else {
            return resp;
        }
    }


    public boolean hasStringTam10(No i) {
        boolean resp = false;
        if (i != null && i.iRaiz != null) {
            resp = i.tam10() || hasStringTam10(i.esq) || hasStringTam10(i.dir);
        }
        return resp;
    }
    public static void main(String[] args) {

    }
}

class No {

    char letra;
    No esq, dir;
    NoInterno iRaiz;

    public No() {
        this(" ");
    }

    public No(String nome) {
        this.letra = Character.toUpperCase(iRaiz.nome.charAt(0));
        this.esq = null;
        this.dir = null;
        this.iRaiz = null;
    }

    public void start() {
        iRaiz = null;
    }

    public void inserirNoInterno(String nome) {
        String sigla = nome.substring(0, Math.min(2, nome.length())).toUpperCase();
        iRaiz = inserirNoInterno(iRaiz, nome, sigla);
    }

    public NoInterno inserirNoInterno(NoInterno i, String nome, String sigla) {
        if (i == null) {
            return new NoInterno(nome);
        }

        int comparacao = sigla.compareTo(i.sigla);

        if (comparacao < 0) {
            i.esq = inserirNoInterno(i.esq, nome, sigla);
        } else if (comparacao > 0) {
            i.dir = inserirNoInterno(i.dir, nome, sigla);
        } else {
            int newTam = sigla.length() + 1;
            if (newTam <= nome.length()) {
                String newSigla = nome.substring(0, newTam).toUpperCase();
                i.dir = inserirNoInterno(i.dir, nome, newSigla);
            } else {
                i.dir = new NoInterno(nome);
            }
        }
        return i;
    }

    public void mostrarNomes() {
        mostrarNomes(iRaiz);
    }

    private void mostrarNomes(NoInterno i) {
        if (i != null) {
            mostrarNomes(i.esq);
            System.out.println("  - " + i.nome);
            mostrarNomes(i.dir);
        }
    }

    public boolean tam10() {
        return tam10(iRaiz);
    }

    public boolean tam10(NoInterno i) {
        boolean resp = false;
        if (i != null) {
            resp = (i.nome.length() > 10) || (i.esq.nome.length() > 10) || (i.dir.nome.length() > 10);
        }
        return resp;
    }

    public boolean tam10(String sigla) {
        return tam10(iRaiz,sigla);
    }

    public boolean tam10(NoInterno i,String sigla) {
        boolean resp = false;
        if (i != null) {
            resp = (i.nome.length() > 10) || (i.esq.nome.length() > 10) || (i.dir.nome.length() > 10);
        }
        return resp;
    }
}

class NoInterno {

    String sigla;
    String nome;
    NoInterno esq, dir;

    public NoInterno() {
        this(" ");
    }

    public NoInterno(String nome) {
        this.nome = nome;
        this.sigla = nome.substring(0, Math.min(2, nome.length())).toUpperCase();
        this.esq = this.dir = null;
    }
}
