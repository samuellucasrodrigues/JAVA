
import java.util.*;

public class InvertedStringR {

    public static boolean equals(String s1, String s2) {
        int i = 0;
        while (i < s1.length() && i < s2.length()) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
            i++;
        }
        return (i == s1.length() && i == s2.length());
    }

    public static String invert(String s, int i){
        if(i==0)return "";
        else{
            return s.charAt(i-1) + invert(s, i - 1);
        }
    }
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        while (ler.hasNextLine()) {
            String s = ler.nextLine();
            if (equals(s, "FIM")) {
                break;
            }
            int i = s.length();
            String inverted = invert(s,i);
            System.out.println(inverted);
        }

        ler.close();
    }
}
