package com.mparaz.pinoyjugakka;

import java.io.Serializable;

/**
 * Message coming in
 */
public class MyMessage implements Serializable {
    private final int n;

    public MyMessage(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }
}
