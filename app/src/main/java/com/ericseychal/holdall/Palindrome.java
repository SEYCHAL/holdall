package com.ericseychal.holdall;

/**
 * Created by ericseychal on 16/11/2016.
 */

public class Palindrome {

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

    public String toward (String word) {
        StringBuffer variable2 = new StringBuffer(word);
        StringBuffer variable = new StringBuffer();
        for (int indice = variable2.length(); indice >= 1; indice--) {
            variable.append(variable2.substring(indice - 1, indice));
        }
        return variable.toString();
    }
}
