package com.mparaz.pinoyjugakka;

import java.io.Serializable;

/**
 * Message for result
 */
public class MyMessageResult implements Serializable {
    private final int n;

    public MyMessageResult(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }
}
