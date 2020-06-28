package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.Task.RetryTask;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/6/7.
 */
@Slf4j
public class SpringRetryTest {


    /**
     * 重试间隔时间ms,默认1000ms
     * */
    private static long fixedPeriodTime = 3000L;
    /**
     * 最大重试次数,默认为3
     */
    private static int maxRetryTimes = 3;
    /**
     * 表示哪些异常需要重试,key表示异常的字节码,value为true表示需要重试
     */
    private static Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();



    public static void main(String[] args) {

        exceptionMap.put(COMM_FAILURE.class,true);

        // 构建重试模板实例
        RetryTemplate retryTemplate = new RetryTemplate();

        // 设置重试回退操作策略，主要设置重试间隔时间
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(fixedPeriodTime);

        // 设置重试策略，主要设置重试次数
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxRetryTimes, exceptionMap);

        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        Boolean execute = retryTemplate.execute(
                //RetryCallback
                retryContext -> {
                    boolean b = RetryTask.retryTask("abc");
                    log.info("调用的结果:{}", b);
                    return b;
                },
                retryContext -> {
                    //RecoveryCallback
                    log.info("已达到最大重试次数或抛出了不重试的异常~~~");
                    return true;
                }
        );

        log.info("执行结果:{}",execute);


    }



}
