public class test {

}

class A {
    void out() {
        System.out.println("HELLO A");
    }
}

class B extends A {
    void out() {
        System.out.println("HELLO B");
    }
}

class C extends A {
    void out() {
        System.out.println("HELLO A");
    }
}