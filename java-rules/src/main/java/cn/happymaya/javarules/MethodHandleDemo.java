package cn.happymaya.javarules;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleDemo {

    static class Bike {
        String sound() {
            return "ding ding";
        }
    }

    static class Animal {
        String sound() {
            return "wow wow";
        }
    }

    static class Man extends Animal {
        @Override
        String sound() {
            return "hou hou";
        }
    }

    String sound(Object o) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class);
        MethodHandle methodHandle = lookup.findVirtual(o.getClass(), "sound", methodType);

        return (String) methodHandle.invoke(o);
    }

    public static void main(String[] args) throws Throwable {
        String str = new MethodHandleDemo().sound(new Bike());
        System.out.println(str);

        str = new MethodHandleDemo().sound(new Animal());
        System.out.println(str);

        str = new MethodHandleDemo().sound(new Man());
        System.out.println(str);
    }

}
