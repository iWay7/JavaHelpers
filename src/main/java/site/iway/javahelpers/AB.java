package site.iway.javahelpers;

import java.io.Serializable;

public class AB<A extends Serializable, B extends Serializable> implements Serializable {

    public A a;
    public B b;

    public AB() {
        a = null;
        b = null;
    }

    public AB(A a, B b) {
        this.a = a;
        this.b = b;
    }

}
