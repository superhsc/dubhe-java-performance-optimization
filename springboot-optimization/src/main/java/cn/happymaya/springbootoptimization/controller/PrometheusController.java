package cn.happymaya.springbootoptimization.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/promethues/")
public class PrometheusController {

    @Autowired
    MeterRegistry registry;

    /**
     * 指标类型设置
     */
    private Counter counterCore;
    private Counter counterIndex;
    private AtomicInteger appOnlineCount;

    /**
     * 服务启动时创建自定义指标
     */
    @PostConstruct
    private void init() {
        counterCore = registry.counter("app_requests_method_count", "method", "PrometheusController.core");
        counterIndex = registry.counter("app_requests_method_count", "method", "PrometheusController.index");
        appOnlineCount = registry.gauge("app_online_count", new AtomicInteger(0));

    }

    /**
     * 监控平台是否可用，每调用一次就记录一次，每调用一次 counterIndex 就加一
     *
     * @return 返回监控平台调用次数
     */
    @GetMapping("testIsUsable")
    public String testIsUsable() {
        counterIndex.increment();
        return counterIndex.count() + " index of springboot-prometheus.";
    }

    /**
     * 监控平台核心接口请求次数
     *
     * @return 返回监控平台核心接口请求次数
     */
    @GetMapping("testIsCore")
    public String testIsCore() {
        counterCore.increment();
        return counterCore.count() + " index of springboot-prometheus.";
    }


    /**
     * 测试实时在线人数，动态数据，每次请求数据可能都不一样
     *
     * @return
     */
    @GetMapping("/online")
    public Object online() {
        int people = 0;
        try {
            people = new Random().nextInt(2000);
            appOnlineCount.set(people);
        } catch (Exception e) {
            return e;
        }
        return "current online people: " + people;
    }


    @GetMapping("/test")
    @ResponseBody
    public String test() {
        registry.counter("test",
                "from", "127.0.0.1",
                "method", "test"
        ).increment();
        return "ok";
    }
}
