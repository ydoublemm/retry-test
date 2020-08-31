package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by caojidasabi on 2020/8/30.
 */
public class SimpleRoundRobinLoadBalanceTest {


    @Test
    public void testMian(){
        SimpleRoundRobinLoadBalance simpleRoundRobinLoadBalance = new SimpleRoundRobinLoadBalance();

        simpleRoundRobinLoadBalance.result(6666);


    }
}