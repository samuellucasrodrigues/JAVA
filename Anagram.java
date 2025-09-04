
import java.util.Scanner;

public class Anagram {

    public static boolean equals(String str1, String str2) {
        int i = 0;
        while (i < str1.length() && i < str2.length()) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
            i++;
        }
        return (i == str1.length() && i == str2.length());
    }

    public static String lower(String str) {
        char[] arr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                arr[i] = (char) (c + 32);
            } else {
                arr[i] = c;
            }
        }
        return new String(arr);
    }

    // separa duas palavras da linha no formato "palavra1 - palavra2"
    public static String[] split(String linha) {
        char[] c1 = new char[50];
        char[] c2 = new char[50];
        int i = 0, j = 0, k = 0;

        while (i < linha.length() && linha.charAt(i) != '-') {
            if (linha.charAt(i) != ' ') {
                c1[j++] = linha.charAt(i);
            }
            i++;
        }

        while (i < linha.length() && (linha.charAt(i) == '-' || linha.charAt(i) == ' ')) {
            i++;
        }

        while (i < linha.length()) {
            if (linha.charAt(i) != ' ') {
                c2[k++] = linha.charAt(i);
            }
            i++;
        }

        return new String[]{new String(c1, 0, j), new String(c2, 0, k)};
    }

    public static boolean isAnagram(String s, String t) {
        int[] v = new int[26];

        for (int i = 0; i < 26; i++) {
            v[i] = 0;
        }

        s = lower(s);
        t = lower(t);

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            v[s.charAt(i) - 'a']++;
            v[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (v[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in, "ISO-8859-1");

        while (ler.hasNextLine()) {
            String linha = ler.nextLine();

            if (equals(linha, "FIM")) {
                break;
            }

            String[] palavras = split(linha);
            String s1 = palavras[0];
            String s2 = palavras[1];

            if (isAnagram(s1, s2)) {
                System.out.println("SIM");
            } else {
                System.out.println("N" + "\u00c3" + "O");
            }
        }

        ler.close();
    }
}
