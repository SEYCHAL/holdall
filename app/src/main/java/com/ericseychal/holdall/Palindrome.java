package com.ericseychal.holdall;

/**
 * Created by ericseychal on 16/11/2016.
 */

public class Palindrome {
    private String word;

    public Palindrome(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean palindrome (String word) {
        StringBuffer variable = new StringBuffer(word);
        boolean variableReturn = true;
        for (int indice = 0; indice < variable.length(); indice++) {
            if (variable.charAt(indice) == variable.charAt(variable.length() - indice - 1)) {
                if (indice >= variable.length() / 2) {
                    break;
                }
            } else {
                variableReturn = false;
                break;
            }
        }
        return variableReturn;
    }
}
