package cn.happymaya.javarules.parallel;

import cn.happymaya.javarules.asyncjob.AsyncJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class TestController {
    @Autowired
    AsyncJob asyncJob;

    @GetMapping("/test")
    @ResponseBody
    public String testJob() throws Exception{
        System.out.println(asyncJob.testJob());
        System.out.println(asyncJob.testJob2().get());
        return "ok";
    }
}
