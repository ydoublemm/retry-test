package org.example;

import org.example.pojo.Server;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by caojidasabi on 2020/8/30.
 */
public class SimpleRoundRobinLoadBalance extends AbstractLoadBalance{


    protected AtomicInteger atomicInteger = new AtomicInteger(-1);


    @Override
    protected Server doSelect() {

        return getServers().get((atomicInteger.addAndGet(1)%getServers().size()));

    }
}
