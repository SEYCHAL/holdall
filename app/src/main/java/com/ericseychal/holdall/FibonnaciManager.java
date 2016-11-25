package com.ericseychal.holdall;

/**
 * Created by ericseychal on 16/11/2016.
 */

public class FibonnaciManager {

    String fibonnaciSuite(int nber) {
        StringBuffer suite = new StringBuffer(" 1 2");
        if (nber > 3) {
            int nber1=1;
            int nber2=2;
            int sum;
            for (int indice = 3; indice < nber; indice++) {
                sum = nber1 + nber2;
                suite.append(" " + String.valueOf(sum));
                nber1 = nber2;
                nber2 = sum;
            }
        }
        return suite.toString();
    }

}
