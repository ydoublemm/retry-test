package org.example;

import org.junit.Test;

/**
 * Created by caojidasabi on 2020/8/30.
 */
public class SimpleRandomLoadBalanceTest  {

    @Test
    public void testMian(){
        SimpleRandomLoadBalance simpleRandomLoadBalance = new SimpleRandomLoadBalance();

        simpleRandomLoadBalance.result(2000);


    }


}