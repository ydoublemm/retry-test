package org.example.Task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.omg.CORBA.COMM_FAILURE;

import java.util.Date;


/**
 * Created by Enzo Cotter on 2020/6/7.
 */
@Slf4j
public class RetryTask {


    /**
     * 重试方法
     * @return
     */
    public static boolean retryTask(String param)  {

        log.info("收到请求参数:{}",param);

        int i = RandomUtils.nextInt(0,11);
        log.info("随机生成的数:{}",i);
        if (i == 0) {
            log.info("为0,抛出参数异常.");
            throw new IllegalArgumentException("参数异常");
        }else if (i  == 1){
            log.info("为1,返回true.");
            return true;
        }else if (i == 2){
            log.info("为2,返回false.");
            return false;
        }else{
            //为其他
            log.info("大于2,抛出自定义异常.");
            throw new COMM_FAILURE("大于2,抛出远程访问异常");
        }
    }

    public static void main(String[] args) {
        retryTask("1");
    }




}
