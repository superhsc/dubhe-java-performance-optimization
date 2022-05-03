package cn.happymaya.javarules;

public class StaticCall {
    public static final int A = 1;

    void test() {
        System.out.println(this.A);
        System.out.println(StaticCall.A);
    }
}
