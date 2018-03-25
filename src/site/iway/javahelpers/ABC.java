package site.iway.javahelpers;

import java.io.Serializable;

public class ABC<A extends Serializable, B extends Serializable, C extends Serializable> implements Serializable {

    public A a;
    public B b;
    public C c;

    public ABC() {
        a = null;
        b = null;
        c = null;
    }

    public ABC(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

}
