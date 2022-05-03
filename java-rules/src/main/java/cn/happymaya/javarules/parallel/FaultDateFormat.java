package cn.happymaya.javarules.parallel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FaultDateFormat {

    final static String TIME_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_YYYY_MM_DD_HH_MM_SS);

    public static void main(String[] args) {
        final FaultDateFormat faultDateFormat = new FaultDateFormat();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println(faultDateFormat.format.parse("2020-07-25 08:56:40"));
                } catch (ParseException e) {
                    throw new IllegalStateException();
                }
            });
        }
        executorService.shutdown();
    }
}
