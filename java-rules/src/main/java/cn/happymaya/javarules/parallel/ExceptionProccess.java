package cn.happymaya.javarules.parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionProccess {

    public static void main(String[] args) {
        ThreadFactory factory = r->{
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setUncaughtExceptionHandler( (t,e) -> {
                System.out.println(t + "" + e);
                e.printStackTrace();//example
            });
            return thread ;
        };
        ExecutorService executor = Executors.newCachedThreadPool(factory);

//        Future future = executor.submit( ()-> {
//            String s = null; s.substring(0);
//        });
//
//        try {
//            Thread.sleep(1000);
//            future.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        executor.execute(()->{
            String s = null; s.substring(0);
        });
        executor.shutdown();
    }

}
