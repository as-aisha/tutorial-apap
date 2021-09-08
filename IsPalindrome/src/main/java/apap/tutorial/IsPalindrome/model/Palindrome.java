package apap.tutorial.IsPalindrome.model;

import java.io.Serializable;

public class Palindrome implements Serializable {

    private String kalimat;

    public Palindrome(String kalimat) {
        this.kalimat = kalimat;
    }

    public boolean isPalindrome() {
        int n = kalimat.length();

        for (int i=0; i<(n/2); ++i) {
            if (kalimat.charAt(i) != kalimat.charAt(n - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public String getKalimat() {
        return kalimat;
    }
}