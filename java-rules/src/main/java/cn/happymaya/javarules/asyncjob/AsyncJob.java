package cn.happymaya.javarules.asyncjob;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@Async
public class AsyncJob {
    public String testJob() {
        try {
            Thread.sleep(1000 * 3);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        }
        return "aaa";
    }

    public Future<String> testJob2() {
        String result = this.testJob();
        return new AsyncResult<>(result);
    }
}
