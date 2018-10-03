package site.iway.javahelpers;

import java.io.Serializable;

public class A<T extends Serializable> {

    public T a;

    public A() {
        a = null;
    }

    public A(T a) {
        this.a = a;
    }

}
